package br.com.ecommerce.entities.cliente;


import javax.persistence.*;

@DiscriminatorValue("ADM")
@Entity(name = "Administrador")

public class AdministradorEntity  extends PessoaEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "senha", nullable = true)
    private String senha;

    public AdministradorEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public AdministradorEntity(Long id, String senha) {
        this.id = id;
        this.senha = senha;
    }

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoaEntity;

    public PessoaEntity getPessoaEntity() {
        return pessoaEntity;
    }

    public void setPessoaEntity(PessoaEntity pessoaEntity) {
        this.pessoaEntity = pessoaEntity;
    }
}
