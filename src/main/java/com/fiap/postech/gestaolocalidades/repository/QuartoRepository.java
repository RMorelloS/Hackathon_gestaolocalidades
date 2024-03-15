package com.fiap.postech.gestaolocalidades.repository;

import com.fiap.postech.gestaolocalidades.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, UUID> {
}
