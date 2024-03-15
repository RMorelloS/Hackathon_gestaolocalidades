package com.fiap.postech.gestaolocalidades.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Quarto {


    @Id
    private UUID idQuarto;

    @ManyToOne
    @JoinColumn(name="idPredio", referencedColumnName = "idPredio")
    private Predio predio;

    @NotBlank(message = "Campo tipo do quarto não pode estar vazio")
    private String tipoQuarto;

    @Min(value=1, message="Campo total de pessoas deve ser no mínimo 1")
    private Integer totalPessoas;

    @Min(value=1, message="Campo total de camas deve ser no mínimo 1")
    private Integer totalCamas;

    private List<String> outrosMoveis;

    @Min(value=50, message="Campo preço da diaria deve ser no mínimo 50")
    private Float precoDiaria;

    private LocalDate fimReserva;

    private LocalDate inicioReserva;


}
