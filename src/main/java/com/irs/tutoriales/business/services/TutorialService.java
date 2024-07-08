package com.irs.tutoriales.business.services;

import com.irs.tutoriales.business.dto.TutorialDto;
import java.util.List;

public interface TutorialService {
    
    List<TutorialDto> findAll();
    
    List<TutorialDto> findByTitle(final String title);
    
    List<TutorialDto> findByPublished();
	
    TutorialDto findById(final Long id);
	
    TutorialDto save(TutorialDto tutorial);
		
    TutorialDto update(Long id, TutorialDto tutorial);
	
    void delete(Long id);
    
    void deleteAll();
}
