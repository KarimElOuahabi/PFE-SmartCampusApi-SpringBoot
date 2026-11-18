package com.school.controllers;

import com.school.dao.BaseUserRepository;
import com.school.dao.ReclamationRepository;
import com.school.dtos.ReclamationDto;
import com.school.entities.Reclamation;
import com.school.enumeration.ReclamationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/reclamations")
public class ReclamationController {

    @Autowired
    ReclamationRepository reclamationRepository;
    @Autowired
    private BaseUserRepository baseUserRepository;

    @GetMapping
    public List<Reclamation> getReclamations() {
        List<Reclamation> reclamations = reclamationRepository.findAll();
        return reclamations;
    }

    @GetMapping("{reclamationId}")
    public ReclamationDto getReclamationById(@PathVariable Integer reclamationId) {
        Reclamation reclamation = reclamationRepository.findById(reclamationId).orElseThrow();

        ReclamationDto dto = new ReclamationDto();
        dto.setId(reclamation.getId());
        dto.setSenderId(reclamation.getSender() != null ? reclamation.getSender().getId() : null);
        dto.setDescription(reclamation.getDescription());
        dto.setResponse(reclamation.getResponse());
        dto.setReclamationContext(reclamation.getReclamationContext());
        dto.setStatus(reclamation.getStatus());

        return dto;
    }

    @GetMapping("baseuser/{baseUserId}")
    public List<Reclamation> getReclamationBySenderId(@PathVariable Integer baseUserId) {
        return reclamationRepository.findAllBySender_Id(baseUserId);
    }

    @PostMapping
    public Reclamation addReclamation(@RequestBody ReclamationDto reclamationDto) {
        Reclamation reclamation = new Reclamation();
        reclamation.setSender(baseUserRepository.getById(reclamationDto.getSenderId()));
        reclamation.setDescription(reclamationDto.getDescription());
        reclamation.setResponse(reclamationDto.getResponse());
        reclamation.setStatus(ReclamationStatus.Pending);
        reclamation.setReclamationContext(reclamationDto.getReclamationContext());

        return reclamationRepository.save(reclamation);
    }

    @PutMapping("/{reclamationId}")
    public Reclamation updateReclamation(@RequestBody ReclamationDto reclamationDto, @PathVariable Integer reclamationId) {
        Reclamation existing = reclamationRepository.findById(reclamationId).get();
        existing.setSender(baseUserRepository.getById(reclamationDto.getSenderId()));
        existing.setDescription(reclamationDto.getDescription());
        existing.setResponse(reclamationDto.getResponse());
        existing.setReclamationContext(reclamationDto.getReclamationContext());
        existing.setStatus(reclamationDto.getStatus());

        return reclamationRepository.save(existing);
    }


    @DeleteMapping("/{id}")
    public void deleteReclamation(@PathVariable Integer id) {
        reclamationRepository.deleteById(id);
    }

}