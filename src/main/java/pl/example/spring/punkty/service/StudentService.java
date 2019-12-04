package pl.example.spring.punkty.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.vavr.collection.List;
import pl.example.spring.punkty.entity.StudentRow;
import pl.example.spring.punkty.model.NewStudent;
import pl.example.spring.punkty.model.Student;
import pl.example.spring.punkty.repository.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository repository;

	@Autowired
	public StudentService(StudentRepository repository) {
		this.repository = repository;
	}

	public List<Student> getStudents(){
//		return List.ofAll(this.repository.findAll())
//				.map(getStudentRowFunction());
		return List.ofAll(this.repository.findAll()).map(StudentRow::toStudent);
	}

	/*private Function<StudentRow, Student> getStudentRowFunction() {
		return dbObj->
		new Student(
				dbObj.getId(),
				dbObj.getName(),
				dbObj.getNumber(),
				dbObj.getGrade());
	}*/
	
	public Student addStudent(final NewStudent newStudent) {
//		StudentRow created = this.repository.save(new StudentRow(theStudent.getName(), 
//				theStudent.getNumber(), theStudent.getGrade()));
//		return getStudentRowFunction().apply(created);
		return this.repository.save(new StudentRow(
				newStudent.getName(),
				newStudent.getNumber(),
				newStudent.getGrade())).toStudent();
	}
	
	@Transactional
	public Optional<Student> changeNumber(long studentId, String newNumber) {
		final Optional<StudentRow> student = this.repository.findById(studentId);
		
		return student.map(c -> {
			c.setNumber(newNumber);
			return c.toStudent();
		});
	}
	
	
	
	
}
