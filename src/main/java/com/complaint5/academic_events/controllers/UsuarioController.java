package com.complaint5.academic_events.controllers;

import com.complaint5.academic_events.models.Usuario;
import com.complaint5.academic_events.models.Usuario.CreateUsuario;
import com.complaint5.academic_events.models.Usuario.UpdateUsuario;
import com.complaint5.academic_events.services.CadastroService;
import com.complaint5.academic_events.services.InstituicaoService;
import com.complaint5.academic_events.services.UsuarioService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private InstituicaoService instituicaoService;
    @Autowired
    private CadastroService cadastroService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(usuarioService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok().body(usuarioService.findAll());
    }

    @GetMapping("/instituicao/{id}")
    public ResponseEntity<List<Usuario>> findByInstituicao(@PathVariable UUID id) {
        this.instituicaoService.findById(id);
        return ResponseEntity.ok().body(usuarioService.findByInstituicao_Id(id));
    }

    @GetMapping("/cadastro/{id}")
    public ResponseEntity<List<Usuario>> findByCadastro(@PathVariable UUID id) {
        this.cadastroService.findById(id);
        return ResponseEntity.ok().body(usuarioService.findByCadastro_Id(id));
    }

    @PostMapping
    @Validated(CreateUsuario.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Usuario usuario) {
        usuarioService.create(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(UpdateUsuario.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Usuario usuario, @PathVariable UUID id) {
        usuario.setId(id);
        usuarioService.update(usuario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
