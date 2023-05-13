package com.four.simple.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.four.simple.checklist.Checklist;
import com.four.simple.checklist.ChecklistRepository;

/*
 * Test SubtaskRepository:
 * 
 *  - CREATE
 *  - READ
 *  - UPDATE
 *  - DELETE
 *  - findByWorkspaceId
 */

@SpringBootTest
public class ChecklistTest {
    @Autowired
    private ChecklistRepository checklistRepo;

    @Test
    public void testCreate() {
        Long count = checklistRepo.count();
        Checklist list = new Checklist(0L, "Another list", 300L);
        checklistRepo.save(list);
        assertEquals(count + 1, checklistRepo.count());
    }

    @Test
    public void testRead() {
        Checklist list = checklistRepo.findById(201L);
        assertEquals(true, list != null);
        assertEquals("Marketing To-dos", list.getName());
        assertEquals(101, list.getWorkspaceId());
    }

    @Test
    public void testUpdate() {
        Checklist list = checklistRepo.findById(201L);
        list.setName("Marketing To-do List");
        checklistRepo.save(list);

        Checklist updated = checklistRepo.findById(201L);

        assertEquals("Marketing To-do List", updated.getName());
    }
   
    @Test
    public void testDelete() {
        Checklist list = checklistRepo.findById(205L);
        checklistRepo.delete(list);
        assertEquals(false, checklistRepo.existsById(205L));
    }

    @Test
    public void testFindByWorkspaceId(){
        assertEquals(2, checklistRepo.findByWorkspaceId(101L).size());
    }
}
