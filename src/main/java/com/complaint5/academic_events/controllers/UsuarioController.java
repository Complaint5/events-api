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
    
    @GetMapping("/{cod_usuario}")
    public ResponseEntity<Usuario> findById(@PathVariable UUID cod_usuario) {
        return new ResponseEntity(usuarioService.findById(cod_usuario), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> findAll() {
        return new ResponseEntity(usuarioService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    @Validated(CreateUsuario.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Usuario usuario) {
        usuarioService.create(usuario);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{cod_usuario}")
    @Validated(UpdateUsuario.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Usuario usuario, @PathVariable UUID cod_usuario) {
        usuario.setCod_usuario(cod_usuario);
        usuarioService.update(usuario);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{cod_usuario}")
    public ResponseEntity<Void> delete(@PathVariable UUID cod_usuario) {
        usuarioService.delete(cod_usuario);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
