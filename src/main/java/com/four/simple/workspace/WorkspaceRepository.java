package com.four.simple.workspace;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceRepository
    extends JpaRepository<Workspace, Long>
{
    Workspace findById(long id);

    List<Workspace> findByUserId(long userId);
}
