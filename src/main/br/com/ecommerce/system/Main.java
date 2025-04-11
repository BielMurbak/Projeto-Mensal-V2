package br.com.ecommerce.system;


import br.com.ecommerce.entities.user.AdministradorEntity;
import br.com.ecommerce.entities.user.PessoaEntity;

import br.com.ecommerce.tipos.TipoPessoa;
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

            // Criar Pessoa
            PessoaEntity pessoa = new PessoaEntity();
            pessoa.setNome("adm");
            pessoa.setCpf("12");
            pessoa.setDataDeNascimento(LocalDate.of(2006, 6, 25));
            pessoa.setTipo(TipoPessoa.ADMINISTRADOR);
            session.save(pessoa);

            // Criar Administrador com a pessoa já salva
            AdministradorEntity adm = new AdministradorEntity();
            adm.setSenha("adm2406@");
            adm.setPessoaEntity(pessoa);
            session.save(adm);

            //Criar Produto

            //ProdutoEntity produto = new ProdutoEntity("Nike Court Vision", "Tenis", 15, 499.99);

            // Criar uma instância do repositório e salvar
            //ProdutoRepository produtoRepository = new ProdutoRepository();
           // produtoRepository.salvar(produto);

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
