package com.irs.tutoriales.business.services.impl;

import com.irs.tutoriales.business.dto.TutorialDto;
import com.irs.tutoriales.business.mappers.Mapper;
import com.irs.tutoriales.business.services.TutorialService;
import com.irs.tutoriales.data.entities.Tutorial;
import com.irs.tutoriales.data.repositories.TutorialRepository;
import com.irs.tutoriales.exceptions.ResourceNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TutorialServiceImpl implements TutorialService {

    private final TutorialRepository tutorialRepository;
    
    private final Mapper<Tutorial, TutorialDto> tutorialMapper;
    
    @Override
    public List<TutorialDto> findAll() {
        List<Tutorial> tutoriales = tutorialRepository.findAll();
        
        return tutoriales.stream().map(tutorialMapper::toTarget).toList();
    }

    @Override
    public List<TutorialDto> findByTitle(String title) {
        List<Tutorial> tutoriales = tutorialRepository.findByTitleContaining(title);
        
        return tutoriales.stream().map(tutorialMapper::toTarget).toList();
    }

    @Override
    public List<TutorialDto> findByPublished() {
        List<Tutorial> tutoriales = tutorialRepository.findByPublished(true);
        
        return tutoriales.stream().map(tutorialMapper::toTarget).toList();
    }

    @Override
    public TutorialDto findById(Long id) {
        return tutorialRepository.findById(id)
                .map(tutorialMapper::toTarget)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Tutorial '%d' no encontrado", id)));
    }

    @Override
    public TutorialDto save(TutorialDto tutorial) {
        Tutorial tutorialSaved = tutorialRepository.save(tutorialMapper.toSource(tutorial));
        
        return tutorialMapper.toTarget(tutorialSaved);
    }

    @Override
    public TutorialDto update(Long id, TutorialDto tutorial) {
        Tutorial tutorialUpdate = tutorialMapper.toSource(findById(id));
        tutorialUpdate.setTitle(tutorial.getTitle());
        tutorialUpdate.setDescription(tutorial.getDescription());
        tutorialUpdate.setPublished(tutorial.isPublished());

	Tutorial tutorialUpdated = tutorialRepository.save(tutorialUpdate);
        return tutorialMapper.toTarget(tutorialUpdated);
        
    }

    @Override
    public void delete(Long id) {
        tutorialRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tutorialRepository.deleteAll();
    }
 
}
