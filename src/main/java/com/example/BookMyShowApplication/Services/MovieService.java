package com.example.BookMyShowApplication.Services;


import com.example.BookMyShowApplication.EntryDtos.MovieEntryDto;
import com.example.BookMyShowApplication.Models.MovieEntity;
import com.example.BookMyShowApplication.Repository.MovieRepository;
import com.example.BookMyShowApplication.ResponseDto.MovieNameAndIdObject;
import com.example.BookMyShowApplication.ResponseDto.MovieResponseDto;
import com.example.BookMyShowApplication.convertors.MovieConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    public String addMovie(MovieEntryDto movieEntryDto){
        MovieEntity movieEntity= MovieConvertor.convertEntryDtoEntity(movieEntryDto);
        movieRepository.save(movieEntity);
        return "Movie added ";
    }

    public MovieNameAndIdObject getNameAndId(int id){
        MovieEntity movieEntity=movieRepository.findById(id).get();

        MovieNameAndIdObject obj=MovieConvertor.convertEntryDto(movieEntity);

        return obj;
    }

    public MovieResponseDto getMovieGenre(int id){

        MovieEntity movieEntity=movieRepository.findById(id).get();

        MovieResponseDto movieResponseDto=MovieConvertor.convertResponseDto(movieEntity);

        return movieResponseDto;

    }

    public ResponseEntity getAllMovies(){
        return new ResponseEntity(movieRepository.findAll(), HttpStatus.ACCEPTED);
    }

}

