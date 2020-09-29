/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.entities;

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
 * @author cordo
 */
@Entity
@Table(name = "Tramites_Cambio_Estado")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TramitesCambioEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne 
    @JoinColumn(name="tramites_registrados_id")
    private TramitesRegistrados tramitesRegistradosId;
    
    @ManyToOne 
    @JoinColumn(name="tramites_estados_id")
    private TramitesEstados tramitesEstadoId;
    
    @ManyToOne 
    @JoinColumn(name="usuario_id")
    private Usuario usuarioId;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;
}
