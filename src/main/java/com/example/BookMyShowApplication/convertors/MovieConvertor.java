package com.example.BookMyShowApplication.convertors;

import com.example.BookMyShowApplication.EntryDtos.MovieEntryDto;
import com.example.BookMyShowApplication.Models.MovieEntity;
import com.example.BookMyShowApplication.ResponseDto.MovieNameAndIdObject;
import com.example.BookMyShowApplication.ResponseDto.MovieResponseDto;

public class MovieConvertor {

    public static MovieEntity convertEntryDtoEntity(MovieEntryDto movieEntryDto){

        MovieEntity movieEntity=MovieEntity.builder().movieName(movieEntryDto.getMovieName()).duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre()).language(movieEntryDto.getLanguage()).build();

        return movieEntity;

    }

    public static MovieNameAndIdObject convertEntryDto(MovieEntity movieEntity){
        return MovieNameAndIdObject.builder().id(movieEntity.getId()).name(movieEntity.getMovieName()).build();
    }

    public static MovieResponseDto convertResponseDto(MovieEntity movieEntity){
        return MovieResponseDto.builder().name(movieEntity.getMovieName()).genre(movieEntity.getGenre())
                .language(movieEntity.getLanguage()).build();
    }
}

