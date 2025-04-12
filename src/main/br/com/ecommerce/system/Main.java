package br.com.ecommerce.system;


import br.com.ecommerce.entities.endereco.EnderecoEntity;
import br.com.ecommerce.entities.produto.ProdutoEntity;
import br.com.ecommerce.entities.user.AdministradorEntity;
import br.com.ecommerce.entities.user.ClienteEntity;
import br.com.ecommerce.entities.user.PessoaEntity;

import br.com.ecommerce.repository.ProdutoRepository;
import br.com.ecommerce.tipos.TipoPessoa;
import br.com.ecommerce.tipos.TipoProduto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        try {
            // Carregar configuração do Hibernate
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");


            // Iniciar transação
            session.beginTransaction();

            //Criar um cliente
            ClienteEntity cliente = new ClienteEntity();
            PessoaEntity pessoaCliente = new PessoaEntity();
            EnderecoEntity endereco = new EnderecoEntity();

            AdministradorEntity adm = new AdministradorEntity();
            PessoaEntity pessoaAdm = new PessoaEntity();

           // Pessoa do cliente
            pessoaCliente.setTipo(TipoPessoa.CLIENTE_VAREJO);
            pessoaCliente.setNome("Adryan");
            pessoaCliente.setCpf("279.641.580-40");
            pessoaCliente.setDataDeNascimento(LocalDate.of(2006, 7, 25));

            // Endereço do cliente
            endereco.setRua("Bem-te-vi");
            endereco.setBairro("Morumbi");
            endereco.setMunicipio("Foz do Iguaçu");
            endereco.setCep("85850-111");
            endereco.setEstado("PR");

            cliente.setSenha("2507@");
            cliente.setPessoa(pessoaCliente);
            cliente.setEnderecoEntity(endereco);

            // Pessoa do administrador
            pessoaAdm.setTipo(TipoPessoa.ADMINISTRADOR);
            pessoaAdm.setNome("Biel");
            pessoaAdm.setCpf("093.777.222-01");
            pessoaAdm.setDataDeNascimento(LocalDate.of(2006, 7, 21));

            adm.setSenha("adm!!");
            adm.setPessoaEntity(pessoaAdm);

            session.save(cliente);
            session.save(adm);

            //Criar Produto

            ProdutoEntity produto = new ProdutoEntity("Nike Court Vision", TipoProduto.TENIS, 15, 499.99, 1);

             //Criar uma instância do repositório e salvar
            ProdutoRepository produtoRepository = new ProdutoRepository();
            session.save(produto);

            // Commit da transação
            session.getTransaction().commit();

            // Fechar tudo
            session.close();
            sessionFactory.close();

        } catch (Exception e) {
            System.err.println("Falha ao conectar ou salvar no banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
