package com.complaint5.academic_events.services;

import com.complaint5.academic_events.models.Instituicao;
import com.complaint5.academic_events.repositories.InstituicaoRepository;
import com.complaint5.academic_events.services.exceptions.DataBindingViolationException;
import com.complaint5.academic_events.services.exceptions.ObjectNotFoundException;
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
            return new ObjectNotFoundException(
                    "Instituição não encontrado! Codigo: " + cod_instituicao + " Tipo: " + Instituicao.class.getName()
            );
        });
    }

    public List<Instituicao> findAll() {
        return this.instituicaoRepository.findAll();
    }

    @Transactional
    public Instituicao create(Instituicao instituicao) {
        instituicao.setId(null);
        return this.instituicaoRepository.save(instituicao);
    }

    @Transactional
    public Instituicao update(Instituicao instituicao) {
        Instituicao newInstituicao = this.findById(instituicao.getId());
        newInstituicao.setNome(instituicao.getNome());
        newInstituicao.setSigla(instituicao.getSigla());
        return this.instituicaoRepository.save(newInstituicao);
    }

    @Transactional
    public void delete(UUID cod_instituicao) {
        Instituicao instituicao = this.findById(cod_instituicao);
        try {
            this.instituicaoRepository.delete(instituicao);
        } catch (Exception e) {
            throw new DataBindingViolationException("Não é possivel excluir pois à entidades relacionadas!");
        }
    }
}
