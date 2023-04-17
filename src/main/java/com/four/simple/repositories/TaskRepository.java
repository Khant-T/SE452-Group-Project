package com.four.simple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.four.simple.models.Task;



@Repository
public interface TaskRepository
    extends JpaRepository<Task, Long>
{
    List<Task> findByListId(long listId);

    List<Task> findByStatusId(long statusId);

    Task save(Task task);

    void delete(Task task);

    void deleteById(long listId);

    void existsById(long listId);
    
}
 