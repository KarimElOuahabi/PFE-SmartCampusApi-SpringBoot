package com.school.dao;

import com.school.entities.ADRequest;
import com.school.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Integer> {
    List<Reclamation> findAllBySender_Id(Integer senderId);
}
