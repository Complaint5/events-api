package com.complaint5.academic_events.controllers;

import com.complaint5.academic_events.models.Cadastro;
import com.complaint5.academic_events.models.Instituicao;
import com.complaint5.academic_events.models.Usuario;
import com.complaint5.academic_events.services.CadastroService;
import com.complaint5.academic_events.services.InstituicaoService;
import com.complaint5.academic_events.services.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @Autowired
    CadastroService cadastroService;
    @Autowired
    InstituicaoService instituicaoService;
    @Autowired
    UsuarioService usuarioService;
    
    @PostMapping("/")
    public ResponseEntity<Cadastro> save(@RequestBody Cadastro cadastro){
        return new ResponseEntity<>(cadastroService.create(cadastro), HttpStatus.CREATED);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Cadastro>> find(){
        return new ResponseEntity<>(cadastroService.findAll(), HttpStatus.OK);
    }
    
    
    
    @DeleteMapping("/")
    public ResponseEntity<Cadastro> delete(@RequestBody Cadastro cadastro){
        cadastroService.delete(cadastro.getCod_cadastro());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    
    
    
    
    
    @GetMapping("/a")
    public ResponseEntity<List<Instituicao>> finda(){
        return new ResponseEntity<>(instituicaoService.findAll(), HttpStatus.OK);
    }
    
    @PostMapping("/a")
    public ResponseEntity<Instituicao> savea(@RequestBody Instituicao instituicao){
        return new ResponseEntity<>(instituicaoService.create(instituicao), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/a")
    public ResponseEntity<Instituicao> deletea(@RequestBody Instituicao instituicao){
        instituicaoService.delete(instituicao.getCod_instituicao());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    
    
    @PostMapping("/b")
    public ResponseEntity<Usuario> saveb(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.create(usuario), HttpStatus.CREATED);
    }
    
    @GetMapping("/b")
    public ResponseEntity<List<Usuario>> findb(){
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }
}
