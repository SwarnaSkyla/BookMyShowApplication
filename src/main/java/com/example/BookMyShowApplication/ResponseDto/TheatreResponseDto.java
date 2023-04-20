package com.example.BookMyShowApplication.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TheatreResponseDto {

    int id;

    String name;

    String address;


}

