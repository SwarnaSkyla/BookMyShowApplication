package com.example.BookMyShowApplication.convertors;

import com.example.BookMyShowApplication.EntryDtos.ShowEntryDto;
import com.example.BookMyShowApplication.Models.MovieEntity;
import com.example.BookMyShowApplication.Models.ShowEntity;
import com.example.BookMyShowApplication.Models.TheatreEntity;
import com.example.BookMyShowApplication.ResponseDto.ShowResponseDto;
import com.example.BookMyShowApplication.ResponseDto.ShowTimingResponseDto;

public class ShowConvertor {

    public static ShowEntity convertEntryDtoEntity(ShowEntryDto showEntryDto){

        ShowEntity showEntity=ShowEntity.builder()
                .showDate(showEntryDto.getLocalDate())
                .showTime(showEntryDto.getLocalTime())
                .showType(showEntryDto.getShowType()).build();

        return showEntity;
    }
    public static ShowTimingResponseDto convertResponseDto(ShowEntity showEntity){

        return ShowTimingResponseDto.builder()
                .movieId(showEntity.getId())
                .theatreId(showEntity.getId())
                .showtime(showEntity.getShowTime()).build();

    }

    public static ShowResponseDto convertresponseDto(ShowEntity showEntity){

        return ShowResponseDto.builder()
                .id(showEntity.getId())
                .showTime(showEntity.getShowTime())
                .showDate(showEntity.getShowDate())
                .build();
    }
}
