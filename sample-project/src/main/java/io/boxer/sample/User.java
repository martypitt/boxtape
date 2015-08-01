package io.boxer.sample;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity @Data
public class User {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;
}
