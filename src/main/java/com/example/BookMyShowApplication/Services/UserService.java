package com.example.BookMyShowApplication.Services;


import com.example.BookMyShowApplication.EntryDtos.UserEntryDto;
import com.example.BookMyShowApplication.Models.UserEntity;
import com.example.BookMyShowApplication.Repository.UserRepository;
import com.example.BookMyShowApplication.ResponseDto.UserResponseDto;
import com.example.BookMyShowApplication.convertors.UserConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto) throws Exception,NullPointerException{
//        //here we need to convert and save
//        /*
//        Old method:create an object and set attributes.
//        no need to set each attribute one by one
//
//        New Method:by builder
//        we can set it in one go
//
//         */
//        UserEntity userEntity=UserEntity.builder().age(userEntryDto.getAge()).name(userEntryDto.getName())
//                .address(userEntryDto.getAddress()).email(userEntryDto.getEmail()).mobNo(userEntryDto.getMobNo()).build();
//
//        //This is to set all of the attributes in 1 go.
//
//        userRepository.save(userEntity);
        UserEntity userEntity= UserConvertor.convertDtoEntity(userEntryDto);
        userRepository.save(userEntity);

        return "User Added successfully";

    }
    public UserResponseDto getUser(int id){
        // UserEntity user=new UserEntity();
        UserEntity userEntity=userRepository.findById(id).get();
        UserResponseDto userResponseDto=UserConvertor.convertDtoEntity(userEntity);
        return userResponseDto;

    }

    public List<UserEntity> getAllUsers(){
        List<UserEntity> list=new ArrayList<>();
        list=userRepository.findAll() ;
        return list;
    }


}

