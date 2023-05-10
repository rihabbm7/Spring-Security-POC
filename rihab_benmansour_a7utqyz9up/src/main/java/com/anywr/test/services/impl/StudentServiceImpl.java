package com.anywr.test.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.anywr.test.dao.dto.StudentDto;
import com.anywr.test.dao.repositories.StudentRepository;
import com.anywr.test.services.IStudentService;
import com.anywr.test.utils.HttpResponse;

@Service
public class StudentServiceImpl implements IStudentService{
	@Autowired
	private StudentRepository studentRepository; 
	
	public StudentServiceImpl(StudentRepository studentRepository2) {
		this.studentRepository=studentRepository2;
	}

	@Override
	public HttpResponse<List<StudentDto>>  getStudentsFiltered(String className, String teacherFullName, int pageNumber, int pageSize){
	    Pageable pageable = PageRequest.of(pageNumber, pageSize);
	    Page<StudentDto> page = studentRepository.findByClassNameAndTeacherFullName(className, teacherFullName, pageable)
	    		.map(student->{
				    	StudentDto dto=new StudentDto();
				    	dto.setFirstName(student.getFirstname());
				    	dto.setId(student.getUserId());
				    	dto.setLastName(student.getLastname());
				    	dto.setLogin(student.getLogin());
				    	return dto;
	    });
	    return new HttpResponse<List<StudentDto>>(HttpStatus.OK, page.getContent())  ;
}
}
