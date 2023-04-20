package com.example.BookMyShowApplication.Models;

import com.example.BookMyShowApplication.Enums.Genre;
import com.example.BookMyShowApplication.Enums.Language;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="movies")
@NoArgsConstructor

@Data
@Builder
@AllArgsConstructor
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(unique = true,nullable = false)
    private String movieName;

    private double rating;

    private int duration;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    //This is parent to showEntity
    @OneToMany(mappedBy = "movieEntity",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShowEntity> showEntityList=new ArrayList<>();


}
