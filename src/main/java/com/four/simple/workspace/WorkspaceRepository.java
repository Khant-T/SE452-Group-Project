package com.four.simple.workspace;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository
    extends JpaRepository<Workspace, Long>
{
    Workspace findById(long id);

    List<Workspace> findByUserId(long userId);
}
