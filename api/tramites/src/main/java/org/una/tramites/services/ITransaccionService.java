package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Transaccion;

/**
 *
 * @author Dios
 */
public interface ITransaccionService {
    
    public Optional<Transaccion> findById(Long id);
    
    public Optional<List<Transaccion>> findAll();
    
    public Transaccion create(Transaccion transaccion);
    
    public Optional<Transaccion> update(Transaccion transaccion, Long id);
    
    public void delete(Long id);
    
    public void deleteAll();
}
