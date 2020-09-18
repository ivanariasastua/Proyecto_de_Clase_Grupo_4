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
import org.una.tramites.dto.ParametrosGeneralesDTO;
import org.una.tramites.entities.ParametrosGenerales;
import org.una.tramites.repositories.IParametrosGeneralesRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author Ivan Josué Arias Astúa
 */
@Service
public class ParametrosGeneralesServiceImplementacion implements IParametrosGeneralesService {

    @Autowired
    private IParametrosGeneralesRepository paramGenRepository;


    @Override
    @Transactional
    public Optional<ParametrosGeneralesDTO> update(ParametrosGeneralesDTO pg, Long id) {
        if (paramGenRepository.findById(id).isPresent()) {
            ParametrosGenerales param = MapperUtils.EntityFromDto(pg, ParametrosGenerales.class);
            param = paramGenRepository.save(param);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(param, ParametrosGeneralesDTO.class));
        } else {
            return null;
        } 
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosGeneralesDTO>> findAll() {
        return ServiceConvertionHelper.findList(paramGenRepository.findAll(),ParametrosGeneralesDTO.class);
    }

    @Override
    @Transactional
    public ParametrosGeneralesDTO create(ParametrosGeneralesDTO parametros) {
        ParametrosGenerales param = MapperUtils.EntityFromDto(parametros, ParametrosGenerales.class);
        param = paramGenRepository.save(param);
        return MapperUtils.DtoFromEntity(param, ParametrosGeneralesDTO.class);
    }

    @Override
    public void delete(Long id) {
        paramGenRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParametrosGeneralesDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(paramGenRepository.findById(id),ParametrosGeneralesDTO.class);
    }
}
