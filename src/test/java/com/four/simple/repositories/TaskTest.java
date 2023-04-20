package com.four.simple.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
        task = taskRepo.findById(101L);
        assertEquals(true, task != null);
        assertEquals(101L, task.getId());
        assertEquals("Schedule an appointment with S Supermarket", task.getDescription());

        // test UPDATE
        task = taskRepo.findById(1L);
        task.setDescription("Testing Task");
        taskRepo.save(task);

        Task updated = taskRepo.findById(1L);
        assertEquals("Testing Task", updated.getDescription());

        // test DELETE
        taskRepo.deleteById(1L);
        Task deleted = taskRepo.findById(1L);
        assertEquals(false, taskRepo.existsById(1L));
    }


    /* Find by list id */
    @Test
    public void testFindByListId() {
        List<Task> tasksL1 = taskRepo.findByListId(100L);
        assertEquals(3, tasksL1.size());

        List<Task> tasksL2 = taskRepo.findByListId(200L);
        assertEquals(4, tasksL2.size());
    }

    /* Testing with find task with status id */
    @Test
    public void testFindByStatusId() {
        List<Task> tasksS3 = taskRepo.findByStatusId(3L);
        assertEquals(1, tasksS3.size());

        List<Task> tasksS4 = taskRepo.findByStatusId(4L);
        assertEquals(2, tasksS4.size());

        List<Task> tasksS5 = taskRepo.findByStatusId(5L);
        assertEquals(2, tasksS5.size());

    }

    /* Test Updaing existing task */
    @Test
    public void testUpdated() {
       // test CREATE
       Task task = new Task();
       task.setDescription("TestUpdated Task");
       task.setId(101L);
       task.setListId(1L);
       task.setStatusId(1L);
       taskRepo.save(task);

       // test UPDATE
       task = taskRepo.findById(101L);
       task.setDescription("After Updated Testing Task");
       taskRepo.save(task);
       Task updated = taskRepo.findById(101L);
       assertEquals("After Updated Testing Task", updated.getDescription());

    }

    /* Test Delete task */
    @Test
    public void testDeleteTask(){
        Task t1=taskRepo.findById(205L);
        taskRepo.delete(t1);
        assertEquals(false, taskRepo.existsById(205L));
    }

    /* Test Delete taks by using id */
    @Test
    public void testDeleteTaskById(){
        taskRepo.deleteById(206L);
        assertEquals(false,taskRepo.existsById(206L));
    }

    /* Test exisiting list id */
    @Test
    public void testExistId(){
        assertEquals(false, taskRepo.existsById(104L));
    }
}
