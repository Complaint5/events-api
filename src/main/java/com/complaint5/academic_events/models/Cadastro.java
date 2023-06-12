package com.complaint5.academic_events.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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
import lombok.Data;

@Entity
@Table(name = "cadastro", schema = "events")
@Data
public class Cadastro {

    public interface CreateCadastro {
    }

    public interface UpdateCadastro {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

    @Column(length = 100, unique = true, nullable = false)
    @NotBlank(groups = {CreateCadastro.class, UpdateCadastro.class})
    @Size(groups = {CreateCadastro.class, UpdateCadastro.class}, min = 1, max = 100)
    private String tipo_cadastro;

    @OneToMany(mappedBy = "cadastro")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Usuario> usuarios;
}
