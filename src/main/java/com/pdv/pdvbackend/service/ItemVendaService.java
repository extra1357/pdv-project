package com.pdv.pdvbackend.service;

import com.pdv.pdvbackend.model.VendaItemModel;
import com.pdv.pdvbackend.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemVendaService {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    // Método para salvar um item de venda
    public VendaItemModel salvarItemVenda(VendaItemModel itemVenda) {
        return itemVendaRepository.save(itemVenda);
    }

    // Método para buscar um item de venda por ID
    public Optional<VendaItemModel> buscarItemVendaPorId(Long id) {
        return itemVendaRepository.findById(id);
    }

    // Outros métodos conforme a necessidade...
}