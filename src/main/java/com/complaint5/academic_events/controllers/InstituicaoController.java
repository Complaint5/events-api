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

    @GetMapping("/{cod_instituicao}")
    public ResponseEntity<Instituicao> findById(@PathVariable UUID cod_instituicao) {
        return new ResponseEntity(instituicaoService.findById(cod_instituicao), HttpStatus.OK);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Instituicao>> findAll(){
        return new ResponseEntity(instituicaoService.findAll(), HttpStatus.OK);
    }
    
    @PostMapping("/")
    @Validated(CreateInstituicao.class)
    public ResponseEntity<Void> create (@Valid @RequestBody Instituicao instituicao){
        instituicaoService.create(instituicao);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @PutMapping("/{cod_instituicao}")
    @Validated(UpdateInstituicao.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Instituicao instituicao, @PathVariable UUID cod_instituicao){
        instituicao.setCod_instituicao(cod_instituicao);
        instituicaoService.update(instituicao);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @DeleteMapping("/{cod_instituicao}")
    public ResponseEntity<Void> delete(@PathVariable UUID cod_instituicao){
        instituicaoService.delete(cod_instituicao);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
