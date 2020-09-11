package org.una.tramites.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
    private Date fechaRegistro; 
    private Date fechaModificacion; 
    private Departamento departamentoId; 
    private List<PermisoOtorgadoDTO> permisos;
    private boolean esJefe;
    
}

