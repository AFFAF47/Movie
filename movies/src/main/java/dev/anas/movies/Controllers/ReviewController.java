package dev.anas.movies.Controllers;

import dev.anas.movies.Models.Review;
import dev.anas.movies.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        return new ResponseEntity<List<Review>>(reviewService.getAllReviews(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<List<Review>> getReviewByImdbId(@PathVariable String imdbId){
        return new ResponseEntity<List<Review>>(reviewService.getReviewByImdbId(imdbId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload){
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.CREATED);
    }
}
