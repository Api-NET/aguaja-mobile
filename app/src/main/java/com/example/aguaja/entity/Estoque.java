package com.example.aguaja.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Estoque {
    private Integer id;
    private Double precoVenda;
    private Integer quatidade;
    private Produto produto;

    public Estoque() {
    }

    public Estoque(Integer id, Double precoVenda, Integer quatidade, Produto produto) {
        this.id = id;
        this.precoVenda = precoVenda;
        this.quatidade = quatidade;
        this.produto = produto;
    }
    public Estoque(Integer id, Double precoVenda, Integer quatidade) {
        this.id = id;
        this.precoVenda = precoVenda;
        this.quatidade = quatidade;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }



    public Integer getQuatidade() {
        return quatidade;
    }

    public void setQuatidade(Integer quatidade) {
        this.quatidade = quatidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "\nPreço: R$"+ getPrecoVenda() + "\n"
                + "Nome: "+ getProduto().getNome() + "\n"
                + "Litros: "+ getProduto().getLitros() + "L\n"
                + "Descrição: \n"+ getProduto().getDescricao();
    }
}
