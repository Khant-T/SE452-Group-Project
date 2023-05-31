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
 *
 */

 @SpringBootTest
 public class WorkspaceTest
 {
    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Test
    public void testCRUD()
    {
        long originalCount = workspaceRepository.count();

        // Test CREATE
        Workspace wsA = Workspace.builder().name("Workspace A").userId(0L).build();
        Workspace wsB = Workspace.builder().name("Workspace B").userId(0L).build();
        workspaceRepository.saveAll(List.of(wsA, wsB));
        assertEquals(originalCount + 2, workspaceRepository.count());

        // Get IDs of newly created Workspace objects
        long wsAId = wsA.getId();
        long wsBId = wsB.getId();

        // Test READ
        Workspace wsBRead = workspaceRepository.findById(wsBId);

        if (wsBRead == null)
            wsBRead = new Workspace();

        assertEquals(true, wsBRead != null);
        assertEquals("Workspace B", wsBRead.getName());

        // Test UPDATE
        Workspace wsBUpdate = wsBRead;
        wsBUpdate.setName("Workspace B, Updated");
        workspaceRepository.save(wsBUpdate);

        wsBUpdate = workspaceRepository.findById(wsBId);
        assertEquals("Workspace B, Updated", wsBUpdate.getName());

        // Test DELETE
        workspaceRepository.deleteById(wsAId);
        assertEquals(false, workspaceRepository.existsById(wsAId));
    }
}