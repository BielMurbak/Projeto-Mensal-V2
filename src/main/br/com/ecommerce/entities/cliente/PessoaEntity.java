package br.com.ecommerce.entities.cliente;
import java.util.Set;
import br.com.ecommerce.entities.endereco.EnderecoEntity;

import javax.persistence.*;

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
    private long dataDeNascimento;

    @OneToMany
    @JoinTable (
        name = "pessoa_endereco",
        joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id")
    )
    private Set<EnderecoEntity> enderecos;

}
