package com.training.coauth.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles",uniqueConstraints=@UniqueConstraint(columnNames = {"user_id","channel_channel_id","role"}))
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @OneToOne(cascade = CascadeType.ALL)
    private Channel channel;

    private String role;

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
