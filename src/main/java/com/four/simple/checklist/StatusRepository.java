package com.four.simple.checklist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService.Work;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository 
    extends JpaRepository<Status,Long> {
        
    Status findById(long id);

    List<Status> findByListId(long list_id);

    boolean existsByListId(long list_id);

    
}
