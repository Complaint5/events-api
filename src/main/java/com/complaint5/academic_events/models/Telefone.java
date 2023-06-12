package com.complaint5.academic_events.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "telefone", schema = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true, nullable = false)
    private Integer telefone_celular;

    @Column(unique = true)
    private Integer telefone_fixo;

    @OneToOne(mappedBy = "telefone")
    @JoinColumn(unique = true, nullable = false)
    @JsonIgnore
    private Usuario usuario;
}
