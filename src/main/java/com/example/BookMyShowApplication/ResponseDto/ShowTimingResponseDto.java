package com.example.BookMyShowApplication.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor

public class ShowTimingResponseDto {

    private LocalTime showtime;

    private int movieId;

    private int theatreId;

}

