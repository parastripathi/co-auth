package com.training.coauth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "users")
public class User {

    @EmbeddedId UserId id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String role;


}



