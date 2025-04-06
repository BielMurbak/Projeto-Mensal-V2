package br.com.ecommerce.entities.cliente;


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

    @Column(name = "data de nascimento" ,nullable = false)
    private long dataDeNascimento;

    @ManyToMany
    @JoinColumn(name = "endereço_id")
    private EnderecoEntity enderecoEntity;

    @OneToOne
    @JoinColumn(name = "clienteVarejo_id")
    private ClienteVarejoEntity  clienteVarejoEntity;

    @OneToOne
    @JoinColumn(name = "clienteAtacado_id")
    private ClienteAtacadoEntity clienteAtacadoEntity;

    @OneToOne
    @JoinColumn(name = "administrador_id")
    private AdministradorEntity administradorEntity;

}
