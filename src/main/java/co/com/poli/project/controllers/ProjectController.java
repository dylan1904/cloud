package co.com.poli.project.controllers;

import co.com.poli.project.helpers.Format;
import co.com.poli.project.entities.Project;
import co.com.poli.project.helpers.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import co.com.poli.project.dto.ProjectDto;
import co.com.poli.project.helpers.ResponseBuilder;
import org.modelmapper.ModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import co.com.poli.project.services.ProjectService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {
    private ProjectService ProjectService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public Response findAll() {
        List<Project> projects = ProjectService.findAll();

        if (projects.isEmpty()) {
            return ResponseBuilder.failed("Not are projects");
        }

        return ResponseBuilder.success(projects);
    }

    @PostMapping()
    public ResponseEntity<Response> save(@Valid @RequestBody ProjectDto projectDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(ResponseBuilder.failed(Format.formatMessage(result)),
                    HttpStatus.BAD_REQUEST);
        }

        Project project = modelMapper.map(projectDto, Project.class);

        if (ProjectService.findProjectByProjectNameOrProjectIdentifier(project.getProjectName(), project.getProjectIdentifier()) != null) {
            return new ResponseEntity<>(ResponseBuilder.failed("Nombre del proyecto o identificador del proyecto ya existen"),
                    HttpStatus.BAD_REQUEST);
        }

        ProjectService.save(project);

        return new ResponseEntity<>(ResponseBuilder.success(project),
                HttpStatus.CREATED);
    }
}
