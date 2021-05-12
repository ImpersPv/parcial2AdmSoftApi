package com.grokonez.jwtauthentication.controller;

//import com.example.ng5api.exception.ResourceNotFoundException;
import com.grokonez.jwtauthentication.model.Imc;
import com.grokonez.jwtauthentication.repository.ImcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin()
@RestController
//@RequestMapping("/api/auth")

public class ImcController {

    @Autowired
    private ImcRepository imcRepository;

    @GetMapping("/imcs")
    public Page<Imc> getImcs(Pageable pageable) {
        return imcRepository.findAll(pageable);
    }


    @PostMapping("/imcs")
    public Imc createImc(@Valid @RequestBody Imc imc) {
        return imcRepository.save(imc);
    }
/*
    @PutMapping("/questions/{questionId}")
    public Question updateQuestion(@PathVariable Long questionId,
                                   @Valid @RequestBody Question questionRequest) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    question.setTitle(questionRequest.getTitle());
                    question.setDescription(questionRequest.getDescription());
                    return questionRepository.save(question);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }


    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    questionRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }
    */
}