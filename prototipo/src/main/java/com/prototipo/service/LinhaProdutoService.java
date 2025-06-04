package com.prototipo.service;

import com.prototipo.model.LinhaProduto;
import com.prototipo.repository.LinhaProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinhaProdutoService {

    private final LinhaProdutoRepository linhaProdutoRepository;

    public LinhaProdutoService(LinhaProdutoRepository linhaProdutoRepository) {
        this.linhaProdutoRepository = linhaProdutoRepository;
    }

    public LinhaProduto cadastrarLinhaProduto(LinhaProduto linhaProduto) {
        if (linhaProduto.getNome() == null || linhaProduto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da linha de produto é obrigatório.");
        }
        return linhaProdutoRepository.save(linhaProduto);
    }

    public Optional<LinhaProduto> buscarPorId(Integer id) {
        return linhaProdutoRepository.findById(id);
    }

    public List<LinhaProduto> listarTodasLinhasProduto() {
        return linhaProdutoRepository.findAll();
    }

    public LinhaProduto atualizarLinhaProduto(Integer id, LinhaProduto linhaProdutoDadosNovos) {
        return linhaProdutoRepository.findById(id).map(linhaProdutoExistente -> {
            linhaProdutoExistente.setNome(linhaProdutoDadosNovos.getNome());
            linhaProdutoExistente.setAtivo(linhaProdutoDadosNovos.getAtivo());
            return linhaProdutoRepository.save(linhaProdutoExistente);
        }).orElse(null);
    }

    public boolean excluirLinhaProduto(Integer id) {
        if (linhaProdutoRepository.existsById(id)) {
            linhaProdutoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}