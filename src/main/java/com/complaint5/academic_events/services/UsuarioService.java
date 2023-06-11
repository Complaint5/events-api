package com.complaint5.academic_events.services;

import com.complaint5.academic_events.models.Usuario;
import com.complaint5.academic_events.repositories.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findById(UUID cod_usuario) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(cod_usuario);
        return usuario.orElseThrow(() -> {
            return new RuntimeException(
                    "Usuario não encontrado! Codigo: " + cod_usuario + " Tipo: " + Usuario.class.getName()
            );
        });
    }

    public List<Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    @Transactional
    public Usuario create(Usuario usuario) {
        usuario.setCod_usuario(null);
        usuario.getTelefone().setCod_telefone(null);
        usuario.getCadastro().setCod_cadastro(null);
        usuario.getInstituicao().setCod_instituicao(null);
        usuario.getEndereco().setCod_endereco(null);
        return this.usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario update(Usuario usuario) {
        Usuario newUsuario = this.findById(usuario.getCod_usuario());
        newUsuario.setNome(usuario.getNome());
        newUsuario.setSenha(usuario.getSenha());
        newUsuario.setData_de_nascimento(usuario.getData_de_nascimento());
        newUsuario.setCpf(usuario.getCpf());
        newUsuario.setGenero(usuario.getGenero());
        newUsuario.setEmail(usuario.getEmail());
        newUsuario.setTelefone(usuario.getTelefone());
        newUsuario.setCadastro(usuario.getCadastro());
        newUsuario.setInstituicao(usuario.getInstituicao());
        newUsuario.setEndereco(usuario.getEndereco());
        return this.usuarioRepository.save(newUsuario);
    }

    @Transactional
    public void delete(UUID cod_usuario) {/////////////////
        this.findById(cod_usuario);
        try {
            this.usuarioRepository.deleteById(cod_usuario);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois à entidades relacionadas!");
        }
    }
}
