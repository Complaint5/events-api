package com.complaint5.academic_events.repositories;

import com.complaint5.academic_events.models.Usuario;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
    
}
