package com.example.BookMyShowApplication.Controllers;


import com.example.BookMyShowApplication.EntryDtos.UserEntryDto;
import com.example.BookMyShowApplication.Models.UserEntity;
import com.example.BookMyShowApplication.ResponseDto.UserResponseDto;
import com.example.BookMyShowApplication.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
//
//    @GetMapping("/printLogs")
//    public String printLogs(){
////level of root is based on given in application properties
//        //Advance way of writing System
//        log.trace("This is a trace");
//        log.debug("This is a Debug");
//        log.info("This is a info");
//        log.warn("This is a warn");
//        log.error("This is a error");
//
//        return "Printed logs";
//    }

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto){

        try{
            String response= userService.addUser(userEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            String result="User could not be added";
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable(value ="id") int id){
        UserResponseDto userResponseDto=userService.getUser(id);
        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        List<UserEntity> userEntities=new ArrayList<>();
        userEntities=userService.getAllUsers();
        return new ResponseEntity<List<UserEntity>>(userEntities,HttpStatus.ACCEPTED);
    }



}
