package pl.example.spring.punkty.model;

import com.fasterxml.jackson.annotation.JsonCreator;


public class Student {

	private final long id;	
	private final String name;
	private final String number;
	private final String grade;
	
	@JsonCreator
	public Student(long id, String name, String number, String grade) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.grade = grade;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public String getGrade() {
		return grade;
	}
	
	
}
