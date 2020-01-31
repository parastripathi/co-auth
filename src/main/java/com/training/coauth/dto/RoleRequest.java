package com.training.coauth.dto;

public class RoleRequest {

    private Long channel_channel_id;
    private String role;

    public Long getChannel_channel_id() {
        return channel_channel_id;
    }

    public void setChannel_channel_id(Long channel_channel_id) {
        this.channel_channel_id = channel_channel_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
