package co.com.poli.project.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class ProjectDto {
    @NotBlank(message = "El project debe tener un nombre")
    private String projectName;

    @Range(min = 5, max = 7, message = "El identificador del project debe estar entre 5 y 7")
    @NotBlank(message = "No se puede tener un identificador vacio")
    private String projectIdentifier;

    @NotBlank(message = "Se debe tener una descripción")
    private String description;

    private Date startDate;

    private Date endDate;
}
