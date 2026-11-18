package com.school.controllers;

import com.school.dao.ADRequestRepository;
import com.school.dao.BaseUserRepository;
import com.school.dtos.AdRequestDto;
import com.school.entities.ADRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/adrequests")
public class ADRequestController {

    @Autowired
    private ADRequestRepository adRequestRepository;
    @Autowired
    private BaseUserRepository baseUserRepository;

    // GET all departments
    @GetMapping
    public List<ADRequest> getADrequests() {
        List<ADRequest> adRequests = adRequestRepository.findAll();
        return adRequests;
    }
    @GetMapping("{adRequestId}")
    public AdRequestDto getADRequestById(@PathVariable Integer adRequestId) {
        ADRequest adRequest = adRequestRepository.findById(adRequestId).orElseThrow();

        AdRequestDto dto = new AdRequestDto();
        dto.setId(adRequest.getId());
        dto.setType(adRequest.getType());
        dto.setHandlerComment(adRequest.getHandlerComment());
        dto.setRequesterComment(adRequest.getRequesterComment());
        dto.setStatus(adRequest.getStatus());
        dto.setRequesterId(adRequest.getRequester() != null ? adRequest.getRequester().getId() : null);
        dto.setHandlerId(adRequest.getHandler() != null ? adRequest.getHandler().getId() : null);

        return dto;
    }

    @GetMapping("baseuser/{baseUserId}")
    public List<ADRequest> getADRequestByRequesterId(@PathVariable Integer baseUserId) {
        return adRequestRepository.findAllByRequester_Id(baseUserId);
    }

    @PostMapping
    public ADRequest createADRequest(@RequestBody AdRequestDto adRequestDto) {
        ADRequest adRequest = new ADRequest();
        adRequest.setRequester(baseUserRepository.getById(adRequestDto.getRequesterId()));
        adRequest.setRequesterComment(adRequestDto.getRequesterComment());
        adRequest.setHandlerComment(adRequestDto.getHandlerComment());
        adRequest.setType(adRequestDto.getType());
        adRequest.setStatus(adRequestDto.getStatus());
        return adRequestRepository.save(adRequest);
    }

    @PutMapping("admin/{adRequestId}")
    public ADRequest updateADRequest(@RequestBody AdRequestDto adRequestDto, @PathVariable Integer adRequestId) {
        System.out.println(adRequestDto.getRequesterId());
        ADRequest adRequest = adRequestRepository.findById(adRequestId).get();
        adRequest.setHandler(baseUserRepository.getById(adRequestDto.getHandlerId()));
        adRequest.setRequesterComment(adRequestDto.getRequesterComment());
        adRequest.setHandlerComment(adRequestDto.getHandlerComment());
        adRequest.setType(adRequestDto.getType());
        adRequest.setStatus(adRequestDto.getStatus());
        return adRequestRepository.save(adRequest);
    }

    @PutMapping("other/{adRequestId}")
    public ADRequest updateADRequestByOther(@RequestBody AdRequestDto adRequestDto, @PathVariable Integer adRequestId) {
        ADRequest adRequest = adRequestRepository.findById(adRequestId).orElseThrow();
        adRequest.setRequesterComment(adRequestDto.getRequesterComment());
        adRequest.setType(adRequestDto.getType());

        return adRequestRepository.save(adRequest);
    }

    @DeleteMapping("{adRequestId}")
    public void deleteADRequest(@PathVariable Integer adRequestId) {
        adRequestRepository.deleteById(adRequestId);
    }


}
