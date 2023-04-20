package com.example.BookMyShowApplication.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TicketResponseDto {
    int id;
    String alloted_seats;
    int amount;
    String movieName;

}
