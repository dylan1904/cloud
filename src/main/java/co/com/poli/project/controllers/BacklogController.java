package co.com.poli.project.controllers;

import co.com.poli.project.helpers.Format;
import co.com.poli.project.dto.BacklogDto;
import org.springframework.web.bind.annotation.*;
import co.com.poli.project.helpers.ResponseBuilder;
import co.com.poli.project.services.ProjectService;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import co.com.poli.project.helpers.Response;
import co.com.poli.project.entities.Backlog;
import org.springframework.validation.BindingResult;
import co.com.poli.project.services.BacklogService;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/backlog")
@AllArgsConstructor
public class BacklogController {
    private final ProjectService iProjectService;
    private final BacklogService iBacklogService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<Response> findAll() {
        List<Backlog> backlog = iBacklogService.findAll();

        if (backlog.isEmpty()) {
            return new ResponseEntity<>(ResponseBuilder.failed("Not are backlogs"),
                    HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(ResponseBuilder.success(backlog),
                HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Response> save(@Valid @RequestBody BacklogDto backlogDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(ResponseBuilder.failed(Format.formatMessage(result)),
                    HttpStatus.BAD_REQUEST);
        } else {
            Backlog backlog = iBacklogService.findBacklogByProjectIdentifier(backlogDto.getProjectIdentifier());

            if (backlog != null) {
                return new ResponseEntity<>(ResponseBuilder.failed("El identificador ya existe"),
                        HttpStatus.BAD_REQUEST);
            }
        }

        Backlog backlog = modelMapper.map(backlogDto, Backlog.class);
        iBacklogService.save(backlog);

        return new ResponseEntity<>(ResponseBuilder.successCreated(backlog),
                HttpStatus.CREATED);
    }
}
