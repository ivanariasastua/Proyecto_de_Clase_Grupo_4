
package municipales.tramite.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Dios
 */

/*
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
*/
public class UsuarioDTO {
    
    private Long id; 
    private String nombreCompleto;   
    private String cedula; 
    private boolean estado; 
    private Date fechaRegistro; 
    private Date fechaModificacion; 
    private DepartamentoDTO departamentoId;
    private List<PermisoOtorgadoDTO> permisos;
    private boolean esJefe;

    public UsuarioDTO(Long id, String nombreCompleto, String cedula, boolean estado, Date fechaRegistro, Date fechaModificacion, DepartamentoDTO departamentoId, List<PermisoOtorgadoDTO> permisos, boolean esJefe) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.cedula = cedula;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
        this.departamentoId = departamentoId;
        this.permisos = permisos;
        this.esJefe = esJefe;
    }

    public UsuarioDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public DepartamentoDTO getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(DepartamentoDTO departamentoId) {
        this.departamentoId = departamentoId;
    }

    public List<PermisoOtorgadoDTO> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermisoOtorgadoDTO> permisos) {
        this.permisos = permisos;
    }

    public boolean isEsJefe() {
        return esJefe;
    }

    public void setEsJefe(boolean esJefe) {
        this.esJefe = esJefe;
    }
    
    
}
