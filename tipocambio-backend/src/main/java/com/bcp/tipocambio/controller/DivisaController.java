package com.bcp.tipocambio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.tipocambio.model.Cambio;
import com.bcp.tipocambio.model.Divisa;
import com.bcp.tipocambio.service.DivisaService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("tipocambio")
public class DivisaController {

    @Autowired
    private DivisaService divisaService;

    @GetMapping("/divisa")
    public Single<ResponseEntity<List<Divisa>>> listarDivisas() {
    	Single<ResponseEntity<List<Divisa>>> singleObservable = divisaService.listarDivisas()
                .subscribeOn(Schedulers.io())
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    	return singleObservable;
    }

    @PostMapping("/divisa")
    public Single<ResponseEntity<Divisa>> registrarDivisa(@RequestBody Divisa request) {
    	Single<ResponseEntity<Divisa>> singleObservable = divisaService.registrarDivisa(request)
                .subscribeOn(Schedulers.io())
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    	return singleObservable;
    }

    @PostMapping(value = "/cambio", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public Single<ResponseEntity<Cambio>> registrarCambio(@RequestBody Cambio request) {
    	Single<ResponseEntity<Cambio>> singleObservable = divisaService.registrarCambio(request)
                .subscribeOn(Schedulers.io())
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    	return singleObservable;
    }

}
