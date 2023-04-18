package com.four.simple.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.four.simple.models.Workspace;

/*
 * Test WorkspaceRepository:
 * 
 *  - CREATE
 *  - READ
 *  - UPDATE
 *  - DELETE  
 */

@SpringBootTest
public class WorkspaceTest {
    @Autowired
    private WorkspaceRepository workspaceRepo;

    @Test
    public void testCRUD()
    {
        // Workspaces 100, 115 already exist on the database

        // test CREATE
        Long count = workspaceRepo.count();

        Workspace workspaceB = new Workspace(200L, "Workspace B", 0L);
        Workspace workspaceC = new Workspace(300L, "Workspace C", 0L);
        workspaceRepo.saveAll(List.of(workspaceB, workspaceC));

        assertEquals(count + 2, workspaceRepo.count());

        // test READ
        Workspace workspace = workspaceRepo.findById(100L);
        assertEquals(true, workspace != null);
        assertEquals(100L, workspace.getId());
        assertEquals("Marketing Space", workspace.getName());

        // test UPDATE
        workspace = workspaceRepo.findById(115L);
        workspace.setName("Random Workspace");
        workspaceRepo.save(workspace);

        Workspace updated = workspaceRepo.findById(115L);
        assertEquals("Random Workspace", updated.getName());

        // test DELETE
        workspaceRepo.deleteById(115L);
        assertEquals(false, workspaceRepo.existsById(115L));
    }

    /*
     * Find work space with user's id.
     */
    @Test
    public void testFindWorkSpaceWUid(){
        assertEquals(3, workspaceRepo.findByUserId(0L).size());
    }

    /* Find Workspace with its Workspace ID 100L. */
    @Test
    public void testFindWorkspaceId(){
        Workspace ws100=workspaceRepo.findById(100L);
        assertEquals("Marketing Space",ws100.getName());
    }


    /* Updated a new work space name with ID 100L */
    @Test
    public void testUpdateSpace(){

        Workspace ws600=workspaceRepo.findById(115L);
        ws600.setName("Computer Science");
        workspaceRepo.save(ws600);

        assertEquals("Computer Science",workspaceRepo.findById(115L).getName());
    }

    /* Deleted Entired Work space */
    @Test
    public void testDeleteWorkspace(){
        Workspace ws=workspaceRepo.findById(120L);
        workspaceRepo.delete(ws);
        assertEquals(false,workspaceRepo.existsById(120L));
    }

    /* Deleted work space by space id */
    @Test
    public void testDeleteWSbyID(){
        workspaceRepo.deleteById(125L);
        assertEquals(false,workspaceRepo.existsById(125L));
    }
}

