package com.school.dao;

import com.school.entities.ADRequest;
import com.school.entities.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ADRequestRepository extends JpaRepository<ADRequest, Integer> {
    List<ADRequest> findAllByRequester_Id(Integer requesterId);
}
