/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.tramites.entities.TramitesRegistrados;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author cordo
 */
public interface ITramitesRegistradosRepository extends JpaRepository<TramitesRegistrados, Long>{
    
    @Query("SELECT t FROM TramitesRegistrados t LEFT JOIN t.cliente d WHERE t.cliente.id =:id")
    public List<TramitesRegistrados> findByClientesIdContaining(Long id);
    public List<TramitesRegistrados> findByTramitesTiposIdContaining(Long id);
}
