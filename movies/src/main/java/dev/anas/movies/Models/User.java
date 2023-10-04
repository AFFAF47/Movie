package dev.anas.movies.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.management.relation.Role;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private ObjectId id;
    @NonNull
    private String firstname;
    private String secondname;
    @Indexed(unique = true)
    private String email;
    @NonNull
    private String password;
    @NonNull
    private Role role;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", secondname='" + secondname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
