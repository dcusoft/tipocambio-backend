package com.bcp.tipocambio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcp.tipocambio.model.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long> {
}
