package com.example.spring2023.service;

import com.example.spring2023.model.Role;
import org.springframework.beans.PropertyValues;


import java.util.List;

public interface RoleService {
    Role createRole(Role role);

    List<Role> findAll();
}