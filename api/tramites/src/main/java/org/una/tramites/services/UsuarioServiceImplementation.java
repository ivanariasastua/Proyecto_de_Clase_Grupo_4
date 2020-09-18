
package org.una.tramites.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.entities.Usuario;
import org.una.tramites.jwt.JwtProvider;
import org.una.tramites.repositories.IUsuarioRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

@Service
public class UsuarioServiceImplementation implements IUsuarioService, UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private JwtProvider jwtProvider;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    private UsuarioDTO encriptarPassword(UsuarioDTO usuario) {
        String password = usuario.getPasswordEncriptado();
        if (!password.isBlank()) {
            usuario.setPasswordEncriptado(bCryptPasswordEncoder.encode(password));
        }
        return usuario;
    }
    
//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<Usuario>> findAll() {
//        return Optional.ofNullable(usuarioRepository.findAll());
//    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findAll() {
        return ServiceConvertionHelper.findList(usuarioRepository.findAll(), UsuarioDTO.class);
    }
    
//    @Override
//    @Transactional(readOnly = true)
//    public Optional<Usuario> findById(Long id) {
//        return usuarioRepository.findById(id);
//    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(usuarioRepository.findById(id), UsuarioDTO.class);
    }
    
//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<Usuario>> findByCedulaAproximate(String cedula) {
//        return Optional.ofNullable(usuarioRepository.findByCedulaContaining(cedula));
//    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByCedulaAproximate(String cedula) {
        return ServiceConvertionHelper.findList(usuarioRepository.findByCedulaContaining(cedula), UsuarioDTO.class);
    }
    
//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<Usuario>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
//        return Optional.ofNullable(usuarioRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto));
//    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
        return ServiceConvertionHelper.findList(usuarioRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto), UsuarioDTO.class);
    }

//    @Override
//    @Transactional
//    public Usuario create(Usuario usuario) {
//        encriptarPassword(usuario);
//        return usuarioRepository.save(usuario);
//    }
    
    @Override
    @Transactional
    public UsuarioDTO create(UsuarioDTO usuario) {
        usuario = encriptarPassword(usuario);
        Usuario user = MapperUtils.EntityFromDto(usuario, Usuario.class);
        user = usuarioRepository.save(user);
        return MapperUtils.DtoFromEntity(user, UsuarioDTO.class);
    }

//    @Override
//    @Transactional
//    public Optional<Usuario> update(Usuario usuario, Long id, Integer enc) {
//        if (usuarioRepository.findById(id).isPresent()) {
//            if(enc == 1)
//                encriptarPassword(usuario);
//            return Optional.ofNullable(usuarioRepository.save(usuario));
//        } else {
//            return null;
//        }
//    }
    
    @Override
    @Transactional
    public Optional<UsuarioDTO> update(UsuarioDTO usuario, Long id) {
        if (usuarioRepository.findById(id).isPresent()) {
            usuario = encriptarPassword(usuario);
            Usuario user = MapperUtils.EntityFromDto(usuario, Usuario.class);
            user = usuarioRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, UsuarioDTO.class));
        } else {
            return null;
        } 
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        usuarioRepository.deleteAll();
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<Usuario> login(Usuario usuario) {
//        return Optional.ofNullable(usuarioRepository.findByCedulaAndPasswordEncriptado(usuario.getCedula(), usuario.getPasswordEncriptado()));
//    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<Usuario>> findUsersByDepartamentoId(Long id) {
//        return Optional.ofNullable(usuarioRepository.findByDepartamentoId(id));
//    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findUsersByDepartamentoId(Long id) {
        return ServiceConvertionHelper.findList(usuarioRepository.findByDepartamentoId(id), UsuarioDTO.class);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<Usuario> findJefesDepartemento(Long id) {
//        return Optional.ofNullable(usuarioRepository.findJefeByDepartamento(id));
//    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findJefesDepartemento(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(Optional.ofNullable(usuarioRepository.findJefeByDepartamento(id)), UsuarioDTO.class);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<Usuario> findByCedula(String cedula) {
//        return Optional.ofNullable(usuarioRepository.findByCedula(cedula));
//    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByCedula(String cedula) {
        return ServiceConvertionHelper.oneToOptionalDto(Optional.ofNullable(usuarioRepository.findByCedula(cedula)), UsuarioDTO.class);
    }
    
//    private void encriptarPassword(Usuario usuario) {
//        String password = usuario.getPasswordEncriptado();
//        if (!password.isBlank()) {
//            usuario.setPasswordEncriptado(bCryptPasswordEncoder.encode(password));
//        }
//    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = Optional.ofNullable(usuarioRepository.findByCedula(username));
        if (usuarioBuscado.isPresent()) {
            Usuario usuario = usuarioBuscado.get();
            List<GrantedAuthority> roles = new ArrayList<>();

            usuario.getPermisos().forEach(permisoOtorgado -> {
                roles.add(new SimpleGrantedAuthority(permisoOtorgado.getPermiso().getCodigo()));
            });
            UserDetails userDetails = new User(usuario.getCedula(), usuario.getPasswordEncriptado(), roles);
            return userDetails;
        } else {
            return null;
        }
    }
}

//    @Override
//    public String login(AuthenticationRequest authenticationRequest) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getCedula(), authenticationRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return jwtProvider.generateToken(authenticationRequest);
//    }
//   
//    @Override
//    @Transactional(readOnly = true)
//    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
//
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getCedula(), authenticationRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//
//        Optional<Usuario> usuario = findByCedula(authenticationRequest.getCedula());
//
//        if (usuario.isPresent()) {
//            authenticationResponse.setJwt(jwtProvider.generateToken(authenticationRequest));
//            UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuario.get(), UsuarioDTO.class);
//            authenticationResponse.setUsuario(usuarioDto);
//            List<PermisoOtorgadoDTO> permisosOtorgadosDto = MapperUtils.DtoListFromEntityList(usuario.get().getPermisos(), PermisoOtorgadoDTO.class);
//            authenticationResponse.setPermisos(permisosOtorgadosDto);
//
//            return authenticationResponse;
//        } else {
//            return null;
//        }
//    }




