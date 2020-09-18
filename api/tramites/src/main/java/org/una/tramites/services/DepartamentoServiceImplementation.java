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
import org.una.tramites.dto.DepartamentoDTO;
import org.una.tramites.entities.Departamento;
import org.una.tramites.repositories.IDepartamentoRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 * 
 * @author Ivan Josué Arias Astúa
 */
@Service
public class DepartamentoServiceImplementation implements IDepartamentoService{

    @Autowired
    private IDepartamentoRepository departamentoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<DepartamentoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(departamentoRepository.findById(id),DepartamentoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findAll() {
        return ServiceConvertionHelper.findList(departamentoRepository.findAll(),DepartamentoDTO.class);
    }

    @Override
    @Transactional
    public DepartamentoDTO create(DepartamentoDTO departamento) {
        Departamento dep = MapperUtils.EntityFromDto(departamento, Departamento.class);
        dep = departamentoRepository.save(dep);
        return MapperUtils.DtoFromEntity(dep, DepartamentoDTO.class);
    }

    @Override
    public Optional<DepartamentoDTO> update(DepartamentoDTO departamento, Long id) {
        if (departamentoRepository.findById(id).isPresent()) {
            Departamento dep = MapperUtils.EntityFromDto(departamento, Departamento.class);
            dep = departamentoRepository.save(dep);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(dep, DepartamentoDTO.class));
        } else {
            return null;
        } 
    }
    
    @Override
    public void delete(Long id) {
        departamentoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        departamentoRepository.deleteAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findByNombreAproximate(String nombre) {
        return ServiceConvertionHelper.findList(departamentoRepository.findByNombreContaining(nombre),DepartamentoDTO.class);
    }

}
