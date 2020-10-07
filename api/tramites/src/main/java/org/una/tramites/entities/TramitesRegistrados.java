/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
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
 * @author cordo
 */
@Entity
@Table(name = "Tramites_Registrados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TramitesRegistrados implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne 
    @JoinColumn(name="clientes_id")
    private Cliente cliente;
    
    @ManyToOne 
    @JoinColumn(name="tramites_tipos_id")
    private TramitesTipos tramitesTiposId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramiteRegistradoId") 
    private List<ArchivoRelacionado> archivosRelacionados = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramitesRegistradosId")
    private List<TramitesCambioEstado> estados = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramiteRegistradoId")
    private List<RequisitoPresentado> requisitosPresentados = new ArrayList<>();
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;
    
    @PrePersist()
    public void prePersist(){
        this.fechaRegistro = new Date();
    }
    
    private static final long serialVersionUID = 1L;
    
}
