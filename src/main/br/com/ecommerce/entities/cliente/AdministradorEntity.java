package br.com.ecommerce.entities.cliente;


import javax.persistence.*;

@Entity(name = "Administrador")
public class AdministradorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "senha", nullable = false, unique = true)
    private int senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public AdministradorEntity(Long id, int senha) {
        this.id = id;
        this.senha = senha;
    }

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoaEntity;
}
