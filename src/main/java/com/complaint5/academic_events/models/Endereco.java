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
@Table(name = "endereco", schema = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    
    public interface CreateEndereco{}
    public interface UpdateEndereco{}
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, updatable = false, nullable = false)
    private UUID cod_endereco;

    @Column(length = 258, nullable = false)
    @NotBlank(groups = {CreateEndereco.class, UpdateEndereco.class})
    @Size(groups = {CreateEndereco.class, UpdateEndereco.class}, min = 1, max = 258)
    private String pais;

    @Column(length = 258, nullable = false)
    @NotBlank(groups = {CreateEndereco.class, UpdateEndereco.class})
    @Size(groups = {CreateEndereco.class, UpdateEndereco.class}, min = 1, max = 258)
    private String estado;

    @Column(length = 258, nullable = false)
    @NotBlank(groups = {CreateEndereco.class, UpdateEndereco.class})
    @Size(groups = {CreateEndereco.class, UpdateEndereco.class}, min = 1, max = 258)
    private String cidade;

    @Column(length = 258, nullable = false)
    @NotBlank(groups = {CreateEndereco.class, UpdateEndereco.class})
    @Size(groups = {CreateEndereco.class, UpdateEndereco.class}, min = 1, max = 258)
    private String bairro;

    @Column(length = 258, nullable = false)
    @NotBlank(groups = {CreateEndereco.class, UpdateEndereco.class})
    @Size(groups = {CreateEndereco.class, UpdateEndereco.class}, min = 1, max = 258)
    private String rua;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private Integer cep;

    @Column()
    private String complemento;

    @OneToMany(mappedBy = "endereco")
    private List<Usuario> usuarios;
}
