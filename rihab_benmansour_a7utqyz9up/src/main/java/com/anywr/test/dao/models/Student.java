package com.anywr.test.dao.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.anywr.test.utils.Roles;

@Entity
@Table(name="student")
public class Student extends User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classroom_id")
	private Classroom classroom;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>(super.getAuthorities());
	        authorities.add(new SimpleGrantedAuthority("ROLE_"+Roles.STUDENT));

	        return authorities;
	}
	public Student(String firstname, String lastname, String login, String password,Classroom classroom) {
		super(firstname, lastname, login, password);
		this.classroom=classroom;
	}
	public Student(String firstname, String lastname, String login, String password) {
		super(firstname, lastname, login, password);
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}



}
