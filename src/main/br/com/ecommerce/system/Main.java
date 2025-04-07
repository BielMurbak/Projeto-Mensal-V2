package br.com.ecommerce.system;

import br.com.ecommerce.entities.cliente.AdministradorEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

                try {
                    // Carregar a configuração do hibernate.cfg.xml
                    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                    Session session = sessionFactory.openSession();

                    System.out.println("Conexão com o banco de dados estabelecida com sucesso!");


                    // Fechar a sessão e a fábrica
                    session.close();
                    sessionFactory.close();

                } catch (Exception e) {
                    System.err.println("Falha ao conectar ao banco de dados: " + e.getMessage());
                    e.printStackTrace();
                }

    }//fecha o public static void main

}//fecha a class main
