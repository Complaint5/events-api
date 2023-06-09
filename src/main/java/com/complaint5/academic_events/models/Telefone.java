package com.complaint5.academic_events.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private UUID cod_telefone;

    @Column(unique = true, nullable = false)
    @NotBlank
    private Integer telefone_celular;

    @Column(unique = true)
    private Integer telefone_fixo;
}