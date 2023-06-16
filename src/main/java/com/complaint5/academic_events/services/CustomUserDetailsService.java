package com.complaint5.academic_events.services;

import com.complaint5.academic_events.models.Usuario;
import com.complaint5.academic_events.repositories.UsuarioRepository;
import com.complaint5.academic_events.security.UserSpringSecurity;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByEmail(email);
        if (Objects.isNull(usuario)) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + email);
        }
        return new UserSpringSecurity(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getProfiles());
    }
}
