package co.com.poli.project.services;

import co.com.poli.project.entities.Backlog;
import co.com.poli.project.repositories.BacklogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BacklogServiceImpl implements BacklogService {

    private BacklogRepository backlogRepository;

    @Override
    public List<Backlog> findAll() {
        return backlogRepository.findAll();
    }

    @Override
    public void save(Backlog backlog) {
        backlogRepository.save(backlog);
    }

    @Override
    public Backlog findBacklogByProjectIdentifier(String projectIdentifier) {
        return backlogRepository.findBacklogByProjectIdentifier(projectIdentifier);
    }
}
