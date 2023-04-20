package com.example.BookMyShowApplication.Repository;

import com.example.BookMyShowApplication.Models.TheatreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TheatreRepository extends JpaRepository<TheatreEntity,Integer> {
}

