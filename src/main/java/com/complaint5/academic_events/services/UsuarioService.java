package com.complaint5.academic_events.services;

import com.complaint5.academic_events.models.Cadastro;
import com.complaint5.academic_events.models.Instituicao;
import com.complaint5.academic_events.models.Usuario;
import com.complaint5.academic_events.repositories.UsuarioRepository;
import com.complaint5.academic_events.services.exceptions.DataBindingViolationException;
import com.complaint5.academic_events.services.exceptions.ObjectNotFoundException;
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
    @Autowired
    private CadastroService cadastroService;
    @Autowired
    private InstituicaoService instituicaoService;

    public Usuario findById(UUID cod_usuario) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(cod_usuario);
        return usuario.orElseThrow(() -> {
            return new ObjectNotFoundException(
                    "Usuario não encontrado! Codigo: " + cod_usuario + " Tipo: " + Usuario.class.getName()
            );
        });
    }

    public List<Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    public List<Usuario> findByInstituicao_Id(UUID id) {
        return this.usuarioRepository.findByInstituicao_Id(id);
    }

    public List<Usuario> findByCadastro_Id(UUID id) {
        return this.usuarioRepository.findByCadastro_Id(id);
    }

    @Transactional
    public Usuario create(Usuario usuario) {
        Cadastro cadastro = cadastroService.findById(usuario.getCadastro().getId());
        Instituicao instituicao = instituicaoService.findById(usuario.getInstituicao().getId());
        usuario.setId(null);
        usuario.getTelefone().setId(null);
        usuario.getEndereco().setId(null);
        usuario.setCadastro(cadastro);
        usuario.setInstituicao(instituicao);
        return this.usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario update(Usuario usuario) {
        Usuario newUsuario = this.findById(usuario.getId());
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
        Usuario usuario = this.findById(cod_usuario);
        try {
            this.usuarioRepository.delete(usuario);
        } catch (Exception e) {
            throw new DataBindingViolationException("Não é possivel excluir pois à entidades relacionadas!");
        }
    }
}
