package com.project.registration_system.components.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.registration_system.components.course.entities.UserCourse;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
}
