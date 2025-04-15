package br.com.ecommerce.entities.user;

import javax.persistence.*;

@Entity(name = "clienteAtacado")
public class ClienteAtacadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "desconto",nullable = false)
    private double desconto;

    @Column(name = "senha", nullable = false)
        private int senha;

    public ClienteAtacadoEntity(int senha, double desconto, Long id) {
        this.senha = senha;
        this.desconto = desconto;
        this.id = id;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoaEntity;
}
