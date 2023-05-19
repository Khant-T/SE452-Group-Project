package com.four.simple.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.four.simple.task.Subtask;
import com.four.simple.task.SubtaskRepository;

/*
 * Test SubtaskRepository:
 *
 * - CREATE
 * - READ
 * - UPDATE
 * - DELETE
 *
 */

@SpringBootTest
public class SubtaskTest {
    @Autowired
    private SubtaskRepository subtaskRepo;

    @Test
    public void testCRUD()
    {
        long originalCount = subtaskRepo.count();

        // Test CREATE
        Subtask subtaskA = Subtask.builder().description("Subtask A").build();
        Subtask subtaskB = Subtask.builder().description("Subtask B").build();
        subtaskRepo.saveAll(List.of(subtaskA, subtaskB));
        assertEquals(originalCount + 2, subtaskRepo.count());

        // Get IDs of newly created Subtask objects
        long subtaskAId = subtaskA.getId();
        long subtaskBId = subtaskB.getId();

        // Test READ
        Subtask subtaskBRead = subtaskRepo.findById(subtaskBId);

        if (subtaskBRead == null)
            subtaskBRead = new Subtask();

        assertEquals(true, subtaskBRead != null);
        assertEquals("Subtask B", subtaskBRead.getDescription());

        // Test UPDATE
        Subtask subtaskBUpdate = subtaskBRead;
        subtaskBUpdate.setDescription("Subtask B, Updated");
        subtaskRepo.save(subtaskBUpdate);

        subtaskBUpdate = subtaskRepo.findById(subtaskBId);

        // Test DELETE
        subtaskRepo.deleteById(subtaskAId);
        assertEquals(false, subtaskRepo.existsById(subtaskAId));
    }
}
