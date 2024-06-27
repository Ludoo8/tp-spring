package edu.fbansept.demo.controller;


import edu.fbansept.demo.dao.QuestionDAO;
import edu.fbansept.demo.dao.ReponsePossibleDAO;
import edu.fbansept.demo.model.Question;
import edu.fbansept.demo.model.ReponsePossible;
import edu.fbansept.demo.security.IsAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reponse-possible")
public class ReponsePossibleController {

    @Autowired
    ReponsePossibleDAO reponsePossibleDAO;

    @Autowired
    QuestionDAO questionDAO;

    @GetMapping
    public List<ReponsePossible> getReponsePossible() {
        return reponsePossibleDAO.findAll();
    }

    @IsAdmin
    @PostMapping("/route/{questionID}")
    public ResponseEntity<ReponsePossible> CreateRep(@PathVariable int questionID, @RequestBody ReponsePossible reponsePossible){
        Optional<Question> question = questionDAO.findById(questionID);

        if(question.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            reponsePossible.setTexte(reponsePossible.toString());
            reponsePossible.setEstVrai(reponsePossible.getEstVrai());
            reponsePossible.setQuestion(question.get());
            reponsePossibleDAO.save(reponsePossible);
        }
        return new ResponseEntity<>(reponsePossible, HttpStatus.CREATED);
    }
}
