package com.bcp.tipocambio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcp.tipocambio.model.Divisa;

public interface DivisaRepository extends JpaRepository<Divisa, Long> {
    Divisa findByMonedaOrigenAndMonedaDestino(String monedaOrigen, String monedaDestino);
}
