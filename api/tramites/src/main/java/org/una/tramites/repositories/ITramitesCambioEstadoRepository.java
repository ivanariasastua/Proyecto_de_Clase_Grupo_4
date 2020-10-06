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
import org.una.tramites.entities.TramitesCambioEstado;

/**
 *
 * @author cordo
 */
public interface ITramitesCambioEstadoRepository extends JpaRepository<TramitesCambioEstado, Long>{

    @Query("SELECT tce FROM TramitesCambioEstado tce WHERE tce.tramitesRegistradosId.id = :idTramite ORDER BY tce.id DESC")
    public List<TramitesCambioEstado> findEstadoActualTramite(@Param("idTramite") Long idTramite);
}
