package com.irs.tutoriales.business.mappers;

import com.irs.tutoriales.business.dto.TutorialDto;
import com.irs.tutoriales.data.entities.Tutorial;
import org.springframework.stereotype.Component;

@Component("tutorialMapper")
public class TutorialMapper implements Mapper<Tutorial, TutorialDto> {

    public TutorialMapper() {
        super();
    }
    
    @Override
    public Tutorial toSource(TutorialDto target) {
        Tutorial source = null;
        
        if (target != null) {
            source = Tutorial.builder()
                    .id(target.getId())
                    .title(target.getTitle())
                    .description(target.getDescription())
                    .published(target.isPublished())
                    .build();
        }
        
        return source;
    }

    @Override
    public TutorialDto toTarget(Tutorial source) {
         TutorialDto target = null;
        
        if (source != null) {
            target = TutorialDto.builder()
                    .id(source.getId())
                    .title(source.getTitle())
                    .description(source.getDescription())
                    .published(source.isPublished())
                    .build();
        }
        
        return target;
    }
    
}
