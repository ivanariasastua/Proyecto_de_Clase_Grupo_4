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
import org.una.tramites.dto.TramitesTiposDTO;
import org.una.tramites.entities.TramitesEstados;
import org.una.tramites.entities.TramitesTipos;
import org.una.tramites.repositories.ITramitesTiposRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 * 
 * @author Ivan Josué Arias Astúa
 */
@Service
public class TramitesTiposServiceImplementation implements ITramitesTiposService{

    @Autowired
    private ITramitesTiposRepository traRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramitesTiposDTO>> findAll() {
        return ServiceConvertionHelper.findList(traRepository.findAll(),TramitesTiposDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TramitesTiposDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(traRepository.findById(id), TramitesTiposDTO.class);
    }

    @Override
    @Transactional
    public TramitesTiposDTO create(TramitesTiposDTO tramiteTipoDTO) {
        TramitesTipos entidad = MapperUtils.EntityFromDto(tramiteTipoDTO, TramitesTipos.class);
        entidad = traRepository.save(entidad);
        return MapperUtils.DtoFromEntity(entidad, TramitesTiposDTO.class);

    }

    @Override
    @Transactional
    public Optional<TramitesTiposDTO> update(TramitesTiposDTO tramiteTipoDTO, Long id) {
        if(traRepository.findById(id).isPresent()){
            TramitesTipos entidad = MapperUtils.EntityFromDto(tramiteTipoDTO, TramitesTipos.class);
            entidad = traRepository.save(entidad);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(entidad, TramitesTiposDTO.class));
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        traRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        traRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramitesTiposDTO>> findByDepartamentoId(Long id) {
        return ServiceConvertionHelper.findList(traRepository.findByDepartamentoId(id), TramitesTiposDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramitesTiposDTO>> findByDescripcion(String descripcion) {
        return ServiceConvertionHelper.findList(traRepository.findByDescripcion(descripcion), TramitesTiposDTO.class);

    }

}
