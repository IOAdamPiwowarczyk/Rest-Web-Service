package pl.example.spring.punkty.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewStudent {
	
	@JsonProperty("name")
	private final String name;
	@JsonProperty("number")
	private final String number;
	@JsonProperty("grade")
	private final String grade;
	
	public NewStudent(String name, String number, String grade) {
		super();
		this.name = name;
		this.number = number;
		this.grade = grade;
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
