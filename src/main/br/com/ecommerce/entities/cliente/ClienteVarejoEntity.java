package br.com.ecommerce.entities.cliente;

import javax.persistence.*;


@Entity(name = "clientevarejo")
public class ClienteVarejoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "senha", nullable = false, unique = true)
    private int senha;


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

    public ClienteVarejoEntity(Long id, int senha) {
        this.id = id;
        this.senha = senha;
    }


}
