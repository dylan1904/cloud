package co.com.poli.project.services;

import co.com.poli.project.entities.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();
    void save(Project project);
    Project findProjectByProjectNameOrProjectIdentifier(String projectName, String projectIdentifier);
    List<Project> findProjectByProjectIdentifier(String projectName);
    int totalHours(List<Project> project);
    int totalHourByStatus(List<Project> project, String status);
}
