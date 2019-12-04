package pl.example.spring.punkty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.example.spring.punkty.entity.StudentRow;

public interface StudentRepository extends JpaRepository<StudentRow, Long>{

}
