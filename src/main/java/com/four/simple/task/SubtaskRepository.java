package com.four.simple.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtaskRepository 
    extends JpaRepository<Subtask, Long> {
   
    Subtask findById(long id);

    List<Subtask> findByTaskId(long task_id);

    boolean existsByTaskId(long task_id);
}
