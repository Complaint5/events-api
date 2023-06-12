package com.complaint5.academic_events.controllers;

import com.complaint5.academic_events.models.Instituicao;
import com.complaint5.academic_events.models.Instituicao.CreateInstituicao;
import com.complaint5.academic_events.models.Instituicao.UpdateInstituicao;
import com.complaint5.academic_events.services.InstituicaoService;
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
@RequestMapping("/instituicao")
@Validated
public class InstituicaoController {

    @Autowired
    private InstituicaoService instituicaoService;

    @GetMapping("/{id}")
    public ResponseEntity<Instituicao> findById(@PathVariable UUID id) {
        return new ResponseEntity(instituicaoService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Instituicao>> findAll() {
        return new ResponseEntity(instituicaoService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    @Validated(CreateInstituicao.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Instituicao instituicao) {
        instituicaoService.create(instituicao);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Validated(UpdateInstituicao.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Instituicao instituicao, @PathVariable UUID id) {
        instituicao.setId(id);
        instituicaoService.update(instituicao);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        instituicaoService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
