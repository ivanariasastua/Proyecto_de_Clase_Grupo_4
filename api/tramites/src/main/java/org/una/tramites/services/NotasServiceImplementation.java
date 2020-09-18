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
import org.una.tramites.dto.NotasDTO;
import org.una.tramites.entities.Notas;
import org.una.tramites.repositories.INotasRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class NotasServiceImplementation implements INotasService {

    @Autowired
    private INotasRepository notasRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotasDTO>> findAll() {
        return ServiceConvertionHelper.findList(notasRepository.findAll(),NotasDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NotasDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(notasRepository.findById(id),NotasDTO.class);
    }

    @Override
    @Transactional
    public NotasDTO create(NotasDTO notas) {
        Notas archiv = MapperUtils.EntityFromDto(notas, Notas.class);
        archiv = notasRepository.save(archiv);
        return MapperUtils.DtoFromEntity(archiv, NotasDTO.class);
    }

    @Override
    @Transactional
    public Optional<NotasDTO> update(NotasDTO notas, Long id) {
        if (notasRepository.findById(id).isPresent()) {
            Notas archiv = MapperUtils.EntityFromDto(notas, Notas.class);
            archiv = notasRepository.save(archiv);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(archiv, NotasDTO.class));
        } else {
            return null;
        } 
    }

    @Override
    @Transactional
    public void delete(Long id) {
        notasRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        notasRepository.deleteAll();
    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<NotasDTO>> findByTitulo(String cedula) {
//        return ServiceConvertionHelper.findList(notasRepository.findByTitulo(cedula), NotasDTO.class);
//    }

}
