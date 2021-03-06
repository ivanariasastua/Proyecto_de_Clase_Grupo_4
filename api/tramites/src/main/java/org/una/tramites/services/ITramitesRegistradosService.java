/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramitesRegistradosDTO;
import org.una.tramites.entities.TramitesRegistrados;

/**
 *
 * @author cordo
 */

public interface ITramitesRegistradosService {
    public Optional<List<TramitesRegistradosDTO>> findAll();

    public Optional<TramitesRegistradosDTO> findById(Long id);
    
    public TramitesRegistradosDTO create(TramitesRegistradosDTO tramitesRegistrados);

    public Optional<TramitesRegistradosDTO> update(TramitesRegistradosDTO tramitesRegistrados, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<TramitesRegistradosDTO>> findByClienteId(Long id);
    
    public Optional<List<TramitesRegistradosDTO>> findByTramiteTipoId(Long id);

    public Optional<List<TramitesRegistradosDTO>> getByFilter(String cedula, String estado, Date inicio, Date fin);

    /*Filtros tarea*/
    
    public Optional<List<TramitesRegistradosDTO>> getByCedulaCliente(String cedula);
    
    public Optional<List<TramitesRegistradosDTO>> getByEstado(String estado);
    
    public Optional<List<TramitesRegistradosDTO>> getByFechas(int yfi, int mfi, int dfi, int yff, int mff, int dff);
}
