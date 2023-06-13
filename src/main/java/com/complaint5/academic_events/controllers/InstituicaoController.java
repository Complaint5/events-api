package com.complaint5.academic_events.controllers;

import com.complaint5.academic_events.models.Instituicao;
import com.complaint5.academic_events.models.Instituicao.CreateInstituicao;
import com.complaint5.academic_events.models.Instituicao.UpdateInstituicao;
import com.complaint5.academic_events.services.InstituicaoService;
import jakarta.validation.Valid;
import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/instituicao")
@Validated
public class InstituicaoController {

    @Autowired
    private InstituicaoService instituicaoService;

    @GetMapping("/{id}")
    public ResponseEntity<Instituicao> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(instituicaoService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Instituicao>> findAll() {
        return ResponseEntity.ok().body(instituicaoService.findAll());
    }

    @PostMapping("/")
    @Validated(CreateInstituicao.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Instituicao instituicao) {
        instituicaoService.create(instituicao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(instituicao.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(UpdateInstituicao.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Instituicao instituicao, @PathVariable UUID id) {
        instituicao.setId(id);
        instituicaoService.update(instituicao);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        instituicaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
