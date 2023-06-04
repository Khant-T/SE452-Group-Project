package com.four.simple.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.four.simple.checklist.Status;
import com.four.simple.checklist.StatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.test.context.ActiveProfiles;

/*
 * Test StatusRepository:
 *
 *  - CREATE
 *  - READ
 *  - UPDATE
 *  - DELETE
 *
 */

@SpringBootTest
@ActiveProfiles("test")
public class StatusTest
{
    @Autowired
    private StatusRepository statusRepo;

    @Test
    public void testCRUD()
    {
        long originalCount = statusRepo.count();

        // Test CREATE
        Status sA = Status.builder().description("Status A").build();
        Status sB = Status.builder().description("Status B").build();
        statusRepo.saveAll(List.of(sA, sB));
        assertEquals(originalCount + 2, statusRepo.count());

        // Get IDs of newly created Workspace objects
        long sAId = sA.getId();
        long sBId = sB.getId();

        // Test READ
        Status sBRead = statusRepo.findById(sBId);

        if (sBRead == null)
            sBRead = new Status();

        assertEquals(true, sBRead != null);
        assertEquals("Workspace B", sBRead.getDescription());

        // Test UPDATE
        Status sBUpdate = sBRead;
        sBUpdate.setDescription("Workspace B, Updated");
        statusRepo.save(sBUpdate);

        sBUpdate = statusRepo.findById(sBId);
        assertEquals("Workspace B, Updated", sBUpdate.getDescription());

        // Test DELETE
        statusRepo.deleteById(sAId);
        assertEquals(false, statusRepo.existsById(sAId));
    }
}