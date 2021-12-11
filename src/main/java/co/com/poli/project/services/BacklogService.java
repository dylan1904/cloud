package co.com.poli.project.services;

import co.com.poli.project.entities.Backlog;

import java.util.List;

public interface BacklogService {
    List<Backlog> findAll();
    void save(Backlog backlog);
    Backlog findBacklogByProjectIdentifier(String projectIdentifier);
}
