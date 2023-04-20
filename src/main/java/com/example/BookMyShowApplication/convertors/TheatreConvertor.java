package com.example.BookMyShowApplication.convertors;

import com.example.BookMyShowApplication.EntryDtos.TheatreEntryDto;
import com.example.BookMyShowApplication.Models.TheatreEntity;
import com.example.BookMyShowApplication.ResponseDto.TheatreResponseDto;

public class TheatreConvertor {

    public static TheatreEntity convertDtoEntity(TheatreEntryDto theatreEntryDto){

        TheatreEntity theatreEntity=TheatreEntity.builder().name(theatreEntryDto.getName()).location(theatreEntryDto.getLocation()).build();

        return theatreEntity;
    }

   /* public static TheaterResponseDto convertEntityToDto(TheaterEntity theaterEntity){

        return TheaterResponseDto.builder().id(theaterEntity.getId()).name(theaterEntity.getName())
                .city(theaterEntity.getCity()).address(theaterEntity.getAddress())
                .type(theaterEntity.getType())
                .build();
    }*/

    public static TheatreResponseDto convertToEntity(TheatreEntity theatreEntity){

        return TheatreResponseDto.builder()
                .id(theatreEntity.getId())
                .name(theatreEntity.getName())
                .address(theatreEntity.getLocation())
                .build();
    }
}
