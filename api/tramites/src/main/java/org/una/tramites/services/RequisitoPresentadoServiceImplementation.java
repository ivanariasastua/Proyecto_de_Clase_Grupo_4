
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.RequisitoPresentado;
import org.una.tramites.repositories.IRequisitoPresentadoRepository;

@Service
public class RequisitoPresentadoServiceImplementation implements IRequisitoPresentadoService{
    
    @Autowired
    private IRequisitoPresentadoRepository requisitoPresentadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<RequisitoPresentado> findById(Long id) {
        return requisitoPresentadoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RequisitoPresentado>> findAll() {
        return Optional.ofNullable(requisitoPresentadoRepository.findAll()) ;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RequisitoPresentado>> findByTramiteRegistrado(Long id) {
        return Optional.of(requisitoPresentadoRepository.findByTramiteRegistrado(id));
    }

    @Override
    @Transactional
    public RequisitoPresentado create(RequisitoPresentado requisitoPresentado) {
        return requisitoPresentadoRepository.save(requisitoPresentado);
    }

    @Override
    @Transactional
    public Optional<RequisitoPresentado> update(RequisitoPresentado requisitoPresentado, Long id) {
        if(requisitoPresentadoRepository.findById(id).isPresent())
            return Optional.ofNullable(requisitoPresentadoRepository.save(requisitoPresentado));
        else
            return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        requisitoPresentadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        requisitoPresentadoRepository.deleteAll();
    }
    
}
