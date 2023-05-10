package com.anywr.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.anywr.test.dao.models.Classroom;
import com.anywr.test.dao.models.Student;
import com.anywr.test.dao.models.Teacher;
import com.anywr.test.dao.repositories.ClassroomRepository;
import com.anywr.test.dao.repositories.StudentRepository;
import com.anywr.test.dao.repositories.TeacherRepository;

@SpringBootApplication
public class RihabBenmansourA7utqyz9upApplication implements CommandLineRunner {
	@Autowired
	private ClassroomRepository classroomRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private PasswordEncoder encoder;
	public static void main(String[] args) {
		SpringApplication.run(RihabBenmansourA7utqyz9upApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Teacher teacher1=new Teacher("Taha","Ben Salah","login01",encoder.encode("AbCd"));
		Teacher teacher2=new Teacher("Rania","Jmal","login02",encoder.encode("AbCd"));
		List<Teacher> saved =teacherRepository.saveAll( List.of(teacher1,teacher2) );
		
		Classroom maths=new Classroom( "Math", saved.get(0));
		Classroom hist=new Classroom( "History", saved.get(1));

		Classroom mathSaved= classroomRepository.save(maths);
		Student s1=new Student("Rihab", "Ben Mansour", "login2",encoder.encode("AbCd"),mathSaved);
		Student s2=new Student("Amira", "Jlasi", "login3", encoder.encode("AbCd"),mathSaved);
		Student s3=new Student("Mohamed", "Saidani", "login5",encoder.encode("AbCd"),mathSaved);
		Student s4=new Student("Salah", "Mensi", "login4", encoder.encode("AbCd"),mathSaved);
		
		Classroom histSaved= classroomRepository.save(hist);

		Student s5=new Student("Moez", "Boukhris", "login6",encoder.encode("AbCd"),histSaved);
		Student s6=new Student("Hajer", "Gouja", "login7", encoder.encode("AbCd"),histSaved);
		studentRepository.saveAll(List.of(s1, s2, s3, s4, s5, s6));


	}

}
