/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.tramites.entities.TramitesRegistrados;

/**
 *
 * @author cordo
 */
public interface ITramitesRegistradosRepository extends JpaRepository<TramitesRegistrados, Long>{
    
    @Query("SELECT t FROM TramitesRegistrados t LEFT JOIN t.cliente d WHERE t.cliente.id =:id")
    public List<TramitesRegistrados> findByClientesIdContaining(Long id);
    
    public List<TramitesRegistrados> findByTramitesTiposIdContaining(Long id);
    @Query("SELECT t FROM TramitesRegistrados t JOIN t.estados est on t.id = est.tramitesRegistradosId.id "+
            "WHERE UPPER(t.cliente.cedula) like CONCAT('%', UPPER(:cedula), '%') "+
            "and UPPER(est.tramitesEstadoId.nombre) Like CONCAT('%', UPPER(:estado), '%') and est.fechaRegistro BETWEEN :inicio and :fin")
    public List<TramitesRegistrados> getByFilter(String cedula, String estado, Date inicio, Date fin);
    
    /*Filtros de la tarea*/
    
    @Query("SELECT t FROM TramitesRegistrados t WHERE t.cliente.cedula = :cedula")
    public List<TramitesRegistrados> getByCedulaCliente(@Param("cedula")String cedula);
    
    @Query("SELECT distinct t FROM TramitesRegistrados t JOIN t.estados est ON t.id = est.tramitesRegistradosId.id "+
           "WHERE UPPER(est.tramitesEstadoId.nombre) = UPPER(:estado) and est.id = (SELECT MAX(e.id) FROM t.estados e WHERE e.tramitesRegistradosId.id = t.id)")
    public List<TramitesRegistrados> getByEstado(@Param("estado")String estado);
    
    @Query("SELECT t FROM TramitesRegistrados t WHERE t.fechaRegistro BETWEEN :fInicial and :fFinal")
    public List<TramitesRegistrados> getByFechas(@Param("fInicial")Date fInicial, @Param("fFinal") Date fFinal);
}
