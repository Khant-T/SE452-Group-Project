package com.four.simple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.four.simple.models.Subtask;

@Repository
public interface SubtaskRepository 
    extends JpaRepository<Subtask, Long> {
   
    Subtask findById(long id);

    List<Subtask> findByTaskId(long task_id);

    Subtask save(Subtask subtask);

    void delete(Subtask subtask);

    void deleteById(long id);

    boolean existsById(long id);

    boolean existsByTaskId(long task_id);

    

}
