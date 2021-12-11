package co.com.poli.project.dto;

import co.com.poli.project.entities.Project;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BacklogDto {
    @NotBlank(message = "No se puede tener un identificador vacio")
    private String projectIdentifier;

    @NotNull(message = "Se debe asociar el backlog con un project que ya esté previamente creado")
    private Project project;
}
