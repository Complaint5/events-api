package com.complaint5.academic_events.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instituicao", schema = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, updatable = false, nullable = false)
    @NotBlank
    private UUID cod_instituicao;

    @Column(length = 258, unique = true, nullable = false)
    @NotBlank
    @Size(min = 1, max = 258)
    private String nome;

    @Column(length = 32, unique = true, nullable = false)
    @NotBlank
    @Size(min = 1, max = 32)
    private String sigla;

    @OneToOne
    @JoinColumn(unique = true, nullable = false)
    @NotBlank
    private Endereco endereco;
}
