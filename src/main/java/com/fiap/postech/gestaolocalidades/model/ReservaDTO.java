package com.fiap.postech.gestaolocalidades.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    private LocalDate dataInicio;
    private LocalDate dataFim;

    private UUID idQuarto;
}
