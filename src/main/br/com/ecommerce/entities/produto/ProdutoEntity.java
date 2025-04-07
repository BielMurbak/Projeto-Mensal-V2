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

    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "codigo",nullable = false)
    private int codigo;

    @Column(name = "quantidade" ,nullable = false)
    private int quantidade;

    @Column(name = "preço" ,nullable = false)
    private double preco;

    @ManyToOne
    @JoinColumn(name = "tenis_id")
    private TenisEntity tenis;

    @OneToMany(mappedBy = "produto")
    private List<HistoricoDeComprasEntity> historicos = new ArrayList<>();


    public ProdutoEntity(Long id, String nome, int codigo, int quantidade, double preco) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.preco = preco;
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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



