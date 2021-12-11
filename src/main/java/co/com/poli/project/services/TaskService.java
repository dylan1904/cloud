package co.com.poli.project.services;

import co.com.poli.project.entities.Backlog;
import co.com.poli.project.entities.ProjectTask;

import java.util.List;

public interface TaskService {
    List<ProjectTask> findAll();
    ProjectTask findById(Long id);
    void save(ProjectTask projectTask);
    ProjectTask findProjectTaskByProjectIdentifierOrBacklog(String projectIdentifier, Backlog backlog);
}
