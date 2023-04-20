package com.example.BookMyShowApplication.ResponseDto;

import com.example.BookMyShowApplication.Enums.Genre;
import com.example.BookMyShowApplication.Enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class MovieResponseDto {

    private int id;

    private String name;

    private Genre genre;

    private Language language;


}
