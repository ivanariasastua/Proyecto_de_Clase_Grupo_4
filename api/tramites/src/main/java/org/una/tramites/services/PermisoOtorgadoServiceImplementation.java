
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.repositories.IPermisoOtorgadoRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dios
 */
@Service
public class PermisoOtorgadoServiceImplementation implements IPermisoOtorgadoService{
    
    @Autowired
    private IPermisoOtorgadoRepository permisoOtorgadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgado>> findAll() {
        return Optional.ofNullable(permisoOtorgadoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoOtorgado> findById(Long id) {
        return permisoOtorgadoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgado>> findByUsuario(Long id) {
        return Optional.ofNullable(permisoOtorgadoRepository.findByUsuario(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgado>> findByPermiso(Long id) {
        return Optional.ofNullable(permisoOtorgadoRepository.findByPermiso(id));
    }

    @Override
    public Optional<List<PermisoOtorgado>> findPermisoOtorgadoByfechaRegistro(Date fechaRegistro) {
        return Optional.ofNullable(permisoOtorgadoRepository.findByFechaRegistro(fechaRegistro));
    }

    @Override
    @Transactional
    public PermisoOtorgado create(PermisoOtorgado permisoOtorgado) {
        return permisoOtorgadoRepository.save(permisoOtorgado);
    }

    @Override
    @Transactional
    public Optional<PermisoOtorgado> update(PermisoOtorgado permisoOtorgado, Long id) {
        if (permisoOtorgadoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(permisoOtorgadoRepository.save(permisoOtorgado));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        permisoOtorgadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        permisoOtorgadoRepository.deleteAll();
    }
    
}
