package com.pdv.pdvbackend.service;

import com.pdv.pdvbackend.dto.VendaRequestDTO;
import com.pdv.pdvbackend.exceptions.EstoqueInsuficienteException;
import com.pdv.pdvbackend.exceptions.RecursoNaoEncontradoException;
import com.pdv.pdvbackend.model.ItemVendaModel;
import com.pdv.pdvbackend.model.ProdutoModel;
import com.pdv.pdvbackend.model.VendaModel;
import com.pdv.pdvbackend.repository.ItemVendaRepository;
import com.pdv.pdvbackend.repository.ProdutoRepository;
import com.pdv.pdvbackend.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        venda.setDataHora(LocalDateTime.now());
        venda.setObservacoes(request.getObservacoes());
        venda.setItens(new ArrayList<>());

        BigDecimal totalVenda = BigDecimal.ZERO;

        for (VendaRequestDTO.ItemVendaRequestDTO itemRequest : request.getItens()) {
            ProdutoModel produto = produtoRepository.findById(itemRequest.getProdutoId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID " + itemRequest.getProdutoId() + " não encontrado."));

            if (produto.getEstoque() < itemRequest.getQuantidade()) {
                throw new EstoqueInsuficienteException(
                        "Estoque insuficiente para o produto " + produto.getNome() + ". Estoque atual: " + produto.getEstoque()
                );
            }

            // Atualiza o estoque
            produto.setEstoque(produto.getEstoque() - itemRequest.getQuantidade());
            produtoRepository.save(produto);

            ItemVendaModel itemVenda = new ItemVendaModel();
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(itemRequest.getQuantidade());
            itemVenda.setPrecoUnitario(produto.getPrecoVenda());
            itemVenda.setPrecoTotalItem(produto.getPrecoVenda().multiply(BigDecimal.valueOf(itemRequest.getQuantidade())));
            itemVenda.setVenda(venda);
            venda.getItens().add(itemVenda);

            totalVenda = totalVenda.add(itemVenda.getPrecoTotalItem());
        }

        venda.setTotalVenda(totalVenda);

        VendaModel vendaSalva = vendaRepository.save(venda);
        itemVendaRepository.saveAll(vendaSalva.getItens());

        return vendaSalva;
    }

    public List<VendaModel> listarVendas() {
        return vendaRepository.findAll();
    }
}