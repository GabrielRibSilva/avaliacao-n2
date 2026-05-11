/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avaliacaoReinaldo.n2.controller;

import com.avaliacaoReinaldo.n2.entity.Produto;
import com.avaliacaoReinaldo.n2.service.ProdutoService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gabriel Ribeiro
 */
@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {
    
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<?> salvarProduto(@RequestBody Produto produto) {
        try {
            Produto novo = produtoService.salvar(produto);
            return ResponseEntity.ok(novo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping()
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable("id") Long id) {
        return produtoService.getProduto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> editarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        produto.setId(id); 
        return ResponseEntity.ok(produtoService.editarProduto(produto));
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id) {
        produtoService.deleteProduto(id);
    }
    
}
