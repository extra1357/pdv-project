package com.pdv.pdvbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VendaRequestDTO {

    private String observacoes;

    @NotNull(message = "A lista de itens não pode ser nula")
    private List<ItemVendaRequestDTO> itens;

    @Getter
    @Setter
    public static class ItemVendaRequestDTO {
        @NotNull(message = "O ID do produto não pode ser nulo")
        private Long produtoId;
        @NotNull(message = "A quantidade não pode ser nula")
        private Integer quantidade;
    }
}