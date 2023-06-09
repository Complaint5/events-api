package com.complaint5.academic_events.repositories;

import com.complaint5.academic_events.models.Endereco;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID>{
    
}
