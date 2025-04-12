package br.com.ecommerce.system;

import java.util.List;
import java.util.Scanner;

import br.com.ecommerce.entities.compra.HistoricoDeComprasEntity;
import br.com.ecommerce.entities.endereco.EnderecoEntity;
import br.com.ecommerce.entities.produto.ProdutoEntity;
import br.com.ecommerce.entities.user.ClienteEntity;
import br.com.ecommerce.payments.Pix;
import br.com.ecommerce.repository.HistoricoDeComprasRepository;
import br.com.ecommerce.repository.ProdutoRepository;
import br.com.ecommerce.tipos.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.hibernate.query.Query;

import static br.com.ecommerce.repository.PessoaRepository.sessionFactory;


public class SystemUser {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int op = 0;
        double total = 0;
        // Menu principal

        do {
            System.out.println("\n");
            System.out.println("Sistema E-commerce");
            System.out.println("1 - Nossos Produtos");
            System.out.println("2 - Suporte aos Clientes");
            System.out.println("3 - Seguraça e Privacidade");
            System.out.println("4 - Sair");
            op = scanner.nextInt();

            switch (op) {
                case 1:
                    nossosProdutos(total);
                    break;
                case 2:
                    suporteAoCliente();
                    break;
                case 3:
                    segurancaPrivacidade(scanner);
                    break;
                case 4:
                    System.out.println("✅ Programa foi encerrado com sucesso.");
                default:
                    System.out.println("❌ Erro! Número digitado é inválido.");
            }//fecha o switch

        } while (op != 4);//fecha o while

    }//fecha a public static void SystemUser


    public static void nossosProdutos(double total ) {
        Scanner scanner = new Scanner(System.in);
        ProdutoRepository produtoRepo = new ProdutoRepository();

        boolean continuarComprando = true;

        while (continuarComprando) {
            System.out.println("\n======= Lista de Produtos Disponíveis =======\n");
            List<ProdutoEntity> produtos = produtoRepo.listarTodos();

            for (ProdutoEntity p : produtos) {
                System.out.println("Codigo do Produto: " + p.getCodigoProduto()
                        + " | Tipo: " + p.getTipo()
                        + " | Nome: " + p.getNome()
                        + " | Preço: R$" + p.getPreco()
                        + " | Quantidade: " + p.getQuantidade());
            }

            System.out.print("\nDigite o código do produto que deseja comprar: ");
            int codigoProduto = scanner.nextInt();

            ProdutoEntity produto = produtoRepo.buscarPorCodigo(codigoProduto);

            if (produto == null) {
                System.out.println("Produto não encontrado.");
                return;
            }

            System.out.print("Digite a quantidade que deseja comprar: ");
            int quantidadeDesejada = scanner.nextInt();

            if (quantidadeDesejada <= 0) {
                System.out.println("Quantidade inválida.");
                return;
            }

            if (produto.getQuantidade() < quantidadeDesejada) {
                System.out.println("Quantidade insuficiente em estoque.");
                return;
            }

// Atualiza a quantidade
            produto.setQuantidade(produto.getQuantidade() - quantidadeDesejada);

// Salva a atualização no banco
            produtoRepo.atualizar(produto); // usando merge aqui

            System.out.println("Compra realizada com sucesso!");

            total += quantidadeDesejada * produto.getPreco();

            scanner.nextLine(); // limpa o buffer antes de ler string
            System.out.println("Deseja continuar comprando? (sim ou nao) ");
            String op = scanner.nextLine().toLowerCase();

            if (op.equals("sim") || op.equals("s")) {
                continuarComprando = true;
            } else if (op.equals("nao") || op.equals("n")) {
                continuarComprando = false;
            } else {
                System.out.println("Erro! Entrada inválida.");
                continuarComprando = false;
            }

            Pix pix = new Pix();


            System.out.println("1-Pix");
            System.out.println("2-Dinheiro");
            System.out.println("3-Cartao de debito");
            System.out.println("4-Cartao de credito");
            System.out.println("Escolha a forma de pagamento:");
            int formaDePagamento = scanner.nextInt();

            switch (formaDePagamento) {
                case 1:
                    pix.formaDePagamentoPix(total);
                    System.out.println("\n");
                    System.out.println("---- Escolha a opção de recebimento ----");
                    System.out.println("1 - Entrega");
                    System.out.println("2 - Retirada na loja");
                    System.out.print("Digite sua opção: ");
                    int opcaoRecebimento = scanner.nextInt();
                    scanner.nextLine();

                    ClienteEntity cliente = null; // declarar fora

                    if(opcaoRecebimento == 1) {
                        System.out.println("Digite o cpf do cliente: ");
                        String cpf = scanner.nextLine();

                        Session session = null;
                        try {
                            session = sessionFactory.openSession();
                            session.beginTransaction();

                            Query query = session.createQuery("FROM cliente c WHERE c.pessoa.cpf = :cpf");
                            query.setParameter("cpf", cpf);

                            cliente = (ClienteEntity) query.uniqueResult(); // agora atribui aqui

                            if (cliente != null && cliente.getEnderecoEntity() != null) {
                                EnderecoEntity endereco = cliente.getEnderecoEntity();
                                System.out.println("Rua: " + endereco.getRua());
                                System.out.println("Bairro: " + endereco.getBairro());
                                System.out.println("Município: " + endereco.getMunicipio());
                                System.out.println("Estado: " + endereco.getEstado());
                                System.out.println("CEP: " + endereco.getCep());
                            } else {
                                System.out.println("Cliente com CPF " + cpf + " não encontrado ou sem endereço cadastrado.");
                            }

                            session.getTransaction().commit();

                        } catch (HibernateException e) {
                            if (session != null) session.getTransaction().rollback();
                            System.out.println("Erro ao buscar endereço: " + e.getMessage());
                        } finally {
                            if (session != null) {
                                session.close();
                            }
                        }

            }else{
                        System.out.println("Nosso endereço:");
                        System.out.println("Rua:Azulão");
                        System.out.println("Bairro: Morumbi");
                        System.out.println("Município: Foz do Iguaçu");
                        System.out.println("Estado: Paraná");
                        System.out.println("CEP: 85867-000");
                        System.out.println("\n");
                    }


                    System.out.println("Escolha o método de frete:");
                    System.out.println("1 - Frete Padrão");
                    System.out.println("2 - Frete Expresso");
                    int opcaoFrete = scanner.nextInt();

                    if(opcaoFrete==1) {
                        double totalComFormaDePamento = pix.formaDePagamentoPix(total);

                        try {
                            HistoricoDeComprasEntity historicoDeCompras = new HistoricoDeComprasEntity();
                            HistoricoDeComprasRepository historicoDeComprasRepository = new HistoricoDeComprasRepository();

                            // Tipo de frete
                            historicoDeCompras.setTipoDeFrete(TipoDeFrete.Padrao);

                            // Forma de pagamento
                            if (formaDePagamento == 1) {
                                historicoDeCompras.setTipoCompra(TipoCompra.Pix);
                            } else if (formaDePagamento == 2) {
                                historicoDeCompras.setTipoCompra(TipoCompra.Dinheiro);
                            } else if (formaDePagamento == 3) {
                                historicoDeCompras.setTipoCompra(TipoCompra.CartaoDebito);
                            } else {
                                historicoDeCompras.setTipoCompra(TipoCompra.CartaoCredito);
                            }

                            // Tipo de pessoa, produto e recebimento
                            historicoDeCompras.setTipo(TipoPessoa.CLIENTE_VAREJO);  // Certifique-se de que esses valores estão corretos
                            historicoDeCompras.setTipoProduto(TipoProduto.TENIS);  // Certifique-se de que esses valores estão corretos
                            historicoDeCompras.setTipoRecebimento(TipoRecebimento.Entrega);  // Certifique-se de que esses valores estão corretos
                            historicoDeCompras.setNomeCliente("rafael");

                            // Total da compra
                            historicoDeCompras.setTotal(totalComFormaDePamento);  // Verifique se totalComFormaDePamento está definido corretamente


                            // Salva no repositório
                            historicoDeComprasRepository.salvar(historicoDeCompras);

                            System.out.println("\n===== HISTÓRICO DE COMPRA =====");
                            System.out.println("Tipo de Frete: " + historicoDeCompras.getTipoDeFrete());
                            System.out.println("Forma de Pagamento: " + historicoDeCompras.getTipoCompra());
                            System.out.println("Tipo de Pessoa: " + historicoDeCompras.getTipo());
                            System.out.println("Tipo de Produto: " + historicoDeCompras.getTipoProduto());
                            System.out.println("Tipo de Recebimento: " + historicoDeCompras.getTipoRecebimento());
                            System.out.println("Total da Compra: R$ " + String.format("%.2f", historicoDeCompras.getTotal()));

                        } catch (Exception e) {
                            throw new RuntimeException("Erro ao salvar histórico de compras", e);


                        }


                    }else if(opcaoFrete==2){
                        double totalComFormaDePamento = pix.formaDePagamentoPix(total);
                        double aux = totalComFormaDePamento;
                        totalComFormaDePamento = aux * 0.20;
                        HistoricoDeComprasEntity historicoDeCompras = new HistoricoDeComprasEntity();
                        HistoricoDeComprasRepository historicoDeComprasRepository = new HistoricoDeComprasRepository();

                        try {

                            // Tipo de frete
                            historicoDeCompras.setTipoDeFrete(TipoDeFrete.Expresso);

                            // Forma de pagamento
                            if (formaDePagamento == 1) {
                                historicoDeCompras.setTipoCompra(TipoCompra.Pix);
                            } else if (formaDePagamento == 2) {
                                historicoDeCompras.setTipoCompra(TipoCompra.Dinheiro);
                            } else if (formaDePagamento == 3) {
                                historicoDeCompras.setTipoCompra(TipoCompra.CartaoDebito);
                            } else {
                                historicoDeCompras.setTipoCompra(TipoCompra.CartaoCredito);
                            }

                            // Tipo de pessoa, produto e recebimento (confirme se esses valores são fixos ou dinâmicos)
                            historicoDeCompras.setTipo(TipoPessoa.CLIENTE_VAREJO);
                            historicoDeCompras.setTipoProduto(TipoProduto.TENIS);
                            historicoDeCompras.setTipoRecebimento(TipoRecebimento.Entrega);

                            // Total da compra
                            historicoDeCompras.setTotal(totalComFormaDePamento); // verifique se essa variável está correta

                            // Salva no repositório
                            historicoDeComprasRepository.salvar(historicoDeCompras);


                        } catch (Exception e) {
                            throw new RuntimeException("Erro ao salvar histórico de compras", e);
                        }

                        System.out.println("\n===== HISTÓRICO DE COMPRA =====");
                        System.out.println("Tipo de Frete: " + historicoDeCompras.getTipoDeFrete());
                        System.out.println("Forma de Pagamento: " + historicoDeCompras.getTipoCompra());
                        System.out.println("Tipo de Pessoa: " + historicoDeCompras.getTipo());
                        System.out.println("Tipo de Produto: " + historicoDeCompras.getTipoProduto());
                        System.out.println("Tipo de Recebimento: " + historicoDeCompras.getTipoRecebimento());
                        System.out.println("Total da Compra: R$ " + String.format("%.2f", historicoDeCompras.getTotal()));
                        totalComFormaDePamento = 0;
                        SystemUser sU = new SystemUser();
                        SystemUser.main(null);

                    }else{
                        System.out.println("Erro! Entrada invalida");
                    }

                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                default:
                    System.out.println("Erro! Entrava invalida ");
            }

        }
    }



    public static void suporteAoCliente() {
        Scanner scanner = new Scanner(System.in);
        int escolhaSuporte;

        do {
            System.out.println("\n--- Suporte ao Cliente ---");
            System.out.println("1 - FAQ (Perguntas Frequentes)");
            System.out.println("2 - Abrir um ticket de suporte");
            System.out.println("3 - Informações de contato");
            System.out.println("4 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            escolhaSuporte = scanner.nextInt();
            scanner.nextLine(); // limpa o buffer

            switch (escolhaSuporte) {
                case 1:
                    System.out.println("\nFAQ - Perguntas Frequentes:");
                    System.out.println("1 - Como posso realizar um pedido?");
                    System.out.println("   → Entre em contato conosco para mais informações.");
                    System.out.println("2 - Como faço para devolver um produto?");
                    System.out.println("   → Veja nossa política de devolução ou fale com o suporte.");
                    break;
                case 2:
                    System.out.println("\nAbrindo um ticket de suporte...");
                    System.out.print("Digite seu nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite sua dúvida ou problema: ");
                    String duvida = scanner.nextLine();
                    System.out.println("✅ Ticket enviado com sucesso. Obrigado, " + nome + "!");
                    break;
                case 3:
                    System.out.println("\nInformações de Contato:");
                    System.out.println("📞 Telefone: 0800-123-456");
                    System.out.println("📧 E-mail: suporte@ecommerce.com");
                    break;
                case 4:
                    System.out.println("\n🔙 Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("❌ Opção inválida. Tente novamente.");
            }
        } while (escolhaSuporte != 4);
    }

    public static void segurancaPrivacidade(Scanner scanner) {
        int escolhaSegurancaPrivacidade;

        do {
            System.out.println("\n--- Segurança e Privacidade ---");
            System.out.println("1 - Exibir política de privacidade");
            System.out.println("2 - Alterar senha (mock)");
            System.out.println("3 - Alterar nome (mock)");
            System.out.println("4 - Logout");
            System.out.println("5 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            escolhaSegurancaPrivacidade = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch (escolhaSegurancaPrivacidade) {
                case 1:
                    System.out.println("\nPolítica de Privacidade:");
                    System.out.println("- Nós respeitamos sua privacidade.");
                    break;
                case 2:
                    System.out.println("Função de alteração de senha ainda não implementada.");

                    break;
                case 3:
                    System.out.println("Função de alteração de nome ainda não implementada.");
                    break;
                case 4:
                    System.out.println("Logout realizado com sucesso!");
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    return; // volta pro menu principal (main)
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (true);
    }

    public static void entrega(Scanner scanner) {

        System.out.println("\n");
        System.out.println("---- Escolha a opção de recebimento ----");
        System.out.println("1 - Entrega");
        System.out.println("2 - Retirada na loja");
        System.out.print("Digite sua opção: ");
        int opcaoRecebimento = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoRecebimento) {
            case 1:
                System.out.println("Digite o cpf do cliente: ");
                String cpf = scanner.nextLine();

                Session session = null;
                try {
                    session = sessionFactory.openSession();
                    session.beginTransaction();  // Abrir a transação

                    Query query = session.createQuery("FROM cliente c WHERE c.pessoa.cpf = :cpf");
                    query.setParameter("cpf", cpf);

                    ClienteEntity cliente = (ClienteEntity) query.uniqueResult();

                    if (cliente != null && cliente.getEnderecoEntity() != null) {
                        System.out.println("Nome: " + cliente.getPessoa().getNome()); // <--
                        EnderecoEntity endereco = cliente.getEnderecoEntity();
                        System.out.println("Rua: " + endereco.getRua());
                        System.out.println("Bairro: " + endereco.getBairro());
                        System.out.println("Município: " + endereco.getMunicipio());
                        System.out.println("Estado: " + endereco.getEstado());
                        System.out.println("CEP: " + endereco.getCep());
                    } else {
                        System.out.println("Cliente com CPF " + cpf + " não encontrado ou sem endereço cadastrado.");
                    }

                    session.getTransaction().commit();  // Commit da transação

                } catch (HibernateException e) {
                    if (session != null) session.getTransaction().rollback();  // Rollback em caso de erro
                    System.out.println("Erro ao buscar endereço: " + e.getMessage());
                } finally {
                    if (session != null) {
                        session.close();  // Fechar a sessão
                    }
                }
                break;

            case 2:
                System.out.println("Nosso endereço");
                System.out.println("Rua:Azulão");
                System.out.println("Bairro: Morumbi");
                System.out.println("Município: Foz do Iguaçu");
                System.out.println("Estado: Paraná");
                System.out.println("CEP: 85867-000");
            break;

            default:
                System.out.println("Erro! opcao invalida");
        }
    }




}//fecha a class System Uuser