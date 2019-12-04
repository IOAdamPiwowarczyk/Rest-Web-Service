package pl.example.spring.punkty.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String hello() {
		String str = "hello, time is " + LocalDateTime.now();
		return str;
	}

}
