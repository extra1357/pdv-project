package com.pdv.pdvbackend.controller;

import com.pdv.pdvbackend.dto.VendaRequestDTO;
import com.pdv.pdvbackend.model.VendaModel;
import com.pdv.pdvbackend.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaModel> criarVenda(@Valid @RequestBody VendaRequestDTO request) {
        VendaModel novaVenda = vendaService.criarVenda(request);
        return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
    }

    @GetMapping
    public List<VendaModel> listarVendas() {
        return vendaService.listarVendas();
    }
}