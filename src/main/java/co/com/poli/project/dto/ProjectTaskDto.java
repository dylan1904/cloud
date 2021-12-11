package co.com.poli.project.dto;

import co.com.poli.project.entities.Backlog;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class ProjectTaskDto {
    @NotBlank(message = "Se debe tener un nombre")
    private String name;

    @NotBlank(message = "Se debe tener un resumen")
    private String summary;

    private String acceptanceCriteria;

    @Pattern(regexp = "Not started|in progress|completed|deleted")
    private String status;

    @Range(min = 1, max = 8, message = "La prioridad debe estar entre 1 y 8")
    private int priority;

    @Range(min = 1, max = 8, message = "El numero de horas debe estar entre 1 y 8")
    private Double hours;

    private Date startDate;
    private Date endDate;

    @NotBlank(message = "Se debe tener un identificador")
    private String projectIdentifier;

    private Backlog backlog;}
