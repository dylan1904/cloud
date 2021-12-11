package co.com.poli.project.services;

import co.com.poli.project.entities.Project;
import co.com.poli.project.entities.ProjectTask;
import co.com.poli.project.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Override
    public Project findProjectByProjectNameOrProjectIdentifier(String projectName, String projectIdentifier) {
        return projectRepository.findProjectByProjectNameOrProjectIdentifier(projectName, projectIdentifier);
    }

    @Override
    public List<Project> findProjectByProjectIdentifier(String projectName) {
        return projectRepository.findProjectByProjectIdentifier(projectName);
    }

    public int totalHours(List<Project> project) {
        int hours = 0;

        for (int i = 0; i < project.size(); i++) {
            List<ProjectTask> tasks = project.get(i).getBacklog().getProjectTasks();
            for (int j = 0; j < tasks.size(); j++) {
                hours += tasks.get(j).getHours();
            }
        }

        return hours;
    }

    public int totalHourByStatus(List<Project> project, String status) {
        int hours = 0;

        for (int i = 0; i < project.size(); i++) {
            List<ProjectTask> tasks = project.get(i).getBacklog().getProjectTasks();
            for (int j = 0; j < tasks.size(); j++) {
                if (tasks.get(j).getStatus().equals(status)) {
                    hours += tasks.get(j).getHours();
                }
            }
        }

        return hours;
    }
}
