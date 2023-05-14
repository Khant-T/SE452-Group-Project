package com.four.simple.checklist;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistRepository 
    extends JpaRepository<Checklist,Long>{
    
    Checklist findById(long id);

    // List<Checklist> findByWorkspaceId(long workspace_id);

    // boolean existsByWorkspaceId(long workspace_id);

}
