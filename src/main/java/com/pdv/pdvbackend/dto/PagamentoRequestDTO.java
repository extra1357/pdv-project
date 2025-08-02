package com.pdv.pdvbackend.dto;

import com.pdv.pdvbackend.enums.FormaPagamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PagamentoRequestDTO {

    @NotNull(message = "A forma de pagamento não pode ser nula")
    private FormaPagamento formaPagamento;

    @NotNull(message = "O valor do pagamento não pode ser nulo")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    private BigDecimal valor;
}