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
import org.una.tramites.dto.RequisitosDTO;
import org.una.tramites.entities.Requisitos;
import org.una.tramites.repositories.IRequisitosRepository;
import org.una.tramites.repositories.IVariacionesRepository;
import org.una.tramites.utils.*;

/**
 * 
 * @author Ivan Josué Arias Astúa
 */
@Service
public class RequisitosServiceImplementation implements IRequisitosService{

    @Autowired
    private IRequisitosRepository reqRepo;
    
    @Autowired
    private IVariacionesRepository varRepo;
    
//    @Override
//    public Optional<List<Requisitos>> findAll() {
//        return Optional.ofNullable(reqRepo.findAll());
//    }
    
    @Override
    public Optional<List<RequisitosDTO>> findAll() {
        return ServiceConvertionHelper.findList(reqRepo.findAll(), RequisitosDTO.class);
    }

//    @Override
//    public Optional<Requisitos> findById(Long id) {
//        return reqRepo.findById(id);
//    }
    @Override
    public Optional<RequisitosDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(reqRepo.findById(id), RequisitosDTO.class);
    }

    @Override
    public RequisitosDTO create(RequisitosDTO requisito, Long id) {
        Requisitos req = MapperUtils.EntityFromDto(requisito, Requisitos.class);
        req.setVariaciones(varRepo.findById(id).get());
        req = reqRepo.save(req);
        return MapperUtils.DtoFromEntity(req, RequisitosDTO.class);
    }

    @Override
    public Optional<RequisitosDTO> update(RequisitosDTO requisito, Long id) {
        if(reqRepo.findById(id).isPresent()){
            Requisitos req = MapperUtils.EntityFromDto(requisito, Requisitos.class);
            req = reqRepo.save(req);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(req, RequisitosDTO.class));
        }
        return null;
    }
    
//    @Override
//    public Optional<List<Requisitos>> findByDescripcion(String descripcion) {
//        return Optional.ofNullable(reqRepo.findByDescripcion(descripcion));
//    }
    
    @Override
    public Optional<List<RequisitosDTO>> findByDescripcion(String descripcion) {
        return ServiceConvertionHelper.findList(reqRepo.findByDescripcion(descripcion), RequisitosDTO.class);
    }

    @Override
    public void delete(Long id) {
        reqRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        reqRepo.deleteAll();
    }
}
