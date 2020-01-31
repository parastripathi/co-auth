package com.training.coauth.Utility;

import com.training.coauth.entity.Role;
import com.training.coauth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleUtils {

    @Autowired
    RoleRepository roleRepository;

    public String getRoleById(Long id,Long provider) {
        String userRole = roleRepository.getUserRole(id,provider);
        return userRole;
    }

}
