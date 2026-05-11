/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avaliacaoReinaldo.n2.service;

import com.avaliacaoReinaldo.n2.entity.Produto;
import com.avaliacaoReinaldo.n2.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gabriel Ribeiro
 */
@Service
public class ProdutoService {
    
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> getProduto(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto editarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deleteProduto(Long id) {
        produtoRepository.deleteById(id);
    }
    
}
