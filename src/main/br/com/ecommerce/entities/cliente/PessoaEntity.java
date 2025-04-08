package br.com.ecommerce.entities.cliente;
import java.time.LocalDate;
import java.util.Set;
import br.com.ecommerce.entities.endereco.EnderecoEntity;

import javax.persistence.*;
import javax.xml.crypto.Data;

@Entity(name = "pessoa")
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "idade",nullable = false)
    private int idade;

    @Column(name = "cpf" ,nullable = false,unique = true)
    private String cpf;

    @Column(name = "dataDeNascimento" ,nullable = false)
    private LocalDate dataDeNascimento;

    @OneToMany
    @JoinTable (
        name = "pessoa_endereco",
        joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id")
    )
    private Set<EnderecoEntity> enderecos;

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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
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
