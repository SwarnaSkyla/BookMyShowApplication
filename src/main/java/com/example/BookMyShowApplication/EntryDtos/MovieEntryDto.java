package com.example.BookMyShowApplication.EntryDtos;


import com.example.BookMyShowApplication.Enums.Genre;
import com.example.BookMyShowApplication.Enums.Language;
import lombok.Data;

@Data
public class MovieEntryDto {

    private String movieName;

    private double rating;

    private int duration;

    private Language language;

    private Genre genre;

}
