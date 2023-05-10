package com.anywr.test.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anywr.test.dao.models.Teacher;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
