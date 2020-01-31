package com.training.coauth.repository;

import com.training.coauth.entity.Role;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{

    @Query(value = "select role from roles  where user_id = ?1 and channel_channel_id = ?2",nativeQuery = true)
    String getUserRole(@Param("user_id") Long userId , @Param("provider") Long provider);

}
