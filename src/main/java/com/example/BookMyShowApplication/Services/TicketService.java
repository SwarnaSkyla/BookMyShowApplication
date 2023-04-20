package com.example.BookMyShowApplication.Services;


import com.example.BookMyShowApplication.Models.*;
import com.example.BookMyShowApplication.EntryDtos.TicketEntryDto;
import com.example.BookMyShowApplication.Repository.MovieRepository;
import com.example.BookMyShowApplication.Repository.ShowRepository;
import com.example.BookMyShowApplication.Repository.TicketRepository;
import com.example.BookMyShowApplication.Repository.UserRepository;
import com.example.BookMyShowApplication.ResponseDto.MovieResponseDto;
import com.example.BookMyShowApplication.ResponseDto.TicketResponseDto;
import com.example.BookMyShowApplication.convertors.MovieConvertor;
import com.example.BookMyShowApplication.convertors.TicketConvertor;
import io.swagger.models.Response;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class TicketService {


    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MovieRepository movieRepository;

    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception {


        //1. Create TicketEntity from entryDto : Convert DTO ---> Entity
        TicketEntity ticketEntity = TicketConvertor.convertEntryToEntity(ticketEntryDto);


        //Validation : Check if the requested seats are available or not ?
        boolean isValidRequest = checkValidityofRequestedSeats(ticketEntryDto);

        if (isValidRequest == false) {
            throw new Exception("Requested seats are not available");
        }

        //We assume that the requestedSeats are valid


        //Calculate the total amount :
        ShowEntity showEntity = showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeatEntity> seatEntityList = showEntity.getListOfShowSeats();
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        int totalAmount = 0;
        for (ShowSeatEntity showSeatEntity : seatEntityList) {

            if (requestedSeats.contains(showSeatEntity.getSeatNo())) {
                totalAmount = totalAmount + showSeatEntity.getPrice();
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedAt(new Date());
            }
        }

        ticketEntity.setTotalAmount(totalAmount);


        //Setting the other attributes for the ticketEntity
        ticketEntity.setMovieName(showEntity.getMovieEntity().getMovieName());
        ticketEntity.setShowDate(showEntity.getShowDate());
        ticketEntity.setShowTime(showEntity.getShowTime());
        ticketEntity.setTheatreName(showEntity.getTheatreEntity().getName());


        //We need to set that string that talked about requested Seats
        String allotedSeats = getAllotedSeatsfromShowSeats(requestedSeats);
        ticketEntity.setBookedSeats(allotedSeats);


        //Setting the foreign key attributes
        UserEntity userEntity = userRepository.findById(ticketEntryDto.getUserId()).get();

        ticketEntity.setUserEntity(userEntity);
        ticketEntity.setShowEntity(showEntity);

        //Save the parent
        ticketEntity = ticketRepository.save(ticketEntity);


        List<TicketEntity> ticketEntityList = showEntity.getListOfBookedTickets();
        ticketEntityList.add(ticketEntity);
        showEntity.setListOfBookedTickets(ticketEntityList);

        showRepository.save(showEntity);


        List<TicketEntity> ticketEntityList1 = userEntity.getBookedTickets();
        ticketEntityList1.add(ticketEntity);
        userEntity.setBookedTickets(ticketEntityList1);

        userRepository.save(userEntity);


        String body = "Hi this is to confirm your booking for seat No " + allotedSeats + "for the movie : " + ticketEntity.getMovieName();


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("swarnaskyla@gmail.com");
        mimeMessageHelper.setTo(userEntity.getEmail());
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject("Confirming your booked Ticket");

        javaMailSender.send(mimeMessage);


        return "Ticket has successfully been added";

    }

    private String getAllotedSeatsfromShowSeats(List<String> requestedSeats) {

        String result = "";

        for (String seat : requestedSeats) {

            result = result + seat + ", ";

        }
        return result;

    }


    private boolean checkValidityofRequestedSeats(TicketEntryDto ticketEntryDto) {

        int showId = ticketEntryDto.getShowId();

        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        ShowEntity showEntity = showRepository.findById(showId).get();

        List<ShowSeatEntity> listOfSeats = showEntity.getListOfShowSeats();

        //Iterating over the list Of Seats for that particular show
        for (ShowSeatEntity showSeatEntity : listOfSeats) {

            String seatNo = showSeatEntity.getSeatNo();

            if (requestedSeats.contains(seatNo)) {

                if (showSeatEntity.isBooked() == true) {
                    return false; //Since this seat cant be occupied : returning false
                }
            }
        }
        //All the seats requested were available
        return true;

    }

    public ResponseEntity getTicket(Integer id) {
        TicketEntity ticketEntity = new TicketEntity();

        try {
            ticketEntity = ticketRepository.findById(id).get();

        } catch (Exception e) {
            return new ResponseEntity("Ticket is not found for id " + id, HttpStatus.BAD_REQUEST);
        }

        TicketResponseDto ticketResponseDto = TicketConvertor.convertEntityToDto(ticketEntity);

        return new ResponseEntity(ticketResponseDto, HttpStatus.ACCEPTED);
    }


    public ResponseEntity getAllTickets() {
        return new ResponseEntity(ticketRepository.findAll(), HttpStatus.ACCEPTED);
    }


    public void cancelTicket(int id) {
        TicketEntity ticketEntity = ticketRepository.findById(id).get();
        ticketEntity.setBookedSeats(null);

        ticketEntity.setMovieName(null);
        LocalTime time = LocalTime.of(0, 0, 0);
        LocalDate date = LocalDate.of(0000, 0, 0);
        ticketEntity.setShowTime(time);
        ticketEntity.setShowDate(date);
        ticketEntity.setTheatreName(null);
        ticketEntity.setTotalAmount(0);
        ticketEntity.getShowEntity().setListOfBookedTickets(null);
        ticketEntity.getUserEntity().setBookedTickets(null);
        ticketEntity.setTicketId(null);
        ticketRepository.save(ticketEntity);

    }
}
