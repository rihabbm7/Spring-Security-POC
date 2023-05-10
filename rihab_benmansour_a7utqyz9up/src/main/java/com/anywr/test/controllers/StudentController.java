package com.anywr.test.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anywr.test.dao.dto.StudentDto;
import com.anywr.test.services.IStudentService;
import com.anywr.test.utils.HttpResponse;

@Controller
@RequestMapping("/api/student")
public class StudentController {
	@Autowired
	private IStudentService studentService;
	
	//localhost:8080/api/student/list?pageSize=10&pageNumber=0&teacherName=Taha Ben Salah
	@GetMapping("/list")
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	public ResponseEntity<List<StudentDto>> getStudents(HttpServletRequest request,@RequestParam(required = false) String className,
			@RequestParam(required = false)String teacherName,
			@RequestParam(required = false)int pageNumber,@RequestParam(required = false)int pageSize){
		HttpResponse<List<StudentDto>> result = studentService.getStudentsFiltered(className, teacherName,pageNumber,pageSize);
		return new ResponseEntity<List<StudentDto>>(result.getPayload(), result.getStatus());
		
	}
}
