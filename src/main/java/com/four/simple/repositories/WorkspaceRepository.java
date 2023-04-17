package com.four.simple.repositories;

import java.util.List;

import org.hibernate.boot.registry.classloading.spi.ClassLoaderService.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.four.simple.models.Workspace;

@Repository
public interface WorkspaceRepository
    extends JpaRepository<Workspace, Long>
{

    Workspace findById(long id);

    List<Workspace> findByUserId(long userId);
    
    Workspace save(Workspace workspace);

    void delete(Workspace workspace);

    void deleteById(long id);

    boolean existsById(long id);


    
}
