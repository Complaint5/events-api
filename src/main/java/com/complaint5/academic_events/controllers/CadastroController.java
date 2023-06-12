package com.complaint5.academic_events.controllers;

import com.complaint5.academic_events.models.Cadastro;
import com.complaint5.academic_events.models.Cadastro.CreateCadastro;
import com.complaint5.academic_events.models.Cadastro.UpdateCadastro;
import com.complaint5.academic_events.services.CadastroService;
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
@RequestMapping("/cadastro")
@Validated
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @GetMapping("/{id}")
    public ResponseEntity<Cadastro> findById(@PathVariable UUID id) {
        return new ResponseEntity(cadastroService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Cadastro>> findAll() {
        return new ResponseEntity(cadastroService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    @Validated(CreateCadastro.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Cadastro cadastro) {
        cadastroService.create(cadastro);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Validated(UpdateCadastro.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Cadastro cadastro, @PathVariable UUID id) {
        cadastro.setId(id);
        cadastroService.update(cadastro);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        cadastroService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
