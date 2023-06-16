package com.complaint5.academic_events.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Data;

@Entity
@Table(name = "usuario", schema = "events")
@Data
public class Usuario {

    public interface CreateUsuario {
    }

    public interface UpdateUsuario {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

    @Column(length = 128, nullable = false)
    @NotBlank(groups = {CreateUsuario.class, UpdateUsuario.class})
    @Size(groups = {CreateUsuario.class, UpdateUsuario.class}, min = 2, max = 128)
    private String nome;

    @Column(length = 64, nullable = false)
    @NotBlank(groups = {CreateUsuario.class, UpdateUsuario.class})
    @Size(groups = {CreateUsuario.class, UpdateUsuario.class}, min = 8, max = 64)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @Column(updatable = false, nullable = false)
    @NotNull(groups = {CreateUsuario.class, UpdateUsuario.class})
    private LocalDate data_de_nascimento;

    @Column(length = 11, updatable = false, nullable = false, unique = true)
    @NotBlank(groups = {CreateUsuario.class})
    @Size(groups = {CreateUsuario.class}, min = 11, max = 11)
    private String cpf;

    @Column(length = 9, nullable = false)
    @NotBlank(groups = {CreateUsuario.class, UpdateUsuario.class})
    @Size(groups = {CreateUsuario.class, UpdateUsuario.class}, min = 1, max = 9)
    private String genero;

    @Column(length = 100, nullable = false, unique = true)
    @NotBlank(groups = {CreateUsuario.class, UpdateUsuario.class})
    @Size(groups = {CreateUsuario.class, UpdateUsuario.class}, min = 5, max = 100)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, unique = true, name = "telefone_cod")
    @NotNull(groups = {CreateUsuario.class, UpdateUsuario.class})
    private Telefone telefone;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "cadastro_cod")
    @NotNull(groups = {CreateUsuario.class, UpdateUsuario.class})
    private Cadastro cadastro;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "instituicao_cod")
    @NotNull(groups = {CreateUsuario.class, UpdateUsuario.class})
    private Instituicao instituicao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, unique = true, name = "endereco_cod")
    @NotNull(groups = {CreateUsuario.class, UpdateUsuario.class})
    private Endereco endereco;
    
    @Column(name = "profile", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_profile", schema = "events")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> profiles = new HashSet();
    
    public Set<ProfileEnum> getProfiles() {
        return this.profiles.stream().map(x -> ProfileEnum.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(ProfileEnum profileEnum) {
        this.profiles.add(profileEnum.getCode());
    }
}
