package pl.example.spring.punkty.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.vavr.collection.List;
import pl.example.spring.punkty.entity.ScoreRow;
import pl.example.spring.punkty.entity.StudentRow;
import pl.example.spring.punkty.model.NewStudent;
import pl.example.spring.punkty.model.Score;
import pl.example.spring.punkty.model.Student;
import pl.example.spring.punkty.repository.ScoreRepository;
import pl.example.spring.punkty.repository.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository studentRepository;
	private final ScoreRepository scoreRepository;

	@Autowired
	public StudentService(StudentRepository repository, ScoreRepository scoreRepository) {
		this.studentRepository = repository;
		this.scoreRepository = scoreRepository;
	}

	public List<Student> getStudents(){
//		return List.ofAll(this.repository.findAll())
//				.map(getStudentRowFunction());
		return List.ofAll(this.studentRepository.findAll()).map(StudentRow::toStudent);
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
		return this.studentRepository.save(new StudentRow(
				newStudent.getName(),
				newStudent.getNumber(),
				newStudent.getGrade())).toStudent();
	}
	
	@Transactional
	public Optional<Student> changeNumber(long studentId, String newNumber) {
		final Optional<StudentRow> student = this.studentRepository.findById(studentId);
		
		return student.map(c -> {
			c.setNumber(newNumber);
			return c.toStudent();
		});
	}
	
	public Optional<Student> deleteStudent(long studentId) {
		final Optional<StudentRow> student = this.studentRepository.findById(studentId);
		
		studentRepository.deleteById(studentId);
		
		return student.map(StudentRow::toStudent);
	}

	@Transactional
	public Optional<Integer> addScore(final long studentId, final Score score) {
		final Optional<StudentRow> student = this.studentRepository.findById(studentId);
		return student.map(c->{
			int existingScore = List.ofAll(c.getScores())
					.foldLeft(0, (p,s)->p + s.getScore());
			final ScoreRow newScore = new ScoreRow(score.score, score.comment, c);
			this.scoreRepository.save(newScore);
			return existingScore + score.score;
		});
	}
	
	
	
}
