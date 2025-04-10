package br.com.ecommerce.entities.produto;

import br.com.ecommerce.entities.compra.HistoricoDeComprasEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "quantidade" ,nullable = false)
    private int quantidade;

    @Column(name = "preco" ,nullable = false)
    private double preco;

    @OneToMany(mappedBy = "produto")
    private List<HistoricoDeComprasEntity> historicos = new ArrayList<>();

    public ProdutoEntity(String nome, String tipo, int quantidade, double preco) {

        this.tipo = tipo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}



