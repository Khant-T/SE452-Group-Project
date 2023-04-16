package com.four.simple.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.four.simple.models.Task;




/*
 * Test TaskRepository:
 * 
 *  - CREATE
 *  - READ
 *  - UPDATE
 *  - DELETE
 *  - findByListId()
 */

@SpringBootTest
public class TaskTest {
    @Autowired
    private TaskRepository taskRepo;

    @Test
    public void testFindByListId()
    {
        List<Task> tasksL1 = taskRepo.findByListId(1L);
        assertEquals(3, tasksL1.size());

        List<Task> tasksL2 = taskRepo.findByListId(2L);
        assertEquals(4, tasksL2.size());

         // test READ
         Task task = taskRepo.findById(1L).get();
         assertEquals(true, task != null);
         assertEquals(1L, task.getId());
         assertEquals("Schedule an appointment with S Supermarket", task.getDescription());

          // test UPDATE
        task = taskRepo.findById(1L).get();
        task.setDescription("Testing Task");
        taskRepo.save(task);

        Task updated = taskRepo.findById(1L).get();
        assertEquals("Testing Task", updated.getDescription());

        // test DELETE
        taskRepo.deleteById(1L);
        Optional<Task> deleted = taskRepo.findById(1L);
        assertEquals(false, deleted.isPresent());

    }
}

