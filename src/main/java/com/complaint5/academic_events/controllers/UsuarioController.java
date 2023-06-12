package com.complaint5.academic_events.controllers;

import com.complaint5.academic_events.models.Usuario;
import com.complaint5.academic_events.models.Usuario.CreateUsuario;
import com.complaint5.academic_events.models.Usuario.UpdateUsuario;
import com.complaint5.academic_events.services.UsuarioService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/usuario")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable UUID id) {
        return new ResponseEntity(usuarioService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> findAll() {
        return new ResponseEntity(usuarioService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/instituicao/{id}")
    public ResponseEntity<List<Usuario>> findByInstituicao_Id(@PathVariable UUID id) {
        return new ResponseEntity(usuarioService.findByInstituicao_Id(id), HttpStatus.OK);
    }

    @GetMapping("/cadastro/{id}")
    public ResponseEntity<List<Usuario>> findByCadastro_Id(@PathVariable UUID id) {
        return new ResponseEntity(usuarioService.findByCadastro_Id(id), HttpStatus.OK);
    }

    @PostMapping("/")
    @Validated(CreateUsuario.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Usuario usuario) {
        usuarioService.create(usuario);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Validated(UpdateUsuario.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Usuario usuario, @PathVariable UUID id) {
        usuario.setId(id);
        usuarioService.update(usuario);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        usuarioService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
