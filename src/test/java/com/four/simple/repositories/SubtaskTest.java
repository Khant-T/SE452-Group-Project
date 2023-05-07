package com.four.simple.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.four.simple.task.Subtask;
import com.four.simple.task.SubtaskRepository;

/*
 * Test SubtaskRepository:
 * 
 *  - CREATE
 *  - READ
 *  - UPDATE
 *  - DELETE
 *  - findByListId
 */

@SpringBootTest
public class SubtaskTest {
    @Autowired
    private SubtaskRepository subtaskRepo;

    @Test
    public void testCreate() {
        long count=subtaskRepo.count();
        Subtask sb=new Subtask();

        sb.setId(600L);
        sb.setDescription("Day 4. Write Subtask test.");
        sb.setTaskId(4L);
        sb.setCompleted(0);

        subtaskRepo.save(sb);
        assertEquals(count+1, subtaskRepo.count());
    }

    @Test 
    public void testRead() {
        Subtask sb = subtaskRepo.findById(501L);
        assertEquals(true, sb != null);
        assertEquals("Reach out to marketing team of S Supermarket", sb.getDescription());
        assertEquals(401, sb.getTaskId());
        assertEquals(0, sb.getCompleted());
    }

    @Test
    public void testUpdate() {
        Subtask sb = subtaskRepo.findById(502L);
        sb.setDescription("Day 0. Testing Update function");
        subtaskRepo.save(sb);

        Subtask updated = subtaskRepo.findById(502L);

        assertEquals("Day 0. Testing Update function", updated.getDescription());
    }

    @Test
    public void testDelete(){
        subtaskRepo.deleteById(501L);
        assertEquals(false,subtaskRepo.existsById(501L));
    }

    @Test
    public void testFindByTaskId(){
        assertEquals(3,subtaskRepo.findByTaskId(402).size());
    }

    @Test
    public void testExistsByTaskId(){
        assertEquals(true, subtaskRepo.existsByTaskId(401));
    }
}
