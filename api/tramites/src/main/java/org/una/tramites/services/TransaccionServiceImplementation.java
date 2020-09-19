package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TransaccionDTO;
import org.una.tramites.entities.Transaccion;
import org.una.tramites.repositories.ITransaccionRepository;
import org.una.tramites.utils.*;

/**
 *
 * @author Dios
 */
@Service
public class TransaccionServiceImplementation implements ITransaccionService{
    
    @Autowired
    private ITransaccionRepository transaccionRepository;
    
//    @Override
//    @Transactional(readOnly = true)
//    public Optional<Transaccion> findById(Long id) {
//        return transaccionRepository.findById(id);
//    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(transaccionRepository.findById(id), TransaccionDTO.class);
    }
    
//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<Transaccion>> findAll() {
//        return Optional.ofNullable(transaccionRepository.findAll());
//    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findAll() {
        return ServiceConvertionHelper.findList(transaccionRepository.findAll(), TransaccionDTO.class);
    }

//    @Override
//    @Transactional
//    public Transaccion create(Transaccion transaccion) {
//        return transaccionRepository.save(transaccion);
//    }
    
    @Override
    @Transactional
    public TransaccionDTO create(TransaccionDTO transaccion) {
        Transaccion trans = MapperUtils.EntityFromDto(transaccion, Transaccion.class);
        trans = transaccionRepository.save(trans);
        return MapperUtils.DtoFromEntity(trans, TransaccionDTO.class);
    }

    @Override
    @Transactional
    public Optional<TransaccionDTO> update(TransaccionDTO transaccion, Long id) {
        if(transaccionRepository.findById(id).isPresent()){
            Transaccion trans = MapperUtils.EntityFromDto(transaccion, Transaccion.class);
            trans = transaccionRepository.save(trans);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(trans, TransaccionDTO.class));
        }
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
