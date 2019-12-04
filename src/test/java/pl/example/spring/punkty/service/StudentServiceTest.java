package pl.example.spring.punkty.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.vavr.collection.List;
import pl.example.spring.punkty.model.NewStudent;
import pl.example.spring.punkty.model.Student;
import pl.example.spring.punkty.repository.StudentRepository;
import pl.example.spring.punkty.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
	
	@Autowired
	StudentRepository repository;
	
//	@Autowired
//	StudentService service;
	
	@Test
	public void getEmptyList() {	
		final StudentService service = new StudentService(repository);
		List<Student> students = service.getStudents();
		assertTrue(students.isEmpty());
	}
	
	@Test 
	public void addStudent() {
		final StudentService service = new StudentService(repository);
		final Student created = service.addStudent(new NewStudent("Student1", "1-2-3", "IP"));
		assertNotNull(created);
	}
	
	@Test
	public void addStudentIsReturned() {
		final StudentService service = new StudentService(repository);
		service.addStudent(new NewStudent("Student1", "1-2-3", "IP"));
		final List<Student> all = service.getStudents();
		assertEquals("Student1", all.get(0).getName());
	}

	@Test
	public void addStudentHasNewId() {
		final StudentService service = new StudentService(repository);
		final Student created = service.addStudent(new NewStudent("Student1", "1-2-3", "IP"));
		final Student created2 = service.addStudent(new NewStudent("Student1", "1-2-3", "IP"));
		assertNotEquals(created.getId(), created2.getId());
		assertEquals(2, service.getStudents().size());
	}
	
	@After
	public void cleanAfterTest() {
		this.repository.deleteAll();
	}
	
	
	
	
	
}




























