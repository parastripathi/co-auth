package com.training.coauth.repository;

import com.training.coauth.entity.User;
import com.training.coauth.entity.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {

}
