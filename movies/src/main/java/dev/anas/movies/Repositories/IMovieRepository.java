package dev.anas.movies.Repositories;

import dev.anas.movies.Models.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMovieRepository extends MongoRepository<Movie, ObjectId> {
    public Optional<Movie> findByImdbId(String imdbId);
    public Boolean deleteByImdbId(String imdbId);
}
