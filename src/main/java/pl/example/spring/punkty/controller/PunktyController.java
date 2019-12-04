package pl.example.spring.punkty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.example.spring.punkty.model.NewStudent;
import pl.example.spring.punkty.model.Student;
import pl.example.spring.punkty.service.StudentService;

@RestController
@RequestMapping("/punkty")
public class PunktyController {
	
	private final StudentService service;	
	
	@Autowired
	public PunktyController(StudentService service) {
		this.service = service;
	}

	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public List<Student> getUsers() {
		return this.service.getStudents().asJava();
	}
	
	@RequestMapping(value = "/students", method = RequestMethod.POST)
	public Student addUsers(@RequestBody NewStudent theStudent) {
		return service.addStudent(theStudent);
	}
	
	@RequestMapping(value = "/students/{id}/number/{number}", method = RequestMethod.POST)
	public Student setNumber(@PathVariable("id") long id, @PathVariable("number") String number) {
		return service.changeNumber(id, number).orElseThrow(
				() -> new IllegalArgumentException("Student o id: " + id + " does not exist"));
	}

}
