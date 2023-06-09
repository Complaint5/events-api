package com.complaint5.academic_events.repositories;

import com.complaint5.academic_events.models.Cadastro;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, UUID> {

}
