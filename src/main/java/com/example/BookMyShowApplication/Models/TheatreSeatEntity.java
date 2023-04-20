package com.example.BookMyShowApplication.Models;

import com.example.BookMyShowApplication.Enums.SeatType;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="theatre_seats")
@Data

@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheatreSeatEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;


    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private String seatNo;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private TheatreEntity theatreEntity;

}
