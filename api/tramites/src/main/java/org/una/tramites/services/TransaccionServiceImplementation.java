package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Transaccion;
import org.una.tramites.repositories.ITransaccionRepository;

/**
 *
 * @author Dios
 */
@Service
public class TransaccionServiceImplementation implements ITransaccionService{
    
    @Autowired
    private ITransaccionRepository transaccionRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Transaccion> findById(Long id) {
        return transaccionRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Transaccion>> findAll() {
        return Optional.ofNullable(transaccionRepository.findAll());
    }

    @Override
    @Transactional
    public Transaccion create(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    @Override
    @Transactional
    public Optional<Transaccion> update(Transaccion transaccion, Long id) {
        if(transaccionRepository.findById(id).isPresent())
            return Optional.ofNullable(transaccionRepository.save(transaccion));
        else
            return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        transaccionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        transaccionRepository.deleteAll();
    }
    
}
