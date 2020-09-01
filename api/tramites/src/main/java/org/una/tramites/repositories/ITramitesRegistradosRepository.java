/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.TramitesRegistrados;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author cordo
 */
public interface ITramitesRegistradosRepository extends JpaRepository<TramitesRegistrados, Long>{
    
    public List<TramitesRegistrados> findByClientesId(Long id);
    public List<TramitesRegistrados> findByTramitesTiposId(Long id);
}
