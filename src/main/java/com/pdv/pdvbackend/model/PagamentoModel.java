package com.pdv.pdvbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pdv.pdvbackend.enums.FormaPagamento;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class PagamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    @JsonBackReference
    private VendaModel venda;
}