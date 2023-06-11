package com.complaint5.academic_events.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cadastro", schema = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cadastro {
    
    public interface CreateCadastro{}
    public interface UpdateCadastro{}

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, updatable = false, nullable = false)
    private UUID cod_cadastro;

    @Column(length = 100, unique = true, nullable = false)
    @NotBlank(groups = {CreateCadastro.class, UpdateCadastro.class})
    @Size(groups = {CreateCadastro.class, UpdateCadastro.class}, min = 1, max = 100)
    private String tipo_cadastro;

    @OneToMany(mappedBy = "cadastro")
    private List<Usuario> usuarios;
}
