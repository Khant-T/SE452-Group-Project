package com.four.simple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.four.simple.models.Checklist;

@Repository
public interface ChecklistRepository 
    extends JpaRepository<Checklist,Long>{
    
    Checklist findById(long id);

    List<Checklist> findByWorkspaceId(long workspace_id);

    Checklist save(Checklist checklist);

    void delete(Checklist checklist);

    void deleteById(long id);

    boolean existsById(long id);

    boolean existsByWorkspaceId(long workspace_id);

}
