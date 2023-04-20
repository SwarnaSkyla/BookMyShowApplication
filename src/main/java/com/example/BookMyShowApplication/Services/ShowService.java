package com.example.BookMyShowApplication.Services;

import com.example.BookMyShowApplication.EntryDtos.ShowEntryDto;
import com.example.BookMyShowApplication.Enums.SeatType;
import com.example.BookMyShowApplication.Models.*;
import com.example.BookMyShowApplication.Repository.MovieRepository;
import com.example.BookMyShowApplication.Repository.ShowRepository;
import com.example.BookMyShowApplication.Repository.TheatreRepository;
import com.example.BookMyShowApplication.ResponseDto.ShowResponseDto;
import com.example.BookMyShowApplication.ResponseDto.ShowTimingResponseDto;
import com.example.BookMyShowApplication.convertors.ShowConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    ShowRepository showRepository;
    public String addShow(ShowEntryDto showEntryDto){
        //Create a showEntity

        ShowEntity showEntity= ShowConvertor.convertEntryDtoEntity(showEntryDto);


        int movieId=showEntryDto.getMovieId();
        int theatreId=showEntryDto.getTheatreId();

        MovieEntity movieEntity=movieRepository.findById(movieId).get();


        TheatreEntity theatreEntity=theatreRepository.findById(theatreId).get();

        //Setting the attributes of foreign key

        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheatreEntity(theatreEntity);


        //Pending attributes the list of showseat entity




        List<ShowSeatEntity> seatEntityList=createShowSeatEntity(showEntryDto,showEntity);

        //My goal is to create the showSeat entity

        showEntity.setListOfShowSeats(seatEntityList);

        showEntity=showRepository.save(showEntity);
        //Now we also need to update the parent Entities

        movieEntity.getShowEntityList().add(showEntity);
        theatreEntity.getShowEntityList().add(showEntity);


        movieRepository.save(movieEntity);

        theatreRepository.save(theatreEntity);
//        List<ShowEntity> showEntityList=movieEntity.getShowEntityList();
//        showEntityList.add(showEntity);
//        movieEntity.setShowEntityList(showEntityList);
////        movieEntity.getShowEntityList().add(showEntity);
////        theatreEntity.getShowEntityList().add(showEntity);
//        movieRepository.save(movieEntity);
//
//        List<ShowEntity> showEntityList1=theatreEntity.getShowEntityList();
//        showEntityList1.add(showEntity);
//        theatreEntity.setShowEntityList(showEntityList1);
//
//        theatreRepository.save(theatreEntity);

        return "The show has been Added successfully";



    }

    private List<ShowSeatEntity> createShowSeatEntity(ShowEntryDto showEntryDto,ShowEntity showEntity){

        //Now the goal is to create the showseatEntity
        //We need to set its attribute

        TheatreEntity theatreEntity=showEntity.getTheatreEntity();

        List<TheatreSeatEntity> theatreSeatEntityList=theatreEntity.getTheatreSeatEntityList();

        List<ShowSeatEntity> seatEntityList=new ArrayList<>();

        for(TheatreSeatEntity theatreSeatEntity:theatreSeatEntityList){

            ShowSeatEntity showSeatEntity=new ShowSeatEntity();

            showSeatEntity.setSeatNo(theatreSeatEntity.getSeatNo());
            showSeatEntity.setSeatType(theatreSeatEntity.getSeatType());

            if(theatreSeatEntity.getSeatType().equals(SeatType.CLASSIC)){
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());
            }
            else{
                showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());
            }

            showSeatEntity.setBooked(false);
            showSeatEntity.setShowEntity(showEntity);//parent:foreign key for the showseat Entity

            seatEntityList.add(showSeatEntity);//Adding it to the list

        }
        return seatEntityList;



    }

    public LocalTime getShowTiming(int movieId){


//        MovieEntity movieEntity=movieRepository.findById(movieId).get();

//        TheatreEntity theatreEntity=theatreRepository.findById(theatreId).get();

        ShowEntity showEntity=showRepository.findById(movieId).get();


        ShowResponseDto showResponseDto=ShowConvertor.convertresponseDto(showEntity);
        return showResponseDto.getShowTime();


    }

    public ShowResponseDto getShow(int id){
        ShowEntity showEntity=new ShowEntity();

        try{
            showEntity=showRepository.findById(id).get();

        }
        catch (Exception e){
            System.out.println("Show does not exist with id "+id);
        }

        ShowResponseDto showResponseDto=ShowConvertor.convertresponseDto(showEntity);

        return showResponseDto;

    }

    public ResponseEntity getAllShows(){
        return new ResponseEntity(showRepository.findAll(), HttpStatus.ACCEPTED);
    }
}

