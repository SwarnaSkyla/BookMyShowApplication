package com.example.BookMyShowApplication.Models;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name="tickets")
@Data
@NoArgsConstructor

@Builder
@AllArgsConstructor
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String movieName;


    private LocalDate showDate;

    private LocalTime showTime;

    private int totalAmount;

    private String ticketId= UUID.randomUUID().toString();

    private String theatreName;

    private  String bookedSeats;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private UserEntity userEntity;//child w.r.t user


    //this is child w.r.t .showEntity

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private ShowEntity showEntity;
}
