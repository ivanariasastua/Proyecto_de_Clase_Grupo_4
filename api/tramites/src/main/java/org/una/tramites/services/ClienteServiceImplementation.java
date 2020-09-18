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
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.ClienteDTO;
import org.una.tramites.entities.Cliente;
import org.una.tramites.repositories.IClienteRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class ClienteServiceImplementation implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteDTO>> findAll() {
        return ServiceConvertionHelper.findList(clienteRepository.findAll(),ClienteDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(clienteRepository.findById(id),ClienteDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteDTO>> findByCedulaAproximate(String cedula) {
        return ServiceConvertionHelper.findList(clienteRepository.findByCedulaContaining(cedula),ClienteDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
        return ServiceConvertionHelper.findList(clienteRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto),ClienteDTO.class);
    }

    @Override
    @Transactional
    public ClienteDTO create(ClienteDTO cliente) {
        Cliente client =MapperUtils.EntityFromDto(cliente,Cliente.class);
        client = clienteRepository.save(client);
        return MapperUtils.DtoFromEntity(client,ClienteDTO.class);
    }

    @Override
    @Transactional
    public Optional<ClienteDTO> update(ClienteDTO cliente, Long id) {
        if (clienteRepository.findById(id).isPresent()) {
            Cliente client = MapperUtils.EntityFromDto(cliente, Cliente.class);
            client = clienteRepository.save(client);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(client, ClienteDTO.class));
        } else {
            return null;
        } 

    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        clienteRepository.deleteAll();
    }

//    @Override
//    public Optional<ClienteDTO> findByCedula(String cedula) {
//        return ServiceConvertionHelper.oneToOptionalDto(clienteRepository.findByCedula(cedula),ClienteDTO.class);
//    }

}
