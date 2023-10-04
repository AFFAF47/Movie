package dev.anas.movies.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Roles")
public class Role {
    @Id
    private ObjectId id;
    private String name;
    private List<Permission> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
