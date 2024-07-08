package com.irs.tutoriales.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TutorialDto {
    
    private long id;

    private String title;

    private String description;

    private boolean published;
}
