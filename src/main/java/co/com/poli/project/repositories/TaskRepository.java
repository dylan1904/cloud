package co.com.poli.project.repositories;

import co.com.poli.project.entities.Backlog;
import co.com.poli.project.entities.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<ProjectTask,Long> {
    ProjectTask findProjectTaskByProjectIdentifierOrBacklog(String projectIdentifier, Backlog backlog);
}
