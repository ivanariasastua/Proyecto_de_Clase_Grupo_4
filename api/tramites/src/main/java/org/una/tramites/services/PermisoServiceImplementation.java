package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Permiso;
import org.una.tramites.repositories.IPermisoRepository;

/**
 *
 * @author Dios
 */
@Service
public class PermisoServiceImplementation implements IPermisoService {
    
    @Autowired
    private IPermisoRepository permisoRepository;
    

    @Override
    @Transactional(readOnly = true)
    public Optional<Permiso> findById(Long id) {
        return permisoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Permiso>> findAll() {
        return Optional.ofNullable(permisoRepository.findAll());
    }

    @Override
    @Transactional
    public Permiso create(Permiso permiso) {
        return permisoRepository.save(permiso);
    }

    @Override
    @Transactional
    public Optional<Permiso> update(Permiso permiso, Long id) {
        if(permisoRepository.findById(id).isPresent())
            return Optional.ofNullable(permisoRepository.save(permiso));
        else
            return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        permisoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        permisoRepository.deleteAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Permiso> findByCodigo(String Codigo) {
        return Optional.ofNullable(permisoRepository.findByCodigo(Codigo));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Permiso>> findByCodigoAproximate(String codigo) {
        return Optional.ofNullable(permisoRepository.findByCodigoContaining(codigo));
    }
    
}
