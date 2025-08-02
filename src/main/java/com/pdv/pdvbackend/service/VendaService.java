package com.pdv.pdvbackend.service;

import com.pdv.pdvbackend.dto.VendaRequestDTO;
import com.pdv.pdvbackend.model.VendaModel;
import com.pdv.pdvbackend.model.ProdutoModel;
import com.pdv.pdvbackend.model.VendaItemModel;
import com.pdv.pdvbackend.repository.VendaRepository;
import com.pdv.pdvbackend.repository.ProdutoRepository;
import com.pdv.pdvbackend.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Transactional
    public VendaModel criarVenda(VendaRequestDTO request) {
        VendaModel venda = new VendaModel();
        venda.setDataVenda(LocalDateTime.now());
        venda.setObservacoes(request.getObservacoes());

        List<VendaItemModel> itensVenda = request.getItens().stream().map(itemRequest -> {
            ProdutoModel produto = produtoRepository.findById(itemRequest.getProdutoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com o ID: " + itemRequest.getProdutoId()));

            if (produto.getEstoque() < itemRequest.getQuantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            VendaItemModel vendaItem = new VendaItemModel();
            vendaItem.setVenda(venda);
            vendaItem.setProduto(produto);
            vendaItem.setQuantidade(itemRequest.getQuantidade());
            vendaItem.setPrecoUnitario(produto.getPrecoVenda());

            // Atualiza o estoque do produto
            produto.setEstoque(produto.getEstoque() - itemRequest.getQuantidade());
            produtoRepository.save(produto);

            return vendaItem;
        }).collect(Collectors.toList());

        venda.setItens(itensVenda);
        venda.calcularValorTotal();
        vendaRepository.save(venda);
        return venda;
    }

    public List<VendaModel> listarVendas() {
        return vendaRepository.findAll();
    }
}