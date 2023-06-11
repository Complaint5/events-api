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
@Table(name = "instituicao", schema = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instituicao {

    public interface CreateInstituicao{}
    public interface UpdateInstituicao{}
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, updatable = false, nullable = false)
    private UUID cod_instituicao;

    @Column(length = 258, unique = true, nullable = false)
    @NotBlank(groups = {CreateInstituicao.class, UpdateInstituicao.class})
    @Size(groups = {CreateInstituicao.class, UpdateInstituicao.class}, min = 1, max = 258)
    private String nome;

    @Column(length = 32, unique = true, nullable = false)
    @NotBlank(groups = {CreateInstituicao.class, UpdateInstituicao.class})
    @Size(groups = {CreateInstituicao.class, UpdateInstituicao.class}, min = 1, max = 32)
    private String sigla;

    //@OneToOne
    //@JoinColumn(unique = true, nullable = false)
    //@NotBlank
    //private Endereco endereco;
    @OneToMany(mappedBy = "instituicao")
    private List<Usuario> usuarios;
}
