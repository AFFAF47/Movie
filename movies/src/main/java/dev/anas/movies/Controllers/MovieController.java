package dev.anas.movies.Controllers;

import dev.anas.movies.Models.Movie;
import dev.anas.movies.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin(origins = "*")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<List<Movie>>(movieService.AllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getMovieByImdbId(@PathVariable String imdbId){
        return new ResponseEntity<Optional<Movie>>(movieService.getMovieByImdbId(imdbId), HttpStatus.OK);
    }

    @PostMapping("/addmovie")
    public Movie saveMovie(@RequestBody Movie movie){
        return new ResponseEntity<Movie>(movieService.saveMovie(movie),HttpStatus.CREATED).getBody();
    }

    @DeleteMapping("/deletemovie/{imdbId}")
    public boolean deleteMovie(@PathVariable String imdbId){
        return movieService.deleteMovie(imdbId);
    }

    @PutMapping("/updatemovie/{imdbId}")
    public ResponseEntity<Optional<Movie>> updateMovie(@PathVariable String imdbId, @RequestBody Movie updatedMovie){
        Optional<Movie> updated = movieService.updateMovie(imdbId, updatedMovie);
        if(updated.isPresent()){
            return new ResponseEntity<Optional<Movie>>(updated, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
