
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
            ClienteEntity cliente2 = new ClienteEntity();
            PessoaEntity pessoaCliente2 = new PessoaEntity();
            EnderecoEntity endereco2 = new EnderecoEntity();

            AdministradorEntity adm = new AdministradorEntity();
            PessoaEntity pessoaAdm = new PessoaEntity();

            // Pessoa do cliente
            pessoaCliente.setTipo(TipoPessoa.CLIENTE_VAREJO);
            pessoaCliente.setNome("Adryan Jacinto");
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

            //pessoa 2
            pessoaCliente2.setTipo(TipoPessoa.CLIENTE_ATACADO);
            pessoaCliente2.setNome("Adryan Silva");
            pessoaCliente2.setCpf("022.141.180-30");
            pessoaCliente2.setDataDeNascimento(LocalDate.of(2004, 2, 10));

            endereco2.setRua("Alexandria");
            endereco2.setBairro("Jacinto");
            endereco2.setMunicipio("Azul Claro");
            endereco2.setCep("45222-101");
            endereco2.setEstado("SC");

            cliente2.setSenha("2507@");
            cliente2.setPessoa(pessoaCliente2);
            cliente2.setEnderecoEntity(endereco2);
            //fim do segunda pessoa

            // Pessoa do administrador
            pessoaAdm.setTipo(TipoPessoa.ADMINISTRADOR);
            pessoaAdm.setNome("Biel");
            pessoaAdm.setCpf("093.777.222-01");
            pessoaAdm.setDataDeNascimento(LocalDate.of(2006, 7, 21));

            adm.setSenha("adm!!");
            adm.setPessoaEntity(pessoaAdm);

            session.save(cliente);
            session.save(adm);
            session.save(cliente2);

            //Criar Produto

            ProdutoEntity produto1 = new ProdutoEntity("Nike Court Vision", TipoProduto.TENIS, 15, 499.99, 1);
            ProdutoEntity produto2 = new ProdutoEntity("Adidas Superstar", TipoProduto.TENIS, 20, 379.90, 2);
            ProdutoEntity produto3 = new ProdutoEntity("Camiseta Básica", TipoProduto.CAMISA, 50, 59.99, 3);

            //Criar uma instância do repositório e salvar
            ProdutoRepository produtoRepository = new ProdutoRepository();
            session.save(produto1);
            session.save(produto2);
            session.save(produto3);

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
