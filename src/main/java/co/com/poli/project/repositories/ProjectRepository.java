package co.com.poli.project.repositories;

import co.com.poli.project.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findProjectByProjectNameOrProjectIdentifier(String projectName, String projectIdentifier);
    List<Project> findProjectByProjectIdentifier(String projectName);
}
