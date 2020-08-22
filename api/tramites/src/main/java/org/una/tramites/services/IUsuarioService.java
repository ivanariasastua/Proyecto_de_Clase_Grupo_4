/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author cordo
 */
public interface IUsuarioService {

    public Optional<List<Usuario>> findAll();

    public Optional<Usuario> findById(Long id);

    public Optional<List<Usuario>> findByCedulaAproximate(String cedula);

    public Optional<List<Usuario>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);

    public Usuario create(Usuario usuario);

    public Optional<Usuario> update(Usuario usuario, Long id);

    public void delete(Long id);

    public void deleteAll();

    public Optional<Usuario> login(Usuario usuario); 
}
