package com.four.simple.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.four.simple.workspace.Workspace;
import com.four.simple.workspace.WorkspaceRepository;

/*
 * Test WorkspaceRepository:
 * 
 *  - CREATE
 *  - READ
 *  - UPDATE
 *  - DELETE
 *  - findById
 *  - findByUserId
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

        // Workspace workspaceB = new Workspace(0L, "Workspace B", 0L);
        // Workspace workspaceC = new Workspace(0L, "Workspace C", 0L);
        Workspace workspaceB = Workspace.builder().name("Workspace B").userId(0L).build();
        Workspace workspaceC = Workspace.builder().name("Workspace C").userId(0L).build();
        workspaceRepo.saveAll(List.of(workspaceB, workspaceC));

        assertEquals(count + 2, workspaceRepo.count());

        // test READ
        Workspace workspace = workspaceRepo.findById(101);
        assertEquals(true, workspace != null);
        assertEquals(101, workspace.getId());
        assertEquals("Marketing Space", workspace.getName());

        // test UPDATE
        workspace = workspaceRepo.findById(102L);
        workspace.setName("Random Workspace");
        workspaceRepo.save(workspace);

        Workspace updated = workspaceRepo.findById(102L);
        assertEquals("Random Workspace", updated.getName());

        // test DELETE
        workspaceRepo.deleteById(102L);
        assertEquals(false, workspaceRepo.existsById(102L));
    }

    @Test
    public void testFindByUserId(){
        assertEquals(3, workspaceRepo.findByUserId(0L).size());
    }

    @Test
    public void testFindById(){
        Workspace workspace = workspaceRepo.findById(101L);
        assertEquals("Marketing Space", workspace.getName());
    }
}

