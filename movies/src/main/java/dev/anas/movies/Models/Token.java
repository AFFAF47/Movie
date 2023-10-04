package dev.anas.movies.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "tokens")
public class Token {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String token;

    private TokenType tokenType = TokenType.BEARER;

    private boolean revoked;

    private boolean expired;

    @DBRef
    private User user;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
