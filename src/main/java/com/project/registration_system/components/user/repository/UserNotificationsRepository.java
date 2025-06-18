package com.project.registration_system.components.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.registration_system.components.user.entities.User_Notifications;

public interface UserNotificationsRepository extends JpaRepository<User_Notifications, Long> {}
