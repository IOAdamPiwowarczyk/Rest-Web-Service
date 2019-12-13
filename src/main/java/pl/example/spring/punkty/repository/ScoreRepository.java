package pl.example.spring.punkty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.example.spring.punkty.entity.ScoreRow;

public interface ScoreRepository extends JpaRepository<ScoreRow, Long> {

}
