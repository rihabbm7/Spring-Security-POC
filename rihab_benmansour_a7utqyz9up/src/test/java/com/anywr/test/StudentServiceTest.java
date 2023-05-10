package com.anywr.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.anywr.test.dao.dto.StudentDto;
import com.anywr.test.dao.models.Classroom;
import com.anywr.test.dao.models.Student;
import com.anywr.test.dao.models.Teacher;
import com.anywr.test.dao.repositories.StudentRepository;
import com.anywr.test.services.impl.StudentServiceImpl;
import com.anywr.test.utils.HttpResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    
    
    @Test
    public void testGetStudentsFiltered() {
        // given
        Classroom classroom1 = new Classroom("Science", new Teacher("John", "Doe", "jdoe", "password"));
        Classroom classroom2 = new Classroom("Algebra", new Teacher("Angelina", "Jolie", "Angel", "pwd"));

        List<Student> students1 = Arrays.asList(
                new Student("Alice", "Smith", "asmith", "password", classroom1),
                new Student("Bob", "Jones", "bjones", "password", classroom1)
        );
        List<Student> students2 = Arrays.asList(
                new Student("Lady", "Gaga", "badR", "password", classroom2)
        );
        Page<Student> page1 = new PageImpl<>(students1);
        Page<Student> page2 = new PageImpl<>(students2);

        // when
        Mockito.when(studentRepository.findByClassNameAndTeacherFullName("Science", "John Doe", PageRequest.of(0, 10)))
                .thenReturn(page1);
        Mockito.when(studentRepository.findByClassNameAndTeacherFullName("Algebra", "Angelina Jolie", PageRequest.of(0, 10)))
        .thenReturn(page2);

        // call the service method with the test data
        StudentServiceImpl service = new StudentServiceImpl(studentRepository);
        HttpResponse<List<StudentDto>> response1 = service.getStudentsFiltered("Science", "John Doe", 0, 10);
        HttpResponse<List<StudentDto>> response2 = service.getStudentsFiltered("Algebra", "Angelina Jolie", 0, 10);

        // then
        Assert.assertEquals(HttpStatus.OK, response1.getStatus());
        Assert.assertEquals(2, response1.getPayload().size());
        Assert.assertEquals("Alice", response1.getPayload().get(0).getFirstName());
        Assert.assertEquals("Smith", response1.getPayload().get(0).getLastName());
        Assert.assertEquals("asmith", response1.getPayload().get(0).getLogin());
        
        Assert.assertEquals("Bob", response1.getPayload().get(1).getFirstName());
        Assert.assertEquals("Jones", response1.getPayload().get(1).getLastName());
        Assert.assertEquals("bjones", response1.getPayload().get(1).getLogin());

        Assert.assertEquals(HttpStatus.OK, response2.getStatus());
        Assert.assertEquals(1, response2.getPayload().size());
        Assert.assertEquals("Lady", response2.getPayload().get(0).getFirstName());
        Assert.assertEquals("Gaga", response2.getPayload().get(0).getLastName());
        Assert.assertEquals("badR", response2.getPayload().get(0).getLogin());

    }
}
