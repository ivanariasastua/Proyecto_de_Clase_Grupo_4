/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TramitesRegistradosDTO;
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
        return ServiceConvertionHelper.oneToOptionalDto(tramitesRegistradosRepository.findById(id), TramitesRegistradosDTO.class);
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
    
    
}
