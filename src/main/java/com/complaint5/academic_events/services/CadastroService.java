package com.complaint5.academic_events.services;

import com.complaint5.academic_events.models.Cadastro;
import com.complaint5.academic_events.repositories.CadastroRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroService {

    @Autowired
    private CadastroRepository cadastroRepository;

    public Cadastro findById(UUID cod_cadastro) {
        Optional<Cadastro> cadastro = this.cadastroRepository.findById(cod_cadastro);
        return cadastro.orElseThrow(() -> {
            return new RuntimeException(
                    "Cadastro não encontrado! Codigo: " + cod_cadastro + " Tipo: " + Cadastro.class.getName()
            );
        });
    }

    public List<Cadastro> findAll() {
        return cadastroRepository.findAll();
    }

    @Transactional
    public Cadastro create(Cadastro cadastro) {
        cadastro.setCod_cadastro(null);
        return this.cadastroRepository.save(cadastro);
    }

    @Transactional
    public Cadastro update(Cadastro cadastro) {
        Cadastro newCadastro = this.findById(cadastro.getCod_cadastro());
        newCadastro.setTipo_cadastro(cadastro.getTipo_cadastro());
        return this.cadastroRepository.save(newCadastro);
    }

    @Transactional
    public void delete(UUID cod_cadastro) {/////////////////
        this.findById(cod_cadastro);
        try {
            this.cadastroRepository.deleteById(cod_cadastro);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois à entidades relacionadas!");
        }
    }
}
