package com.project.registration_system.components.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.registration_system.components.user.entities.User_Notification;

public interface UserNotificationRepository extends JpaRepository<User_Notification, Long> {}
