package com.four.simple.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.four.simple.task.Task;
import com.four.simple.task.TaskRepository;
import org.springframework.test.context.ActiveProfiles;

/*
 * Test TaskRepository:
 *
 *  - CREATE
 *  - READ
 *  - UPDATE
 *  - DELETE
 *
 */

 @SpringBootTest
 @ActiveProfiles("test")
 public class TaskTest
 {
    @Autowired
    private TaskRepository taskRepo;

    @Test
    public void testCRUD()
    {
        long originalCount = taskRepo.count();

        // Test CREATE
        Task taskA = Task.builder().description("Task A").build();
        Task taskB = Task.builder().description("Task B").build();
        taskRepo.saveAll(List.of(taskA, taskB));
        assertEquals(originalCount + 2, taskRepo.count());

        // Get IDs of newly created Task Objects
        long taskAId = taskA.getId();
        long taskBId = taskB.getId();

        // Test READ
        Task taskBRead = taskRepo.findById(taskBId);

        if (taskBRead == null)
            taskBRead = new Task();

        assertEquals(true, taskBRead != null);
        assertEquals("Task B", taskBRead.getDescription());

        // Test UPDATE
        Task taskBUpdate = taskBRead;
        taskBUpdate.setDescription("Task B, Updated");
        taskRepo.save(taskBUpdate);

        taskBUpdate = taskRepo.findById(taskBId);
        assertEquals("Task B, Updated", taskBUpdate.getDescription());

        // test DELETE
        taskRepo.deleteById(taskAId);
        assertEquals(false, taskRepo.existsById(taskAId));
    }
 }
