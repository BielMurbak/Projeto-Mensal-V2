package br.com.ecommerce.entities.produto;

import br.com.ecommerce.entities.compra.HistoricoDeComprasEntity;
import br.com.ecommerce.tipos.TipoPessoa;
import br.com.ecommerce.tipos.TipoProduto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoProduto tipo;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "quantidade" ,nullable = false)
    private int quantidade;

    @Column(name = "preco" ,nullable = false)
    private double preco;

    @Column(name = "codigo_produto", nullable = false, unique = true)
    private int codigoProduto;

    @OneToMany(mappedBy = "produto")
    private List<HistoricoDeComprasEntity> historicos = new ArrayList<>();

    public ProdutoEntity(String nome, TipoProduto tipo, int quantidade, double preco, int codigoProduto) {

        this.tipo = tipo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.codigoProduto = codigoProduto;
    }

    public ProdutoEntity() {
        // Construtor vazio para o Hibernate
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
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

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }
}



