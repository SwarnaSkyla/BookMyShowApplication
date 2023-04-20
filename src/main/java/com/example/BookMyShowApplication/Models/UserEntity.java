package com.example.BookMyShowApplication.Models;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor

@Builder
@AllArgsConstructor

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;


    @Column(unique = true,nullable = false)
    private String email;

    @NonNull
    @Column(unique = true)
    private String mobNo;

    private String address;

    //this is parent w.r.t to ticket

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TicketEntity> bookedTickets=new ArrayList<>();





}
