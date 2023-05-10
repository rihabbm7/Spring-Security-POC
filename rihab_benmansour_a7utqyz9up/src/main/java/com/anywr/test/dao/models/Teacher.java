package com.anywr.test.dao.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.anywr.test.utils.Roles;



@Entity
@Table(name="teacher")

public class Teacher extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
	private List<Classroom> classrooms;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>(super.getAuthorities());
	        authorities.add(new SimpleGrantedAuthority("ROLE_"+Roles.TEACHER));

	        return authorities;
	}
	public Teacher(String firstname, String lastname, String login, String password) {
		super(firstname, lastname, login, password);
	}
	public Teacher() {
		super();
	}
	
}
