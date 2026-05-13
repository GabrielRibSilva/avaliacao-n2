/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avaliacaoReinaldo.n2.service;

import com.avaliacaoReinaldo.n2.entity.Produto;
import com.avaliacaoReinaldo.n2.entity.Venda;
import com.avaliacaoReinaldo.n2.repository.ProdutoRepository;
import com.avaliacaoReinaldo.n2.repository.VendaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gabriel Ribeiro
 */
@Service
public class VendaService {
    
    private VendaRepository vendaRepository;
    private ProdutoRepository produtoRepository;
    
    public VendaService(VendaRepository vendaRepository, ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
    }

    public Venda salvar(Venda venda) {
        Produto produtoDB = produtoRepository.findById(venda.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        venda.setValorTotal(produtoDB.getPreco());
        venda.setDataVenda(LocalDateTime.now());
        
        return vendaRepository.save(venda);
    }

    public List<Venda> listarVendas() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> getVenda(Long id) {
        return vendaRepository.findById(id);
    }

    public Venda editarVenda(Venda venda) {
        //Essa validacao e mais somente para ajudar a recalcular o valor do carrinho caso sofra alteracao
        if (venda.getProduto() != null) {
            Produto produtoDB = produtoRepository.findById(venda.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
            venda.setValorTotal(produtoDB.getPreco());
        }
        return vendaRepository.save(venda);
    }

    public void deleteVenda(Long id) {
        vendaRepository.deleteById(id);
    }
    
}
