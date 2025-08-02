package com.pdv.pdvbackend.service;

import com.pdv.pdvbackend.dto.ProdutoResponseDTO;
import com.pdv.pdvbackend.exceptions.CustomNotFoundException;
import com.pdv.pdvbackend.model.ProdutoModel;
import com.pdv.pdvbackend.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoResponseDTO> listarProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(this::mapearParaProdutoResponseDTO)
                .collect(Collectors.toList());
    }

    public ProdutoResponseDTO salvarProduto(ProdutoResponseDTO produtoDTO) {
        ProdutoModel produto = new ProdutoModel();
        produto.setNome(produtoDTO.getNome());
        produto.setPrecoVenda(produtoDTO.getPrecoVenda());
        produto.setEstoque(produtoDTO.getEstoque());
        produto.setCodigoBarras(produtoDTO.getCodigoBarras());
        produto.setSku(produtoDTO.getSku());
        ProdutoModel produtoSalvo = produtoRepository.save(produto);
        return mapearParaProdutoResponseDTO(produtoSalvo);
    }
    
    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new CustomNotFoundException("Produto não encontrado.");
        }
        produtoRepository.deleteById(id);
    }

    public ProdutoResponseDTO buscarProdutoPorId(Long id) {
        ProdutoModel produto = produtoRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Produto não encontrado com o ID: " + id));
        return mapearParaProdutoResponseDTO(produto);
    }

    public ProdutoResponseDTO buscarPorCodigoBarras(String codigoBarras) {
        ProdutoModel produto = produtoRepository.findByCodigoBarras(codigoBarras)
                .orElseThrow(() -> new CustomNotFoundException("Produto não encontrado com o código de barras: " + codigoBarras));
        return mapearParaProdutoResponseDTO(produto);
    }

    private ProdutoResponseDTO mapearParaProdutoResponseDTO(ProdutoModel produto) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO(produto);
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPrecoVenda(produto.getPrecoVenda());
        dto.setEstoque(produto.getEstoque());
        dto.setCodigoBarras(produto.getCodigoBarras());
        dto.setSku(produto.getSku());
        return dto;
    }
}