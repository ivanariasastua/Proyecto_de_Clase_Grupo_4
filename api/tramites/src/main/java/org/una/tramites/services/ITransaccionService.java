package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TransaccionDTO;

/**
 *
 * @author Dios
 */
public interface ITransaccionService {
    
    public Optional<TransaccionDTO> findById(Long id);
    
    public Optional<List<TransaccionDTO>> findAll();
    
    public TransaccionDTO create(TransaccionDTO transaccion);
    
    public Optional<TransaccionDTO> update(TransaccionDTO transaccion, Long id);
    
    public void delete(Long id);
    
    public void deleteAll();
}
