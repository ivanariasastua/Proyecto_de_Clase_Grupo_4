
package org.una.tramites.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.ArchivoRelacionadoDTO;
import org.una.tramites.entities.ArchivoRelacionado;
import org.una.tramites.repositories.IArchivoRelacionadoRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

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
    public Optional<ArchivoRelacionadoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(archivoRelacionadoRepository.findById(id),ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findAll() {
        return ServiceConvertionHelper.findList(archivoRelacionadoRepository.findAll(),ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByTramiteRegistrado(Long id) {
        return ServiceConvertionHelper.findList(archivoRelacionadoRepository.findByTramiteRegistrado(id),ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByFechaRegistro(Date fechaRegistro) {
        return ServiceConvertionHelper.findList(archivoRelacionadoRepository.findByFechaRegistro(fechaRegistro),ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional
    public ArchivoRelacionadoDTO create(ArchivoRelacionadoDTO archivo) {
        ArchivoRelacionado archiv = MapperUtils.EntityFromDto(archivo, ArchivoRelacionado.class);
        archiv = archivoRelacionadoRepository.save(archiv);
        return MapperUtils.DtoFromEntity(archiv, ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<ArchivoRelacionadoDTO> update(ArchivoRelacionadoDTO archivo, Long id) {
        if (archivoRelacionadoRepository.findById(id).isPresent()) {
            ArchivoRelacionado archiv = MapperUtils.EntityFromDto(archivo, ArchivoRelacionado.class);
            archiv = archivoRelacionadoRepository.save(archiv);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(archiv, ArchivoRelacionadoDTO.class));
        } else {
            return null;
        } 
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
