package com.school.dtos;

import com.school.enumeration.ADRequestStatusEnum;
import com.school.enumeration.AdministrativeDocumentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdRequestDto {
    private Integer id;
    private AdministrativeDocumentType type;
    private String handlerComment;
    private String requesterComment;
    private ADRequestStatusEnum status;
    private Integer requesterId;
    private Integer handlerId;
}
