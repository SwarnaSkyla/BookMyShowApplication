package com.example.BookMyShowApplication.Models;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="theatres")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheatreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String location;

    //This is parent class w.r.t  theatreseatEntity

    @OneToMany(mappedBy = "theatreEntity",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TheatreSeatEntity> theatreSeatEntityList=new ArrayList<>();


    @OneToMany(mappedBy = "theatreEntity",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShowEntity> showEntityList=new ArrayList<>();

}

