package dev.anas.movies.Services;

import dev.anas.movies.Models.Movie;
import dev.anas.movies.Repositories.IMovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private IMovieRepository movieRepository;

    public List<Movie> AllMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieByImdbId(String  imdbId){
        return movieRepository.findByImdbId(imdbId);
    }

    public Movie saveMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public Boolean deleteMovie(String imdbId){
        return movieRepository.deleteByImdbId(imdbId);
    }

    public Optional<Movie> updateMovie(String imdbId, Movie updatedMovie){
        Optional<Movie> currentMovie = getMovieByImdbId(imdbId);
        if(currentMovie.isPresent()){
            Movie movieToUpdate = currentMovie.get();
            if(!movieToUpdate.getTitle().isEmpty()){
                movieToUpdate.setTitle(updatedMovie.getTitle());
            }
            if(!movieToUpdate.getGeneres().isEmpty()){
                movieToUpdate.setGeneres(updatedMovie.getGeneres());
            }
            if(!movieToUpdate.getPoster().isEmpty()){
                movieToUpdate.setPoster(updatedMovie.getPoster());
            }
            if(!movieToUpdate.getReleaseDate().isEmpty()){
                movieToUpdate.setReleaseDate(updatedMovie.getReleaseDate());
            }
            if(!movieToUpdate.getBackdrops().isEmpty()){
                movieToUpdate.setBackdrops(updatedMovie.getBackdrops());
            }
            if(!movieToUpdate.getTrailerLink().isEmpty()){
                movieToUpdate.setTrailerLink(updatedMovie.getTrailerLink());
            }
            Movie savedMovie = movieRepository.save(movieToUpdate);
            return Optional.of(savedMovie);
        }else{
            return Optional.empty();
        }
    }

}
