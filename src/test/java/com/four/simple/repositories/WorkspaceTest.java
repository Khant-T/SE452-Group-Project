package com.four.simple.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

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

    // @Test
    // public void testFindWorkSpaceWUid(){
    //     Long count = workspaceRepo.count();

    //     Workspace workspaceB = new Workspace(200L, "Workspace B", 0L);
    //     Workspace workspaceC = new Workspace(300L, "Workspace C", 0L);
    //     workspaceRepo.saveAll(List.of(workspaceB, workspaceC));

    //     /*
    //      * We have 4 datat in Repo, after delete 115L 
    //      * the size suppose to be 3, but we got 5 after.
    //      */
    //     assertEquals(5, workspaceRepo.findByUserId(0L).size());
    // }
}

