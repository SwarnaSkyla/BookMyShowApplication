package com.example.BookMyShowApplication.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor

public class ShowResponseDto {

    int id;

    LocalDate showDate;

    LocalTime showTime;

    MovieResponseDto movieResponseDto;

    TheatreResponseDto theatreResponseDto;

}
