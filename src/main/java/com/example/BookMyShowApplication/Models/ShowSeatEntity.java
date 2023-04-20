package com.example.BookMyShowApplication.Models;

import com.example.BookMyShowApplication.Enums.SeatType;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="show_seats")

@NoArgsConstructor

@Data

public class ShowSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isBooked;

    private int price;//price of CLASSIC Seat for that particular seat

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private Date bookedAt;


    //this is child w.r.t show

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private ShowEntity showEntity;


}
