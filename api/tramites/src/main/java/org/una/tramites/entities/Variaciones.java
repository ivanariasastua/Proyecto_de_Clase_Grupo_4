/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.una.tramites.entities;

import java.io.Serializable;
import java.util.ArrayList;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author Ivan Josué Arias Astúa
 */
@Entity
@Table(name = "Variaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Variaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private boolean grupo;
    
    @Column
    private String descripcion;
    
    @Column
    private boolean estado;
    
    @ManyToOne 
    @JoinColumn(name="tramites_tipos_id")
    private TramitesTipos tramites;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "variaciones") 
    private List<Requisitos> requisitos = new ArrayList<>();
    
    @PrePersist
    public void prePersist() {
        estado=true;
    }
}
