package pl.example.spring.punkty.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import pl.example.spring.punkty.model.Student;

@Entity
public class StudentRow {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String number;
	private String grade;
	
	public Student toStudent() {
		return new Student(
				this.getId(),
				this.getName(),
				this.getNumber(),
				this.getGrade());
	}
	
	
	
	protected StudentRow() {
		super();
	}
	public StudentRow(String name, String number, String grade) {
		super();
		this.name = name;
		this.number = number;
		this.grade = grade;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
}
