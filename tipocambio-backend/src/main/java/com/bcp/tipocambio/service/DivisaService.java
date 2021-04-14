package com.bcp.tipocambio.service;

import io.reactivex.Single;

import java.util.List;

import com.bcp.tipocambio.model.Cambio;
import com.bcp.tipocambio.model.Divisa;

public interface DivisaService {
    Single<List<Divisa>> listarDivisas();
    Single<Divisa> registrarDivisa(Divisa request);
    Single<Cambio> registrarCambio(Cambio request);
}
