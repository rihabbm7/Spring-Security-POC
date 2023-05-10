package com.anywr.test.services;

import java.util.List;

import com.anywr.test.dao.dto.StudentDto;
import com.anywr.test.dao.models.Student;
import com.anywr.test.utils.HttpResponse;

public interface IStudentService {

	HttpResponse<List<StudentDto>> getStudentsFiltered(String className, String teacherFullName, int pageNumber,
			int pageSize);

}
