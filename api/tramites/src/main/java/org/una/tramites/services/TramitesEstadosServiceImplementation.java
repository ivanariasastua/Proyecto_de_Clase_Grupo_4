/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TramitesEstadosDTO;
import org.una.tramites.entities.TramitesEstados;
import org.una.tramites.repositories.ITramitesEstadosRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class TramitesEstadosServiceImplementation implements ITramitesEstadosService {

    @Autowired
    private ITramitesEstadosRepository tramRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramitesEstadosDTO>> findAll() {
        return ServiceConvertionHelper.findList(tramRepository.findAll(), TramitesEstadosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TramitesEstadosDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(tramRepository.findById(id), TramitesEstadosDTO.class);
    }

    @Override
    @Transactional
    public TramitesEstadosDTO create(TramitesEstadosDTO tramites) {
        TramitesEstados entidad = MapperUtils.EntityFromDto(tramites, TramitesEstados.class);
        entidad = tramRepository.save(entidad);
        return MapperUtils.DtoFromEntity(entidad, TramitesEstadosDTO.class);
    }

    @Override
    @Transactional
    public Optional<TramitesEstadosDTO> update(TramitesEstadosDTO tramites, Long id) {
        if(tramRepository.findById(id).isPresent()){
            TramitesEstados entidad = MapperUtils.EntityFromDto(tramites, TramitesEstados.class);
            entidad = tramRepository.save(entidad);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(entidad, TramitesEstadosDTO.class));
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tramRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        tramRepository.deleteAll();
    }
    
}
