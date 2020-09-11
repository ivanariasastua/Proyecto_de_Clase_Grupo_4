package org.una.tramites.dto;

import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.Departamento;

@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class UsuarioDTO {
    private Long id; 
    private String nombreCompleto;   
    private String cedula; 
    private boolean estado; 
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro; 
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaModificacion; 
    private Departamento departamentoId; 
    private List<PermisoOtorgadoDTO> permisos;
    private boolean esJefe;
    
}

