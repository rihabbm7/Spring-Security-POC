package com.anywr.test.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.anywr.test.dao.models.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>,PagingAndSortingRepository<Student, Integer> {
	@Query(nativeQuery = true,
			value = "SELECT * FROM Student s JOIN classroom c\r\n"
					+ "on s.classroom_id=c.id\r\n"
					+ "JOIN teacher t on t.USER_ID=c.teacher_id\r\n"
					+ " WHERE (:className IS NULL OR c.name = :className) AND (:teacherFullName IS NULL OR (t.firstName || ' ' || t.lastName) = :teacherFullName)")
    Page<Student> findByClassNameAndTeacherFullName(String className, String teacherFullName, Pageable pageable);

}
