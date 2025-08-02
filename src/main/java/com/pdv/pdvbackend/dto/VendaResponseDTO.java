package com.pdv.pdvbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class VendaResponseDTO {
    private Long id;
    private LocalDateTime dataHora;
    private String observacoes;
    private BigDecimal totalVenda;
    private List<ItemVendaResponseDTO> itens; // Alterado para o novo DTO
}