package com.example.BookMyShowApplication.Controllers;


import com.example.BookMyShowApplication.EntryDtos.ShowEntryDto;
import com.example.BookMyShowApplication.ResponseDto.ShowResponseDto;
import com.example.BookMyShowApplication.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/shows")
public class ShowController {


    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto){

        String result= showService.addShow(showEntryDto);
        return new ResponseEntity<String>(result, HttpStatus.CREATED);
    }

    @GetMapping("/showTime/{movieId}")
    public ResponseEntity<LocalTime> getShowTiming(@PathVariable("movieId") int movieId){

        LocalTime timing=showService.getShowTiming(movieId);
        return new ResponseEntity<>(timing,HttpStatus.FOUND);
    }

    @GetMapping("get/{id}")
    public ResponseEntity getTheatre(@PathVariable Integer id){
        ShowResponseDto showResponseDto=showService.getShow(id);
        if(showResponseDto.getId()==0){
            return new ResponseEntity<>("Show does not exist with id "+id,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(showResponseDto,HttpStatus.FOUND);
    }
    @GetMapping("/getAllShows")
    public ResponseEntity getAllShows(){
        return showService.getAllShows();
    }

}
