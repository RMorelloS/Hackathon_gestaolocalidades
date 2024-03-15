package com.fiap.postech.gestaolocalidades.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Localidade {
    @Id
    private UUID idLocalidade;

    @NotBlank(message="Nome da localidade não pode estar vazio")
    private String nomeLocalidade;
    @NotBlank(message="Endereço da localidade não pode estar vazio")
    private String endereco;
    private List<String> listaAmenidades;
}
