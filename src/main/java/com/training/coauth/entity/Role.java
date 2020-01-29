package com.training.coauth.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    private Channel channel;

    @Id
    private String role;

}
