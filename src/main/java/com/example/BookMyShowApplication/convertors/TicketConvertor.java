package com.example.BookMyShowApplication.convertors;

import com.example.BookMyShowApplication.EntryDtos.TicketEntryDto;
import com.example.BookMyShowApplication.Models.TheatreEntity;
import com.example.BookMyShowApplication.Models.TicketEntity;
import com.example.BookMyShowApplication.ResponseDto.TicketResponseDto;

public class TicketConvertor {


    public static TicketEntity convertEntryToEntity(TicketEntryDto ticketEntryDto){

        TicketEntity ticketEntity= new TicketEntity();

        return ticketEntity;
    }
    public static TicketResponseDto convertEntityToDto(TicketEntity ticketEntity){

        return TicketResponseDto.builder().id((int) ticketEntity.getId()).amount(ticketEntity.getTotalAmount())
                .alloted_seats(ticketEntity.getBookedSeats()).movieName(ticketEntity.getMovieName())
                .build();

    }
}
