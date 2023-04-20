package com.four.simple.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.four.simple.models.Subtask;

@SpringBootTest
public class SubtaskTest {
    @Autowired
    private SubtaskRepository subtaskRepo;

    /* Test Create subtask */
    @Test
    public void testCreate(){
        long count=subtaskRepo.count();
        Subtask sb=new Subtask();

        sb.setId(600L);
        sb.setDescription("Day 4. Write Subtask test.");
        sb.setTaskId(4L);
        sb.setCompleted(0);

        subtaskRepo.save(sb);
        assertEquals(count+1, subtaskRepo.count());
    }

    /* Test Reading subtask */
    @Test 
    public void testReading(){
        Subtask sb=new Subtask();
        sb=subtaskRepo.findById(100L);
        assertEquals(true, sb != null);
        assertEquals("Reach out to marketing team of S Supermarket",sb.getDescription());
        assertEquals(1,sb.getTaskId());
        assertEquals(0,sb.getCompleted());
    }

    /* Test Updating subtask*/
    @Test
    public void testUpdaing(){
        Subtask sb=new Subtask();
        sb=subtaskRepo.findById(200L);
        sb.setDescription("Day 0. Testing Update function");
        subtaskRepo.save(sb);

        Subtask nupdate=new Subtask();
        nupdate=subtaskRepo.findById(200L);

        assertEquals("Day 0. Testing Update function",nupdate.getDescription());
    }

    /* Test Deleting subtask */
    @Test
    public void testDeleting(){
        subtaskRepo.deleteById(100L);
        assertEquals(false,subtaskRepo.existsById(100L));
    }

    /* Test find by ID Day 1. Brand Strategy by Dr. A	 */
    @Test
    public void testFindByID(){
        Subtask sb=new Subtask();
        sb=subtaskRepo.findById(300L);
        assertEquals("Day 1. Brand Strategy by Dr. A",sb.getDescription());
    }

    /* Test list of Subtask by task_id */
    @Test
    public void testFindByTID(){
        assertEquals(3,subtaskRepo.findByTaskId(3L).size());
    }

    /* Test save Subtask */
    @Test
    public void testSave(){
        Subtask sb=new Subtask();
        sb=subtaskRepo.findById(200L);

        sb.setCompleted(1);
        sb.setDescription("Day 0. Testting Save new Subtask.");
        subtaskRepo.save(sb);
        sb=subtaskRepo.findById(200L);
        assertEquals(1,sb.getCompleted());
        assertEquals("Day 0. Testting Save new Subtask.",sb.getDescription());
    }
    
    /* Test delete subtask */
    @Test
    public void testDeleteTask(){
        Subtask sb=new Subtask();
        sb.setCompleted(1);
        sb.setDescription("Day 5. Set new task");
        sb.setTaskId(2);
        sb.setId(100L);
        subtaskRepo.save(sb);
        subtaskRepo.delete(sb);

    }

    /* Test exists by id */
    @Test
    public void testExistsById(){
        assertEquals(true, subtaskRepo.existsById(300L));
    }

    /* Test exists by task_id */
    @Test
    public void testExistsByTid(){
        assertEquals(true, subtaskRepo.existsByTaskId(3L));
    }
}
