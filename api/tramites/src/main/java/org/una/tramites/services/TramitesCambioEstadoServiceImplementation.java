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
import org.una.tramites.dto.TramitesCambioEstadoDTO;
import org.una.tramites.entities.TramitesCambioEstado;
import org.una.tramites.repositories.ITramitesCambioEstadoRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class TramitesCambioEstadoServiceImplementation implements ITramitesCambioEstadoService {

    @Autowired
    private ITramitesCambioEstadoRepository tramitesCambioRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramitesCambioEstadoDTO>> findAll() {
        return ServiceConvertionHelper.findList(tramitesCambioRepository.findAll(), TramitesCambioEstadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TramitesCambioEstadoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(tramitesCambioRepository.findById(id), TramitesCambioEstadoDTO.class);
    }

    @Override
    @Transactional
    public TramitesCambioEstadoDTO create(TramitesCambioEstadoDTO tramitesCambioE) {
        TramitesCambioEstado entidad = MapperUtils.EntityFromDto(tramitesCambioE, TramitesCambioEstado.class);
        entidad = tramitesCambioRepository.save(entidad);
        return MapperUtils.DtoFromEntity(entidad, TramitesCambioEstadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<TramitesCambioEstadoDTO> update(TramitesCambioEstadoDTO tramitesCambioE, Long id) {
        if(tramitesCambioRepository.findById(id).isPresent()){
            TramitesCambioEstado entidad = MapperUtils.EntityFromDto(tramitesCambioE, TramitesCambioEstado.class);
            entidad = tramitesCambioRepository.save(entidad);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(entidad, TramitesCambioEstadoDTO.class));
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tramitesCambioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        tramitesCambioRepository.deleteAll();
    }
    
    

}
