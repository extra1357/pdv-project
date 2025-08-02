package com.pdv.pdvbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ItemVendaResponseDTO {
    private Long id;
    private ProdutoResponseDTO produto; // Alterado para o novo DTO
    private int quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotalItem;
}