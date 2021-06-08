package com.example.aguaja.entity;

import java.util.ArrayList;
import java.util.List;

public class Produto {
    private Integer id;
    private String descricao;
    private Double litros;
    private String nome;


    public Produto(Integer id, String descricao, Double litros, String nome){
        this.id = id;
        this.descricao = descricao;
        this.litros = litros;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getLitros() {
        return litros;
    }

    public void setLitros(Double litros) {
        this.litros = litros;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public static List<Produto> criaVarios() {
        List<Produto> produtos = new  ArrayList<Produto>();
        produtos.add(new Produto(1, "    Nitrato -> 5,15mg/L\n" +
                                                "    Sódio -> 4,180mg/L\n" +
                                                "    Sulfato -> 1,11mg/L\n" +
                                                "    Fluoreto -> 0,11mg/L\n", 20.0, "Indaiá" ));
        produtos.add(new Produto(2, "    Nitrato -> 4,5mg/L\n" +
                                                "    Sódio -> 5,1mg/L\n" +
                                                "    Sulfato -> 0,99mg/L\n" +
                                                "    Fluoreto -> 0,14mg/L\n", 10.0, "Ana Rosa" ));
        produtos.add(new Produto(3, "    Nitrato -> 6,5mg/L\n" +
                                                "    Sódio -> 4,4mg/L\n" +
                                                "    Sulfato -> 1,4mg/L\n" +
                                                "    Fluoreto -> 0,1mg/L\n", 5.0, "Frescale" ));
        produtos.add(new Produto(4, "    Nitrato -> 5,1mg/L\n" +
                                                "    Sódio -> 4,0mg/L\n" +
                                                "    Sulfato -> 1,3mg/L\n" +
                                                "    Fluoreto -> 1,1mg/L\n", 1.0, "Maceratti" ));
        produtos.add(new Produto(5, "    Nitrato -> 4,1mg/L\n" +
                                                    "    Sódio -> 3,0mg/L\n" +
                                                    "    Sulfato -> 2,3mg/L\n" +
                                                    "    Fluoreto -> 2,1mg/L\n", 5.0, "Aguativa" ));
        produtos.add(new Produto(6, "    Nitrato -> 5,15mg/L\n" +
                                                    "    Sódio -> 4,6mg/L\n" +
                                                    "    Sulfato -> 1,7mg/L\n" +
                                                    "    Fluoreto -> 0,2mg/L\n", 3.5, "Itaipu" ));
        produtos.add(new Produto(7, "    Nitrato -> 5,1mg/L\n" +
                                                    "    Sódio -> 4,1mg/L\n" +
                                                    "    Sulfato -> 2,7mg/L\n" +
                                                    "    Fluoreto -> 0,11mg/L\n", 3.5, "Fontana Oro" ));
        return produtos;
    }
    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", litros=" + litros +
                ", nome='" + nome + '\'' +
                '}';
    }
}
