package com.example.BookMyShowApplication.Models;


import com.example.BookMyShowApplication.Enums.ShowType;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="shows")
@Data


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private LocalDate showDate;
    private LocalTime showTime;


    @Enumerated(value = EnumType.STRING)
    private ShowType showType;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    //This is child w.r.t movieEntity

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private MovieEntity movieEntity;


    //This is child w.r.t theatreEntity

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private TheatreEntity theatreEntity;

    //parent w.r.t ticket
    @OneToMany(mappedBy = "showEntity",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TicketEntity> listOfBookedTickets=new ArrayList<>();


    //parent to showseat
    @OneToMany(mappedBy = "showEntity",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShowSeatEntity> listOfShowSeats=new ArrayList<>();



}

