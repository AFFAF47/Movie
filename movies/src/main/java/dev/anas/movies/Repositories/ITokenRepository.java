package dev.anas.movies.Repositories;

import dev.anas.movies.Models.Token;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ITokenRepository extends MongoRepository<Token, ObjectId> {
    @Query("{ 'user.id' : ?0, 'expired' : false, 'revoked' : false }")
    List<Token> findAllValidTokensByUserId(String userId);

    Optional<Token> findByToken(String token);
}
