package co.com.poli.project.services;

import co.com.poli.project.entities.Backlog;
import co.com.poli.project.entities.ProjectTask;
import co.com.poli.project.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private TaskRepository iTaskRepository;

    @Override
    public List<ProjectTask> findAll() {
        return iTaskRepository.findAll();
    }

    @Override
    public ProjectTask findById(Long id) {
        return iTaskRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ProjectTask projectTask) {
        iTaskRepository.save(projectTask);
    }

    @Override
    public ProjectTask findProjectTaskByProjectIdentifierOrBacklog(String projectIdentifier, Backlog backlog) {
        return iTaskRepository.findProjectTaskByProjectIdentifierOrBacklog(projectIdentifier, backlog);
    }
}
