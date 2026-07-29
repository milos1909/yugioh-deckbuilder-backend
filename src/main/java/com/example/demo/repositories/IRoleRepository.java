package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer>{
    Role findByName(String name);
}
