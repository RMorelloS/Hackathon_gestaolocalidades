package com.fiap.postech.gestaolocalidades.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Predio {
    @Id
    private UUID idPredio;
    @ManyToOne
    @JoinColumn(name="idLocalidade", referencedColumnName = "idLocalidade")
    private Localidade localidade;
    @NotBlank(message="Nome do prédio não pode estar vazio")
    private String nomePredio;


}
