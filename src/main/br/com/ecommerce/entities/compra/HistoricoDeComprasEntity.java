package br.com.ecommerce.entities.compra;



import br.com.ecommerce.tipos.*;

import javax.persistence.*;

@Entity(name = "historicoDeCompras ")
public class HistoricoDeComprasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "totalDaCompra" ,nullable = false)
    private double total;


    @Column(name = "nomeCliente" ,nullable = false)
    private String nomeCliente;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    @Column(name = "quantidadeComprada", nullable = false)
    private int quantidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoUser", nullable = false)
    private TipoPessoa tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoDeFrete", nullable = false)
    private TipoDeFrete tipoDeFrete;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoCompra", nullable = false)
    private TipoCompra tipoCompra;


    @Enumerated(EnumType.STRING)
    @Column(name = "tipoProduto", nullable = false)
    private TipoProduto tipoProduto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoRecebimento", nullable = false)
    private TipoRecebimento tipoRecebimento;

    public TipoRecebimento getTipoRecebimento() {
        return tipoRecebimento;
    }

    public void setTipoRecebimento(TipoRecebimento tipoRecebimento) {
        this.tipoRecebimento = tipoRecebimento;
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

    public void setTotal(double total) {
        this.total = total;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }



    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }

    public TipoDeFrete getTipoDeFrete() {
        return tipoDeFrete;
    }

    public void setTipoDeFrete(TipoDeFrete tipoDeFrete) {
        this.tipoDeFrete = tipoDeFrete;
    }

    public TipoCompra getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(TipoCompra tipoCompra) {
        this.tipoCompra = tipoCompra;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
}
