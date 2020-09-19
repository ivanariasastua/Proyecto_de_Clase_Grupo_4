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
import org.una.tramites.dto.VariacionesDTO;
import org.una.tramites.entities.Variaciones;
import org.una.tramites.repositories.ITramitesTiposRepository;
import org.una.tramites.repositories.IVariacionesRepository;
import org.una.tramites.utils.*;

/**
 * 
 * @author Ivan Josué Arias Astúa
 */
@Service
public class VariacionesServiceImplementation implements IVariacionesService{
    
    @Autowired
    private IVariacionesRepository varRepository;
    
    @Autowired
    private ITramitesTiposRepository traRepository;

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<Variaciones>> findAll() {
//        return Optional.ofNullable(varRepository.findAll());
//    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<VariacionesDTO>> findAll() {
        return ServiceConvertionHelper.findList(varRepository.findAll(), VariacionesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VariacionesDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(varRepository.findById(id), VariacionesDTO.class);
    }

    @Override
    @Transactional
    public VariacionesDTO create(VariacionesDTO variacion, Long id) {
        Variaciones var = MapperUtils.EntityFromDto(variacion, Variaciones.class);
        var.setTramites(traRepository.findById(id).get());
        var = varRepository.save(var);
        return MapperUtils.DtoFromEntity(var, VariacionesDTO.class);
    }

    @Override
    @Transactional
    public Optional<VariacionesDTO> update(VariacionesDTO variacion, Long id) {
        if(varRepository.findById(id).isPresent()){
            Variaciones var = MapperUtils.EntityFromDto(variacion, Variaciones.class);
            var = varRepository.save(var);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(var, VariacionesDTO.class));
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        varRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        varRepository.deleteAll();
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<Variaciones>> findByGrupoContaining(int grupo) {
//        return Optional.ofNullable(varRepository.findByGrupoContaining(grupo));
//    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<VariacionesDTO>> findByGrupoContaining(int grupo) {
        return ServiceConvertionHelper.findList(varRepository.findByGrupoContaining(grupo), VariacionesDTO.class);
    }
    
//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<Variaciones>> findByDescripcion(String descripcion) {
//        return Optional.ofNullable(varRepository.findByDescripcionContaining(descripcion));
//    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<VariacionesDTO>> findByDescripcion(String descripcion) {
        return ServiceConvertionHelper.findList(varRepository.findByDescripcionContaining(descripcion), VariacionesDTO.class);
    }

}
