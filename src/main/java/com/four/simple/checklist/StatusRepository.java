package com.four.simple.checklist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository 
    extends JpaRepository<Status,Long> {
        
    Status findById(long id);

    // List<Status> findByListId(long list_id);

    // boolean existsByListId(long list_id);

    
}
