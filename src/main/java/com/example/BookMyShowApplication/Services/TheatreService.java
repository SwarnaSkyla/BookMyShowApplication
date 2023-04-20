package com.example.BookMyShowApplication.Services;

import com.example.BookMyShowApplication.EntryDtos.TheatreEntryDto;
import com.example.BookMyShowApplication.Enums.SeatType;
import com.example.BookMyShowApplication.Models.MovieEntity;
import com.example.BookMyShowApplication.Models.TheatreEntity;
import com.example.BookMyShowApplication.Models.TheatreSeatEntity;
import com.example.BookMyShowApplication.Repository.MovieRepository;
import com.example.BookMyShowApplication.Repository.TheatreRepository;
import com.example.BookMyShowApplication.Repository.TheatreSeatRepository;
import com.example.BookMyShowApplication.ResponseDto.TheatreResponseDto;
import com.example.BookMyShowApplication.convertors.TheatreConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TheatreService {

    @Autowired
    TheatreSeatRepository theatreSeatRepository;

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    MovieRepository movieRepository;
    public String addTheatre(TheatreEntryDto theatreEntryDto) throws Exception{

        //Do some validations??


        if(theatreEntryDto.getName()==null||theatreEntryDto.getLocation()==null) {
            throw new Exception("Name and location should be valid");
        }
        /*

        1.create theatreseats
        2.i need to save theatre:I need theatreEntity
        3.Always set the attributes before saving
         */

        TheatreEntity theatreEntity= TheatreConvertor.convertDtoEntity(theatreEntryDto);
        List<TheatreSeatEntity> theatreSeatEntityList=createTheatreSeats(theatreEntryDto,theatreEntity);

        theatreEntity.setTheatreSeatEntityList(theatreSeatEntityList);
        theatreRepository.save(theatreEntity);

        return "Theatre Added ";
    }


    private List<TheatreSeatEntity> createTheatreSeats(TheatreEntryDto theatreEntryDto,TheatreEntity theatreEntity){
        int noClassicSeats=theatreEntryDto.getClassicSeatsCount();

        int noPremiumSeats=theatreEntryDto.getPremiumSeatsCount();

        List<TheatreSeatEntity> theatreSeatEntityList=new ArrayList<>();

        //Created  the classic seats
        for(int count=1;count<=noClassicSeats;count++){
            //We need to make a new TheatreSeatEntity

            TheatreSeatEntity theatreSeatEntity=TheatreSeatEntity.builder().seatType(SeatType.CLASSIC)
                    .seatNo(String.valueOf(count)+"C").theatreEntity(theatreEntity).build();
            theatreSeatEntityList.add(theatreSeatEntity);
        }

        //Created premium seats
        for(int count=1;count<=noPremiumSeats;count++){
            TheatreSeatEntity theatreSeatEntity=TheatreSeatEntity.builder()
                    .seatType(SeatType.PREMIUM).seatNo(String.valueOf(count)+"P").theatreEntity(theatreEntity).build();


            theatreSeatEntityList.add(theatreSeatEntity);
        }

        //not saving the child

//        theatreSeatRepository.saveAll(theatreSeatEntityList);

        return theatreSeatEntityList;

    }

//    TheaterSeatsEntity getTheaterSeat(String seatName, int rate, SeatType seatType){
//
//        return TheaterSeatsEntity.builder().seatNumber(seatName).rate(rate).seatType(seatType).build();
//    }
//
//    //Seperate function will be create...
//
//
//    @Override
//    public TheaterResponseDto getTheater(int id) {
//
//        TheaterEntity theaterEntity = theaterRepository.findById(id).get();
//
//        TheaterResponseDto theaterResponseDto = TheaterConverter.convertEntityToDto(theaterEntity);
//
//        return theaterResponseDto;
//    }

    public TheatreResponseDto getTheatre(Integer id){

        TheatreEntity theatreEntity=new TheatreEntity();

        try{
            theatreEntity=theatreRepository.findById(id).get();
        }
        catch (Exception e){
            System.out.println("Theatre does not exist with id "+id);
        }
        TheatreResponseDto theatreResponseDto=TheatreConvertor.convertToEntity(theatreEntity);

        return theatreResponseDto;
    }
    public ResponseEntity getAllTheatres(){
        return new ResponseEntity(theatreRepository.findAll(), HttpStatus.ACCEPTED);
    }
}
