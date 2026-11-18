package com.school.dao;

import com.school.entities.Mark;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
    List<Mark> findAllByStudent_id(Integer studentId);

    List<Mark> findAllBySubject_id(Integer subjectId);
}
