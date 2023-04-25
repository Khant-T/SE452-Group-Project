package com.four.simple.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.four.simple.models.Status;

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
public class StatusTest {
    @Autowired
    private StatusRepository statusRepo;

    @Test
    public void testCreate(){
        long count = statusRepo.count();
        Status status = new Status(0L,"Delayed", 203L);

        statusRepo.save(status);
        assertEquals(count+1, statusRepo.count());
        
    }

    @Test
    public void testRead(){
        Status status = statusRepo.findById(301L);
        assertEquals(true, status != null);
        assertEquals("To-do", status.getDescription());
        assertEquals(201, status.getListId());
    }

    @Test
    public void testUpdate(){
        long count = statusRepo.count();
        Status statu1=new Status(600L,"Delay",2L);

        statusRepo.save(statu1);
        assertEquals(count+1, statusRepo.count());
        
    }

    @Test
    public void testDelete() {
        Status deleted = statusRepo.findById(301L);
        statusRepo.delete(deleted);
        assertEquals(false, statusRepo.existsById(301L));
    }

    @Test
    public void testFindByListId(){
        assertEquals(2, statusRepo.findByListId(202L).size());
    }

    @Test
    public void testExistsByListId(){
        assertEquals(true, statusRepo.existsByListId(202L));
    }
}
