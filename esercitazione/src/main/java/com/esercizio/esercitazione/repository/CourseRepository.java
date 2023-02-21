package com.esercizio.esercitazione.repository;

import com.esercizio.esercitazione.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}