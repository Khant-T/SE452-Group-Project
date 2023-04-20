package com.four.simple.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.four.simple.models.Checklist;

@SpringBootTest
public class ChecklistTest {
    @Autowired
    private ChecklistRepository checklistRepo;

    /* Test Creating */
    @Test
    public void testCreat(){
        Long count=checklistRepo.count();
        Checklist list1=new Checklist(600L, "Adding id 600L ", 300);
        checklistRepo.save(list1);
        assertEquals(count+1,checklistRepo.count());
    }

    /* Test Checklist find by id */
    @Test
    public void testFindById(){
        Checklist cl=checklistRepo.findById(100L);
        assertEquals("Marketing To-dos",cl.getName());
    }

    /* Test list of check list find by worskspace id */
    @Test
    public void testFindByWorkspaceId(){
       
        assertEquals(2L,checklistRepo.findByWorkspaceId(200L).size());
    
    }

    /* Test check list updating new Checklist */
    @Test
    public void testSave(){
        Checklist cl=checklistRepo.findById(200L);
        cl.setName("Brianstoming on Simple App");
        cl.setWorkspaceId(300L);
        checklistRepo.save(cl);
        assertEquals("Brianstoming on Simple App",checklistRepo.findById(200L).getName());

    }

    /* Test delete on entire checklist */
    @Test
    public void testDelete(){
        Checklist cl=checklistRepo.findById(100L);
        checklistRepo.delete(cl);
        assertEquals(false, checklistRepo.existsById(100L));
    }

    /* Test delete by id */
    @Test
    public void testDeleteById(){
        checklistRepo.deleteById(200L);
        assertEquals(false,checklistRepo.existsById(200L));
    }

    /* Test existst by id */
    @Test
    public void testExistsById(){
        assertEquals(true,checklistRepo.existsById(300L));
    }

    /* Test existst by workspace id */
    @Test
    public void testExistsByWorkspaceId(){
        assertEquals(true,checklistRepo.existsByWorkspaceId(200L));
    }
}
