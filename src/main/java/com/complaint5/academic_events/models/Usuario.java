package com.complaint5.academic_events.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario", schema = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, updatable = false, nullable = false)
    @NotBlank
    private UUID cod_usuario;

    @Column(length = 128, nullable = false)
    @NotBlank
    @Size(min = 2, max = 128)
    private String nome;

    @Column(length = 64, nullable = false)
    @NotBlank
    @Size(min = 8, max = 64)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String senha;

    @Column(updatable = false, nullable = false)
    @NotBlank
    private LocalDate data_de_nascimento;

    @Column(length = 11, updatable = false, nullable = false, unique = true)
    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @Column(length = 9, nullable = false)
    @NotBlank
    @Size(min = 1, max = 9)
    private String genero;

    @Column(length = 100, nullable = false, unique = true)
    @NotBlank
    @Size(min = 5, max = 100)
    private String email;

    @OneToOne
    @JoinColumn(nullable = false, unique = true, name = "fk_telefone")
    @NotBlank
    private Telefone telefone;

    @ManyToOne
    @JoinColumn(nullable = false, name = "fk_cadastro")
    @NotBlank
    private Cadastro cadastro;

    @ManyToOne
    @JoinColumn(nullable = false, name = "fk_instituicao")
    @NotBlank
    private Instituicao instituicao;

    @OneToOne
    @JoinColumn(nullable = false, name = "fk_endereco")
    @NotBlank
    private Endereco endereco;
}
