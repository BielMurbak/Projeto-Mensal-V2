package br.com.ecommerce.entities.compra;


import br.com.ecommerce.entities.produto.ProdutoEntity;
import br.com.ecommerce.entities.user.ClienteEntity;
import br.com.ecommerce.tipos.TipoPessoa;

import javax.persistence.*;

@Entity(name = "historicoDeCompras ")
public class HistoricoDeComprasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "totalDaCompra" ,nullable = false)
    private double total;

    @Column(name = "quantidadeComprada", nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoPessoa tipo;


    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }
}
