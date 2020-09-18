/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.una.tramites.dto.AuthenticationRequest;
import org.una.tramites.dto.AuthenticationResponse;
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.entities.Usuario;
import org.una.tramites.jwt.JwtProvider;
import org.una.tramites.repositories.IUsuarioRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author ivana
 */
@Service
public class AuthenticationServiceImplemnetation implements IAuthenticationService, UserDetailsService{

    @Autowired
    private IUsuarioRepository usuarioRepository;
    
    @Autowired
    private JwtProvider jwtProvider;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getCedula(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        Optional<Usuario> usuario = findByCedula(authenticationRequest.getCedula());

        if (usuario.isPresent()) {
            authenticationResponse.setJwt(jwtProvider.generateToken(authenticationRequest));
            UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuario.get(), UsuarioDTO.class);
            authenticationResponse.setUsuario(usuarioDto);
            List<PermisoOtorgadoDTO> permisosOtorgadosDto = MapperUtils.DtoListFromEntityList(usuario.get().getPermisos(), PermisoOtorgadoDTO.class);
            authenticationResponse.setPermisos(permisosOtorgadosDto);

            return authenticationResponse;
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = findByCedula(username);
        if (usuarioBuscado.isPresent()) {
            Usuario usuario = usuarioBuscado.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            for(PermisoOtorgado permiso : usuario.getPermisos()){
                authorities.add(new SimpleGrantedAuthority(permiso.getPermiso().getCodigo()));
            }
            for(GrantedAuthority aut : authorities){
                System.out.println(aut);
            }
            //List<GrantedAuthority> roles = new ArrayList<>();
            //roles.add(new SimpleGrantedAuthority("ADMIN"));
            UserDetails userDetails = new User(usuario.getCedula(), usuario.getPasswordEncriptado(), authorities);
            return userDetails;
        } else {
            System.out.println("loadUserByUserName: fail");
            return null;
        }
    }

    @Override
    public Optional<Usuario> findByCedula(String cedula) {
        return Optional.ofNullable(usuarioRepository.findByCedula(cedula));
    }
    
}
