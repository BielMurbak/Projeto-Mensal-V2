package br.com.ecommerce.entities.produto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tenis")
public class TenisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public TenisEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "tenis")
    private List<ProdutoEntity> produtos;

    public List<ProdutoEntity> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoEntity> produtos) {
        this.produtos = produtos;
    }
}
