
package org.una.tramites.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.ArchivoRelacionado;
import org.una.tramites.repositories.IArchivoRelacionadoRepository;

/**
 *
 * @author Dios
 */
@Service
public class ArchivoRelacionadoServiceImplementation implements IArchivoRelacionadoService{
    
    @Autowired
    private IArchivoRelacionadoRepository archivoRelacionadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ArchivoRelacionado> findById(Long id) {
        return archivoRelacionadoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionado>> findAll() {
        return Optional.ofNullable(archivoRelacionadoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionado>> findByTramiteRegistrado(Long id) {
        return Optional.ofNullable(archivoRelacionadoRepository.findByTramiteRegistrado(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionado>> findByFechaRegistro(Date fechaRegistro) {
        return Optional.ofNullable(archivoRelacionadoRepository.findByFechaRegistro(fechaRegistro));
    }

    @Override
    @Transactional
    public ArchivoRelacionado create(ArchivoRelacionado archivoRelacionado) {
        return archivoRelacionadoRepository.save(archivoRelacionado);
    }

    @Override
    @Transactional
    public Optional<ArchivoRelacionado> update(ArchivoRelacionado ArchivoRelacionado, Long id) {
        if(archivoRelacionadoRepository.findById(id).isPresent())
            return Optional.ofNullable(archivoRelacionadoRepository.save(ArchivoRelacionado));
        else
            return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        archivoRelacionadoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        archivoRelacionadoRepository.deleteAll();
    }
    
}
