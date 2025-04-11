package br.com.ecommerce.entities.user;
import java.time.LocalDate;
import java.util.Set;
import br.com.ecommerce.entities.endereco.EnderecoEntity;
import br.com.ecommerce.tipos.TipoPessoa;

import javax.persistence.*;

@Entity
@Table(name = "pessoa")
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "cpf" ,nullable = false,unique = true)
    private String cpf;

    @Column(name = "dataDeNascimento" ,nullable = false)
    private LocalDate dataDeNascimento;

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

    @OneToMany
    @JoinTable (
        name = "pessoa_endereco",
        joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id")
    )

    private Set<EnderecoEntity> enderecos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Set<EnderecoEntity> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<EnderecoEntity> enderecos) {
        this.enderecos = enderecos;
    }



}
