package com.anywr.test.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anywr.test.dao.models.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

}
