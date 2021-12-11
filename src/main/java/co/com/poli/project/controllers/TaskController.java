package co.com.poli.project.controllers;

import co.com.poli.project.helpers.Format;
import co.com.poli.project.entities.ProjectTask;
import co.com.poli.project.helpers.Response;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import co.com.poli.project.helpers.ResponseBuilder;
import org.springframework.validation.BindingResult;
import lombok.AllArgsConstructor;
import co.com.poli.project.dto.ProjectTaskDto;
import co.com.poli.project.services.TaskService;
import co.com.poli.project.services.ProjectService;
import org.springframework.web.bind.annotation.*;
import co.com.poli.project.entities.Project;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projecttask")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private ProjectService projectService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<Response> findAll() {
        List<ProjectTask> projectTasks = taskService.findAll();

        if (projectTasks.isEmpty()) {
            return new ResponseEntity<>(ResponseBuilder.failed("Not are project tasks"),
                    HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(ResponseBuilder.success(projectTasks),
                HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Response> save(@Valid @RequestBody ProjectTaskDto projectTaskDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(ResponseBuilder.failed(Format.formatMessage(result)),
                    HttpStatus.BAD_REQUEST);
        }

        ProjectTask projectTask = modelMapper.map(projectTaskDto, ProjectTask.class);
        taskService.save(projectTask);

        return new ResponseEntity<>(ResponseBuilder.successCreated(projectTask),
                HttpStatus.CREATED);
    }

    @GetMapping("/project/{projectIdentifier}")
    public ResponseEntity<Response> findProjectTasks(@PathVariable String projectIdentifier) {
        List<Project> projects = projectService.findProjectByProjectIdentifier(projectIdentifier);

        if (projects.isEmpty()) {
            return new ResponseEntity<>(ResponseBuilder.failed("Not are projects"),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ResponseBuilder.success(projects.get(0).getBacklog().getProjectTasks()),
                HttpStatus.OK);
    }

    @GetMapping("/hours/project/{projectIdentifier}")
    public ResponseEntity<Response> totalHours(@PathVariable String projectIdentifier) {
        List<Project> projects = projectService.findProjectByProjectIdentifier(projectIdentifier);

        if (projects.isEmpty()) {
            return new ResponseEntity<>(ResponseBuilder.failed("Not are projects"),
                    HttpStatus.NOT_FOUND);
        }

        int totalHours = projectService.totalHours(projects);

        return new ResponseEntity<>(ResponseBuilder.success(totalHours),
                HttpStatus.OK);
    }

    @GetMapping("/hours/project/{projectIdentifier}/{status}")
    public ResponseEntity<Response> totalHourByStatus(@PathVariable String projectIdentifier, @PathVariable String status) {
        List<Project> projects = projectService.findProjectByProjectIdentifier(projectIdentifier);

        if (projects.isEmpty()) {
            return new ResponseEntity<>(ResponseBuilder.failed("Not projects"),
                    HttpStatus.NOT_FOUND);
        }

        int totalHours = projectService.totalHourByStatus(projects, status);

        return new ResponseEntity<>(ResponseBuilder.success(totalHours),
                HttpStatus.OK);
    }

}
