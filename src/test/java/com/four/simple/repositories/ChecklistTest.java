 package com.four.simple.repositories;

 import static org.junit.jupiter.api.Assertions.assertEquals;

 import com.four.simple.checklist.Status;
 import com.four.simple.task.Task;
 import org.junit.jupiter.api.Test;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.context.SpringBootTest;

 import java.util.List;
 import com.four.simple.checklist.Checklist;
 import com.four.simple.checklist.ChecklistRepository;
 import com.four.simple.workspace.Workspace;
 import org.springframework.test.context.ActiveProfiles;

 /*
  * Test ChecklistRepository:
  *
  *  - CREATE
  *  - READ
  *  - UPDATE
  *  - DELETE
  *  -
  */

 @SpringBootTest
 @ActiveProfiles("test")
 public class ChecklistTest {
     @Autowired
     private ChecklistRepository checklistRepo;


     @Test
     public void testCRUD() {
         long originalCount = checklistRepo.count();

         //Test CREATE
         Workspace wsF = Workspace.builder().name("Workspace F").build();
         Workspace wsJ = Workspace.builder().name("Workspace J").build();

         Checklist checklistA=new Checklist();
         checklistA.setName("TEST CHECKLIST A");
         checklistA.setWorkspace(wsF);
         Checklist checklistB=new Checklist();
         checklistB.setName("TEST CHECKLIST B");
         checklistB.setWorkspace(wsJ);
         checklistRepo.saveAll(List.of(checklistA,checklistB));
         assertEquals(checklistRepo.count(),originalCount+2);

         //Get IDs of newly created Checklist objects
         long checklistAId = checklistA.getId();
         long checklistBId = checklistB.getId();

         //Test READ
         Checklist checklistRead=checklistRepo.findById(checklistBId);

         if(checklistRead == null){
             checklistRead = new Checklist();
         }

         assertEquals(true,checklistRead != null);
         assertEquals("TEST CHECKLIST B",checklistRead.getName());

         //Test UPDATE
         Checklist checklistBUpdate=checklistRead;
         checklistBUpdate.setName("TEST CHECKLIST B, UPDATED");
         checklistRepo.save(checklistBUpdate);

         checklistBUpdate = checklistRepo.findById(checklistBId);
         assertEquals("TEST CHECKLIST B, UPDATED",checklistBUpdate.getName());

         //Test DELETE
         checklistRepo.deleteById(checklistAId);
         assertEquals(false,checklistRepo.existsById(checklistAId));

     }
 }
