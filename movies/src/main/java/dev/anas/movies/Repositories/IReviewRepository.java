package dev.anas.movies.Repositories;

import dev.anas.movies.Models.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IReviewRepository extends MongoRepository<Review, ObjectId> {
}
