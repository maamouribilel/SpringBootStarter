package com.learningspring.demo.entities;

import lombok.Data;
import lombok.NonNull;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User extends RepresentationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
    private String userName;
    @Size(min = 2, message = "must be at least 2 characters.")
    @Column(name = "FIRST_NAME", length = 50, nullable = false, unique = false)
    private String firstName;
    @Column(name = "LAST_NAME", length = 50, nullable = false, unique = false)
    private String lastName;
    @Column(name = "EMAIl", length = 50, nullable = false, unique = true)
    @NotEmpty(message = "{email.notEmpty}")
    private String email;
    @Column(name = "ROLE", length = 50, nullable = false, unique = false)
    private String role;
    @Column(name = "SSN", length = 50, nullable = false, unique = true)
    private String ssn;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User() {
    }
}
