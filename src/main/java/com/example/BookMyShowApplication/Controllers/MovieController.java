package com.example.BookMyShowApplication.Controllers;


import com.example.BookMyShowApplication.EntryDtos.MovieEntryDto;
import com.example.BookMyShowApplication.ResponseDto.MovieNameAndIdObject;
import com.example.BookMyShowApplication.ResponseDto.MovieResponseDto;
import com.example.BookMyShowApplication.Services.MovieService;
import javax.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto) {
        try{
            String res=movieService.addMovie(movieEntryDto);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
        catch (Exception e){
            String res="Movie not added";
            return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getNameandId(@PathVariable Integer id){
        MovieNameAndIdObject movieNameAndIdObject=movieService.getNameAndId(id);
        if(movieNameAndIdObject.getId()==0){
            return new ResponseEntity<>("Movie does not exist with id "+id,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(movieNameAndIdObject,HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public MovieResponseDto getMovieGenre(@PathVariable Integer id){
        MovieResponseDto movieResponseDto=movieService.getMovieGenre(id);
        return movieResponseDto;
    }


    @GetMapping("/getAll")
    public ResponseEntity getAllMovies(){
        return movieService.getAllMovies();
    }

}
