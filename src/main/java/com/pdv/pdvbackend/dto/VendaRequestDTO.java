package com.pdv.pdvbackend.dto;

import java.util.List;

public class VendaRequestDTO {
    private String observacoes;
    private List<ItemVendaRequestDTO> itens;

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public List<ItemVendaRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemVendaRequestDTO> itens) {
        this.itens = itens;
    }
}
