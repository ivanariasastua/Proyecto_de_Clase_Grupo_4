package org.una.tramites.dto;

import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class UsuarioDTO {
    private Long id; 
    private String nombreCompleto; 
    private String passwordEncriptado;
    private String cedula; 
    private boolean estado; 
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro; 
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaModificacion; 
    private DepartamentoDTO departamento; 
    private List<PermisoOtorgadoDTO> permisos;
    private boolean esJefe;
}

