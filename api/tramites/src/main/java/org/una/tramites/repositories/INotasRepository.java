/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.tramites.entities.Notas;

/**
 *
 * @author cordo
 */
public interface INotasRepository extends JpaRepository<Notas, Long>{
    
    @Query("select n from Notas n where n.titulo = :titulo")
    public Notas findByTitulo(@Param("titulo")String titulo);
}
