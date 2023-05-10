package com.anywr.test.dao.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="classroom")
public class Classroom {
	@Id
	@GeneratedValue
	private Integer id;
	@Column
	private String name;
	@OneToMany(mappedBy = "classroom",cascade = CascadeType.PERSIST)
	private List<Student> students;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	public Classroom( String name,  Teacher teacher) {
		super();
	
		this.name = name;
		this.teacher = teacher;
	}
	public Classroom() {
		super();
	}

}
