package com.irs.tutoriales.web.controllers;

import com.irs.tutoriales.business.dto.TutorialDto;
import com.irs.tutoriales.business.services.TutorialService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:5173" })
@AllArgsConstructor
public class TutorialController {

    private final TutorialService tutorialService;

    @GetMapping("/tutoriales")
    public ResponseEntity<List<TutorialDto>> getAllTutorials(@RequestParam(required = false) String title) {
        List<TutorialDto> tutoriales;
        
        if (title == null) {
            tutoriales = tutorialService.findAll();
        } else {
            tutoriales = tutorialService.findByTitle(title);
        }
        
        if (tutoriales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(tutoriales, HttpStatus.OK);
    }

    @GetMapping("/tutoriales/{id}")
    public ResponseEntity<TutorialDto> getTutorialById(@PathVariable("id") long id) {
        TutorialDto tutorial = tutorialService.findById(id);
        
        return new ResponseEntity<>(tutorial, HttpStatus.OK);
    }

    @PostMapping("/tutoriales")
    public ResponseEntity<TutorialDto> createTutorial(@RequestBody TutorialDto tutorial) {
        TutorialDto tutorialSaved = tutorialService.save(tutorial);
        
        return new ResponseEntity<>(tutorialSaved, HttpStatus.CREATED);
    }

    @PutMapping("/tutoriales/{id}")
    public ResponseEntity<TutorialDto> updateTutorial(@PathVariable("id") long id, @RequestBody TutorialDto tutorial) {
        TutorialDto tutorialUpdated = tutorialService.update(id, tutorial);
        
         return new ResponseEntity<>(tutorialUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/tutoriales/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        tutorialService.delete(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutoriales")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
         tutorialService.deleteAll();
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tutoriales/publicados")
    public ResponseEntity<List<TutorialDto>> findByPublished() {
        List<TutorialDto> tutoriales = tutorialService.findByPublished();
        
        if (tutoriales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(tutoriales, HttpStatus.OK);
    }
}
