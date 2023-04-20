package com.four.simple.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.four.simple.models.Status;

@SpringBootTest
public class StatusTest {
    @Autowired
    private StatusRepository statusRepo;


    /* Test create new Status */
    @Test
    public void testCreat(){
        long count = statusRepo.count();
        Status statu1=new Status(600L,"Delay",2L);

        statusRepo.save(statu1);
        assertEquals(count+1, statusRepo.count());
        
    }
    
    /* Test find by Id  */
    @Test
    public void testFindById(){
        assertEquals("To-do",statusRepo.findById(100L).getDescription());
    }


    /* Test find by list id */
    @Test
    public void testFindByListId(){
        assertEquals(3,statusRepo.findByListId(1L).size());
    }

    /* Test update an exisiting status */
    @Test
    public void testSaveExisitingStatus(){
        Status st=new Status();
        st=statusRepo.findById(400L);
        st.setDescription("Delayed");
        st.setListId(3L);
        statusRepo.save(st);

        assertEquals("Delayed",statusRepo.findById(400L).getDescription());
    }

    /* Test delete */
    @Test
    public void testDelete(){
        Status st=statusRepo.findById(400L);
        statusRepo.delete(st);
        assertEquals(false,statusRepo.existsById(400L));
    }

    /* Test deleteById */
    @Test
    public void testDeleteById(){
        statusRepo.deleteById(500L);
        assertEquals(false,statusRepo.existsById(500L));
    }

    /* Test exists by id */
    @Test
    public void testExiststById(){
        assertEquals(true,statusRepo.existsById(100L));
    }

    /* Test existst by list id */
    @Test
    public void testExistsByListId(){
        assertEquals(true, statusRepo.existsByListId(1L));
    }
}
