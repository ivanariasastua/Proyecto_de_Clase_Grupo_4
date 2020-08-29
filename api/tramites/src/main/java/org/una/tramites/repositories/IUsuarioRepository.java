/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author Ivan Josué Arias Astúa
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
    
    public Usuario findByCedulaAndPasswordEncriptado(String cedula, String passwordEncriptado);

    public List<Usuario> findByCedulaContaining(String cedula);

    public List<Usuario> findByNombreCompletoContainingIgnoreCase(String nombreCompleto);
    
    @Query("select u from Usuario u where UPPER(u.nombreCompleto) like CONCAT('%', UPPER(:nombreCompleto), '%')")
    public Usuario findNombreCompletoWithLikeSQL(@Param("nombreCompleto")String nombreCompleto);

    @Query("select u from Usuario u where u.cedula = :cedula")
    public Usuario findByCedula(@Param("cedula")String cedula);
    
    public List<Usuario> findByDepartamentoId(Long id);

    @Query("SELECT u FROM Usuario u LEFT JOIN u.departamento d WHERE u.esJefe=1 AND d.id=:id")
    public Usuario findJefeByDepartamento(Long id);

}
