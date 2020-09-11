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

/**
 *
 * @author Dios
 */

@Entity
@Table(name = "Requisitos_Presentados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequisitoPresentado implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;
    
    @ManyToOne 
    @JoinColumn(name="tramites_registrados_id")
    private TramitesRegistrados tramiteRegistrado;
    
    @ManyToOne 
    @JoinColumn(name="requisitos_id")
    private Requisitos requisito;
}
