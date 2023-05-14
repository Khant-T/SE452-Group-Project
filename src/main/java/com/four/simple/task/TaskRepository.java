package com.four.simple.task;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository
    extends JpaRepository<Task, Long>
{
    Task findById(long id);
    
    // List<Task> findByListId(long listId);

    // List<Task> findByStatusId(long statusId);
}
 