package com.project.registration_system.components.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.registration_system.components.user.entities.User;

@Repository()
public interface UserRepository extends JpaRepository<User, Long>{
    User findUserByName(String name);
}
