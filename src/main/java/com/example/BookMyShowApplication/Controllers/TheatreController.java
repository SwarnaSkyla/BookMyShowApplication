package com.example.BookMyShowApplication.Controllers;


import com.example.BookMyShowApplication.EntryDtos.TheatreEntryDto;
import com.example.BookMyShowApplication.ResponseDto.TheatreResponseDto;
import com.example.BookMyShowApplication.Services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @PostMapping("/add")
    public ResponseEntity addTheatre(@RequestBody TheatreEntryDto theatreEntryDto) throws Exception{

        try{
            String result=theatreService.addTheatre(theatreEntryDto);
            return new ResponseEntity(result, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/get/{id}")
    public ResponseEntity getTheatre(@PathVariable Integer id) {
        TheatreResponseDto theatreResponseDto = theatreService.getTheatre(id);
        if(theatreResponseDto.getId()==0){
            return new ResponseEntity("Theatre does not exist eith id "+id,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(theatreResponseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllTheatres")
    public ResponseEntity getAllTheatres(){
        return  theatreService.getAllTheatres();
    }


}
