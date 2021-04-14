package com.bcp.tipocambio.service;

import com.bcp.tipocambio.model.Cambio;
import com.bcp.tipocambio.model.Divisa;
import com.bcp.tipocambio.repository.CambioRepository;
import com.bcp.tipocambio.repository.DivisaRepository;

import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisaServiceImpl implements DivisaService {

    @Autowired
    private DivisaRepository divisaRepository;

    @Autowired
    private CambioRepository cambioRepository;

    @Override
    public Single<List<Divisa>> listarDivisas() {
    	Single<List<Divisa>> singleObservable = Single.create(subscriber -> {
            subscriber.onSuccess(divisaRepository.findAll());
        });
        return singleObservable;
    }

    @Override
    public Single<Divisa> registrarDivisa(Divisa request) {
    	Single<Divisa> singleObservable = Single.create(subscriber -> {
            subscriber.onSuccess(divisaRepository.save(request));
        });
    	return singleObservable;
    }

    @Override
    public Single<Cambio> registrarCambio(Cambio request) {
    	Single<Cambio> singleObservable = Single.create(subscriber -> {
            Divisa divisa = divisaRepository.findByMonedaOrigenAndMonedaDestino(request.getMonedaOrigen(), request.getMonedaDestino());

            Double montoDestino = request.getMontoOrigen() * divisa.getTipoCambio();

            request.setTipoCambio(divisa.getTipoCambio());
            request.setMontoDestino(montoDestino);

            subscriber.onSuccess(cambioRepository.save(request));
        });
    	return singleObservable;
    }
}
