package edu.fbansept.demo.dao;

import edu.fbansept.demo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionDAO extends JpaRepository<Question, Integer> {
}
