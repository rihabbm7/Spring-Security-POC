package com.anywr.test.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.anywr.test.dao.dto.AuthenticationRequest;
import com.anywr.test.dao.dto.AuthenticationResponse;
import com.anywr.test.dao.repositories.StudentRepository;
import com.anywr.test.dao.repositories.TeacherRepository;
import com.anywr.test.dao.repositories.UserRepository;
import com.anywr.test.security.JwtService;
import com.anywr.test.services.IAuthService;

@Service
public class AuthenticationServiceImpl implements IAuthService{
	@Autowired
	private StudentRepository  studentRepository;
	@Autowired
	private TeacherRepository  teacherRepository;
	@Autowired
	private UserRepository  userRepository;
	@Autowired
	private  AuthenticationManager authenticationManager;
	@Autowired
	private  JwtService jwtService;
	@Autowired
	private PasswordEncoder encoder;
	/*
	 * @Override public AuthenticationResponse register(AuthenticationRequest
	 * request){ String jwtToken =""; if(request.getRole().equals(Roles.STUDENT)) {
	 * Student student=new
	 * Student(request.getFirstName(),request.getFirstName(),request.getLogin(),
	 * encoder.encode(request.getPassword())); studentRepository.save(student);
	 * jwtService.generateToken(new HashMap<>(),student); }else if
	 * (request.getRole().equals(Roles.TEACHER)) { Teacher teacher=new
	 * Teacher(request.getFirstName(),request.getFirstName(),request.getLogin(),
	 * encoder.encode(request.getPassword())); teacherRepository.save(teacher);
	 * jwtToken = jwtService.generateToken(new HashMap<>(),teacher); }
	 * 
	 * 
	 * return new AuthenticationResponse(jwtToken);
	 * 
	 * }
	 */

		@Override
	    public AuthenticationResponse authenticate(AuthenticationRequest request) {
	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(),request.getPassword()));
	        var user= userRepository.findByLogin(request.getLogin())
	                .orElseThrow();
	        Map<String,String> info = new HashMap<>();
	        info.put("id", user.getUserId().toString());
	        var jwtToken =jwtService.generateToken(info,user);
	        return new  AuthenticationResponse(jwtToken);

	    }
}
