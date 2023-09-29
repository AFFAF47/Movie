package dev.anas.movies.Services;

import dev.anas.movies.Models.Movie;
import dev.anas.movies.Models.Review;
import dev.anas.movies.Repositories.IMovieRepository;
import dev.anas.movies.Repositories.IReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private IReviewRepository reviewRepository;
    @Autowired
    private IMovieRepository movieRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId){
        Review review = new Review(reviewBody);
        reviewRepository.insert(review);

        mongoTemplate.update(Movie.class).matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review)).first();

        return review;
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public List<Review> getReviewByImdbId(String imdbId){
        Optional<Movie> movie = movieRepository.findByImdbId(imdbId);
        if(movie.isPresent()) {
            return movie.get().getReviewIds();
        }else{
            return Collections.emptyList();
        }
    }
}
