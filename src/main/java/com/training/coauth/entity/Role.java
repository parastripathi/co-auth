package com.training.coauth.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "email")
    private User users;


}
