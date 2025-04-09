package br.com.ecommerce.system;


import br.com.ecommerce.entities.cliente.AdministradorEntity;
import br.com.ecommerce.entities.cliente.PessoaEntity;
import br.com.ecommerce.repository.AdministradorRepository;
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
            PessoaEntity pessoa = new PessoaEntity();

            pessoa.setNome("adm");
            pessoa.setCpf("123.456.789-00");
            pessoa.setDataDeNascimento(LocalDate.of(2006, 6, 25));
            pessoaRepository.salvar(pessoa);

            AdministradorRepository administradorRepository = new AdministradorRepository();
            AdministradorEntity adm = new AdministradorEntity();

            adm.setSenha("adm2406@");
            adm.setPessoaEntity(pessoa);
            administradorRepository.salvar(adm);


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
