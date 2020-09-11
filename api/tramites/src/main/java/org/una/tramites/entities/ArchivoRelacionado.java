package org.una.tramites.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Archivos_Relacionados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArchivoRelacionado implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne 
    @JoinColumn(name="tramites_registrados_id")
    private TramitesRegistrados tramiteRegistrado;
    
    
    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column
    private boolean estado;
    
    @Column(name = "ruta_archivo")
    private String rutaArchivo;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;
    
    @Column(name = "etiquetas")
    private String etiqueta;
    
    @Column(name = "fecha_modificacion")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
}
