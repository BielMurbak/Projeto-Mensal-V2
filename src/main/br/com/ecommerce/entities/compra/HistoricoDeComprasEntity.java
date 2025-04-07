package br.com.ecommerce.entities.compra;


import br.com.ecommerce.entities.produto.ProdutoEntity;

import javax.persistence.*;

@Entity(name = "historicoDeCompras ")
public class HistoricoDeComprasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    public HistoricoDeComprasEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
