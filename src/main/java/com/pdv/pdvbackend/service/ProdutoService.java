package com.pdv.pdvbackend.service;

import com.pdv.pdvbackend.model.ProdutoModel; // ALTERADO: Deve ser ProdutoModel
import com.pdv.pdvbackend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Indica que esta classe é um componente de serviço gerenciado pelo Spring
public class ProdutoService {

    @Autowired // Injeta uma instância de ProdutoRepository
    private ProdutoRepository produtoRepository;

    // Método para listar todos os produtos
    public List<ProdutoModel> listarTodosProdutos() { // ALTERADO: Tipo de retorno ProdutoModel
        return produtoRepository.findAll();
    }

    // Método para buscar um produto por ID
    public Optional<ProdutoModel> buscarProdutoPorId(Long id) { // ALTERADO: Tipo de retorno ProdutoModel
        return produtoRepository.findById(id);
    }

    // Método para salvar um novo produto ou atualizar um existente
    public ProdutoModel salvarProduto(ProdutoModel produto) { // ALTERADO: Parâmetro e tipo de retorno ProdutoModel
        return produtoRepository.save(produto);
    }

    // Método para deletar um produto por ID
    public void deletarProduto(Long id) {
        // É uma boa prática verificar se o produto existe antes de tentar deletar
        if (!produtoRepository.existsById(id)) {
            throw new IllegalArgumentException("Produto com ID " + id + " não encontrado para deleção.");
        }
        produtoRepository.deleteById(id);
    }

    // Método para atualizar um produto existente
    public ProdutoModel atualizarProduto(Long id, ProdutoModel produtoAtualizado) { // ALTERADO: Parâmetro e tipo de retorno ProdutoModel
        // Verifica se o produto existe antes de atualizar
        return produtoRepository.findById(id)
                .map(produtoExistente -> {
                    produtoExistente.setNome(produtoAtualizado.getNome());
                    produtoExistente.setPrecoVenda(produtoAtualizado.getPrecoVenda()); // ALTERADO: Usar setPrecoVenda()
                    produtoExistente.setEstoque(produtoAtualizado.getEstoque());     // ALTERADO: Usar setEstoque()
                    produtoExistente.setCodigoBarras(produtoAtualizado.getCodigoBarras()); // Adicionado (se houver no DTO)
                    produtoExistente.setSku(produtoAtualizado.getSku()); // Adicionado (se houver no DTO)
                    return produtoRepository.save(produtoExistente);
                })
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + id)); // Lança exceção se não encontrar
    }

    // Se você tiver um método para busca por termo (para o PDV)
    // public List<ProdutoModel> buscarProdutosPorTermo(String termo) {
    //     // Implemente a lógica de busca aqui
    //     return produtoRepository.findByNomeContainingIgnoreCase(termo); // Exemplo
    // }
}