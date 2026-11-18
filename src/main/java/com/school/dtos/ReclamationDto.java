package com.school.dtos;

import com.school.enumeration.ReclamationContextEnum;
import com.school.enumeration.ReclamationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReclamationDto {
    private Integer id;
    private Integer senderId;
    private String description;
    private String response;
    private ReclamationContextEnum reclamationContext;
    private ReclamationStatus status;
}

