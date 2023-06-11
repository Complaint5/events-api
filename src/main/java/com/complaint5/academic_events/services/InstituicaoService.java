package com.complaint5.academic_events.services;

import com.complaint5.academic_events.models.Instituicao;
import com.complaint5.academic_events.repositories.InstituicaoRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstituicaoService {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    public Instituicao findById(UUID cod_instituicao) {
        Optional<Instituicao> instituicao = this.instituicaoRepository.findById(cod_instituicao);
        return instituicao.orElseThrow(() -> {
            return new RuntimeException(
                    "Instituição não encontrado! Codigo: " + cod_instituicao + " Tipo: " + Instituicao.class.getName()
            );
        });
    }

    public List<Instituicao> findAll() {
        return this.instituicaoRepository.findAll();
    }

    @Transactional
    public Instituicao create(Instituicao instituicao) {
        instituicao.setCod_instituicao(null);
        //instituicao.getEndereco().setCod_endereco(null);
        return this.instituicaoRepository.save(instituicao);
    }
    
    @Transactional
    public Instituicao update(Instituicao instituicao){
        Instituicao newInstituicao = this.findById(instituicao.getCod_instituicao());
        newInstituicao.setNome(instituicao.getNome());
        newInstituicao.setSigla(instituicao.getSigla());
        //newInstituicao.setEndereco(instituicao.getEndereco());
        return this.instituicaoRepository.save(newInstituicao);
    }
    
    @Transactional
    public void delete(UUID cod_instituicao){
        this.findById(cod_instituicao);
        try {
            this.instituicaoRepository.deleteById(cod_instituicao);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois à entidades relacionadas!");
        }
    }
}
