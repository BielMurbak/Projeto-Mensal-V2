package br.com.ecommerce.system;

import br.com.ecommerce.entities.cliente.AdministradorEntity;
import br.com.ecommerce.entities.cliente.PessoaEntity;
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
            pessoa.setNome("Adm");
            pessoa.setIdade(18);
            pessoa.setCpf("123.456.789-01");
            pessoa.setDataDeNascimento(LocalDate.of(2006, 7, 25)); // Ano, mês, dia

            // Salvar pessoa no banco
            session.save(pessoa);

            // Criar Administrador
            AdministradorEntity admin = new AdministradorEntity();
            admin.setSenha("adm2425%");
            admin.setPessoaEntity(pessoa);

            // Salvar administrador no banco
            session.save(admin);

            // Commit da transação
            session.getTransaction().commit();

            System.out.println("Administrador criado com sucesso!");

            // Fechar tudo
            session.close();
            sessionFactory.close();

        } catch (Exception e) {
            System.err.println("Falha ao conectar ou salvar no banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
