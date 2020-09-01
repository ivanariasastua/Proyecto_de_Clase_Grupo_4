/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Cliente;

/**
 *
 * @author cordo
 */
public interface IClienteService {
    public Optional<List<Cliente>> findAll();

    public Optional<Cliente> findById(Long id);

    public Optional<List<Cliente>> findByCedulaAproximate(String cedula);

    public Optional<List<Cliente>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);

    public Cliente create(Cliente cliente);

    public Optional<Cliente> update(Cliente cliente, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<Cliente> findByCedula(String cedula);
}
