package com.pdv.pdvbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos") // Nome da tabela no banco de dados
@Data // Gera getters, setters, toString, equals e hashCode do Lombok
@NoArgsConstructor // Gera construtor sem argumentos do Lombok
@AllArgsConstructor // Gera construtor com todos os argumentos do Lombok
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @Column(nullable = false, precision = 10, scale = 2) // Precisão de 10 dígitos no total, 2 após a vírgula
    private BigDecimal precoVenda;

    @Column(unique = true, length = 20) // Ex: Código de barras (EAN-13)
    private String codigoBarras;

    @Column(unique = true, length = 50) // SKU (Stock Keeping Unit)
    private String sku;

    @Column(nullable = false)
    private Integer estoque; // Quantidade em estoque
}