/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TramitesRegistradosDTO;
import org.una.tramites.entities.TramitesCambioEstado;
import org.una.tramites.entities.TramitesRegistrados;
import org.una.tramites.repositories.ITramitesRegistradosRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class TramitesRegistradosServiceImplementation implements ITramitesRegistradosService{
    
    @Autowired
    private ITramitesRegistradosRepository tramitesRegistradosRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramitesRegistradosDTO>> findAll() {
        return ServiceConvertionHelper.findList(tramitesRegistradosRepository.findAll(), TramitesRegistradosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TramitesRegistradosDTO> findById(Long id) {
        Optional<TramitesRegistrados> tramite = tramitesRegistradosRepository.findById(id);
        tramite.get().getEstados().sort((TramitesCambioEstado obj1, TramitesCambioEstado obj2) -> obj1.getId().compareTo(obj2.getId()));
        return ServiceConvertionHelper.oneToOptionalDto(tramite, TramitesRegistradosDTO.class);
    }

    @Override
    @Transactional
    public TramitesRegistradosDTO create(TramitesRegistradosDTO tramitesRegistrados) {
        TramitesRegistrados entidad = MapperUtils.EntityFromDto(tramitesRegistrados, TramitesRegistrados.class);
        entidad = tramitesRegistradosRepository.save(entidad);
        return MapperUtils.DtoFromEntity(entidad, TramitesRegistradosDTO.class);
    }

    @Override
    @Transactional
    public Optional<TramitesRegistradosDTO> update(TramitesRegistradosDTO tramitesRegistrados, Long id) {
        if(tramitesRegistradosRepository.findById(id).isPresent()){
            TramitesRegistrados entidad = MapperUtils.EntityFromDto(tramitesRegistrados, TramitesRegistrados.class);
            entidad = tramitesRegistradosRepository.save(entidad);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(entidad, TramitesRegistradosDTO.class));
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tramitesRegistradosRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        tramitesRegistradosRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramitesRegistradosDTO>> findByClienteId(Long id) {
        return ServiceConvertionHelper.findList(tramitesRegistradosRepository.findByClientesIdContaining(id), TramitesRegistradosDTO.class);
    }

    @Override
    public Optional<List<TramitesRegistradosDTO>> findByTramiteTipoId(Long id) {
        return ServiceConvertionHelper.findList(tramitesRegistradosRepository.findByTramitesTiposIdContaining(id), TramitesRegistradosDTO.class);
    }

    @Override
    public Optional<List<TramitesRegistradosDTO>> getByFilter(String cedula, String estado, Date inicio, Date fin) {
        return ServiceConvertionHelper.findList(tramitesRegistradosRepository.getByFilter(cedula, estado, inicio, fin), TramitesRegistradosDTO.class);
    }

    @Override
    public Optional<List<TramitesRegistradosDTO>> getByCedulaCliente(String cedula) {
        List<TramitesRegistrados> tramites = tramitesRegistradosRepository.getByCedulaCliente(cedula);
        tramites.forEach( t -> {
            t.getEstados().sort((TramitesCambioEstado obj1, TramitesCambioEstado obj2) -> obj1.getId().compareTo(obj2.getId()));
        });
        return ServiceConvertionHelper.findList(tramites, TramitesRegistradosDTO.class);
    }

    @Override
    public Optional<List<TramitesRegistradosDTO>> getByEstado(String estado) {
        List<TramitesRegistrados> tramites = tramitesRegistradosRepository.getByEstado(estado);
        tramites.forEach( t -> {
            t.getEstados().sort((TramitesCambioEstado obj1, TramitesCambioEstado obj2) -> obj1.getId().compareTo(obj2.getId()));
        });
        return ServiceConvertionHelper.findList(tramites, TramitesRegistradosDTO.class);
    }

    @Override
    public Optional<List<TramitesRegistradosDTO>> getByFechas(int yfi, int mfi, int dfi, int yff, int mff, int dff) {
        Date fInicial = createDate(yfi, mfi, dfi), fFinal = createDate(yff, mff, dff);
        List<TramitesRegistrados> tramites = tramitesRegistradosRepository.getByFechas(fInicial, fFinal);
        tramites.forEach( t -> {
            t.getEstados().sort((TramitesCambioEstado obj1, TramitesCambioEstado obj2) -> obj1.getId().compareTo(obj2.getId()));
        });
        return ServiceConvertionHelper.findList(tramites, TramitesRegistradosDTO.class);
    }
    
    private Date createDate(int year, int mes, int day){
        LocalDateTime ldt = LocalDateTime.of(year, mes, day, 0, 0, 0);
        return Date.from(ldt.atZone(ZoneId.of("UTC")).toInstant());  
    }
    
}
