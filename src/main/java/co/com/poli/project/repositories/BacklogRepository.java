package co.com.poli.project.repositories;


import co.com.poli.project.entities.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog,Long> {
    Backlog findBacklogByProjectIdentifier(String projectIdentifier);
}
