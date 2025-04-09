package br.com.ecommerce.system;


import br.com.ecommerce.entities.cliente.PessoaEntity;
import br.com.ecommerce.repository.PessoaRepository;
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

            PessoaRepository pessoaRepository = new PessoaRepository();
            PessoaEntity novaPessoa = new PessoaEntity();

            novaPessoa.setNome("João");
            novaPessoa.setCpf("123.456.789-00");
            novaPessoa.setDataDeNascimento(LocalDate.of(1994, 5, 20));

            pessoaRepository.salvar(novaPessoa); // salva primeiro
            System.out.println("Pessoa salva com ID: " + novaPessoa.getId()); // agora sim: ID está preenchido


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
