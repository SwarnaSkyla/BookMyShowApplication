package com.example.BookMyShowApplication.Repository;

import com.example.BookMyShowApplication.Models.TheatreEntity;
import com.example.BookMyShowApplication.Models.TheatreSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreSeatRepository extends JpaRepository<TheatreSeatEntity,Integer> {
}
