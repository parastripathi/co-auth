package com.training.coauth.repository;

import com.training.coauth.entity.Channel;
import com.training.coauth.entity.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel,Long> {
}
