package dev.anas.movies.Repositories;

import dev.anas.movies.Models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<User, ObjectId> {
    public Optional<User> getUserByEmail(String email);
    public Optional<User> getUserByPhone(String number);
    public  Boolean deleteByEmail(String email);
}
