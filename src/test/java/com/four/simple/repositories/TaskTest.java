package com.four.simple.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.four.simple.task.Task;
import com.four.simple.task.TaskRepository;

/*
 * Test TaskRepository:
 * 
 *  - CREATE
 *  - READ
 *  - UPDATE
 *  - DELETE
 *  - findByListId
 */

@SpringBootTest
public class TaskTest {
    @Autowired
    private TaskRepository taskRepo;

    @Test
    public void testCRUD() {
        long count = taskRepo.count();

        // test CREATE
        Task task = new Task();
        task.setDescription("New task");
        task.setId(30);
        task.setListId(1L);
        task.setStatusId(1L);
        taskRepo.save(task);

        assertEquals(count + 1, taskRepo.count());

        // test READ
        task = taskRepo.findById(401L);
        assertEquals(true, task != null);
        assertEquals(401L, task.getId());
        assertEquals("Schedule an appointment with S Supermarket", task.getDescription());

        // test UPDATE
        task = taskRepo.findById(401L);
        task.setDescription("Testing Task");
        taskRepo.save(task);

        Task updated = taskRepo.findById(401L);
        assertEquals("Testing Task", updated.getDescription());

        // test DELETE
        taskRepo.deleteById(401L);
        assertEquals(false, taskRepo.existsById(401L));
    }


    @Test
    public void testFindByListId() {
        List<Task> tasksL1 = taskRepo.findByListId(202L);
        assertEquals(4, tasksL1.size());

        List<Task> tasksL2 = taskRepo.findByListId(201L);
        assertEquals(3, tasksL2.size());
    }

    @Test
    public void testFindByStatusId() {
        List<Task> tasksS1 = taskRepo.findByStatusId(351L);
        assertEquals(2, tasksS1.size());

        List<Task> tasksS2 = taskRepo.findByStatusId(303L);
        assertEquals(1, tasksS2.size());
    }
}
