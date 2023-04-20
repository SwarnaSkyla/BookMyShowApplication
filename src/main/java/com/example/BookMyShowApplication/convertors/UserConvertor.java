package com.example.BookMyShowApplication.convertors;

import com.example.BookMyShowApplication.EntryDtos.UserEntryDto;
import com.example.BookMyShowApplication.Models.UserEntity;
import com.example.BookMyShowApplication.ResponseDto.UserResponseDto;

public class UserConvertor {
    //static is kept to avoid calling it via objects/instances
    public static UserEntity convertDtoEntity(UserEntryDto userEntryDto){

        UserEntity userEntity= UserEntity.builder().age(userEntryDto.getAge()).address(userEntryDto.getAddress())
                .email(userEntryDto.getEmail()).name(userEntryDto.getName()).mobNo(userEntryDto.getMobNo()).build();

        return userEntity;
    }

    public static UserResponseDto convertDtoEntity(UserEntity user){
        return UserResponseDto.builder().id(user.getId()).name(user.getName())
                .mobileNo(user.getMobNo()).build();
    }
}
