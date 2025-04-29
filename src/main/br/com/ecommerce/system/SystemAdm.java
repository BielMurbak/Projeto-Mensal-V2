package br.com.ecommerce.system;

// Imports
import br.com.ecommerce.entities.endereco.EnderecoEntity;
import br.com.ecommerce.entities.produto.ProdutoEntity;
import br.com.ecommerce.entities.user.AdministradorEntity;
import br.com.ecommerce.entities.user.ClienteEntity;
import br.com.ecommerce.entities.user.PessoaEntity;
import br.com.ecommerce.repository.*;
import br.com.ecommerce.tipos.TipoPessoa;
import br.com.ecommerce.tipos.TipoProduto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;


public class SystemAdm {

    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);
        int op = 0;

        // Menu principal

        do {
            // Menu principal com opções administrativas
            System.out.println("=================================");
            System.out.println("===== E-Commerce GBs Street =====");
            System.out.println("===== Sistema Administrador =====");
            System.out.println("=================================\n");
            System.out.println("---------------------");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos e quantidade de produtos");
            System.out.println("3 - Buscar produtos por valores minimo e maximo");
            System.out.println("4 - Remover Produto");
            System.out.println("--------------------");
            System.out.println("5 - Adicionar Administrador");
            System.out.println("6 - Listar Administradores");
            System.out.println("7 - Remover Administrador");
            System.out.println("--------------------");
            System.out.println("8 - Cadastrar Cliente");
            System.out.println("9 - Listar Clientes");
            System.out.println("10 - Remover Cliente");
            System.out.println("--------------------");
            System.out.println("11 - Acessar Sistema de Usuário");
            System.out.println("12 - Buscar pessoas por primeiro nome ");
            System.out.println("13-  Encerrar Programa");
            System.out.println("---------------------");
            System.out.print("Escolha uma opção: ");
            op = scanner.nextInt();

            // Switch para selecionar a ação conforme opção escolhida
            switch (op) {
                case 1:
                    cadastrarProduto(scanner);
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    buscarProdutoPorPreco(scanner);
                    break;
                case 4:
                    removerProduto(scanner);
                    break;
                case 5:
                    adicionarAdministrador(scanner);
                    break;
                case 6:
                    listarAdministradores();
                    break;
                case 7:
                    removerAdministrador(scanner);
                    break;
                case 8:
                    cadastrarCliente(scanner);
                    break;
                case 9:
                    listarClientes();
                    break;
                case 10:
                    removerCliente(scanner);
                    break;
                case 11:
                    System.out.println("Acessando sistema usuario...");
                    SystemUser.main(null);
                   break;
                case 12:
                    buscarPorPrimeiroNome(scanner);
                case 13:
                    System.out.println("✅ Programa foi encerrado com sucesso.");
                    exit(0);
                    break;
                default:
                    System.out.println("❌ Erro! Número digitado é inválido.");

            }//fecha o switch

        } while (op != 12);//fecha o while

    }//fecha a public static void SystemAdm



    // case 1
    public static void cadastrarProduto(Scanner scanner) {
        // Limpa a quebra de linha pendente no Scanner
        scanner.nextLine();

        System.out.println("\n--- Adicionar Produto ---");

        // Solicita o nome do produto
        System.out.print("Nome do Produto: ");
        String nomeProduto = scanner.nextLine();

        // Exibe as opções de tipo de produto
        System.out.println("1-Tenis");
        System.out.println("2-Camiseta");
        System.out.println("3-Calça ");
        System.out.println("4-Boné");
        System.out.print("Escolha o tipo:");
        int tipoProduto = scanner.nextInt();

        // Limpa a quebra de linha pendente
        scanner.nextLine();

        // Solicita quantidade
        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        // Solicita preço
        System.out.print("Preço: ");
        double preco = Double.parseDouble(scanner.nextLine());

        // Solicita código do produto
        System.out.print("Código do Produto: ");
        int codigoProduto = Integer.parseInt(scanner.nextLine());

        // Cria objeto ProdutoEntity e seta os dados coletados
        ProdutoEntity produto = new ProdutoEntity();
        produto.setNome(nomeProduto);
        produto.setQuantidade(quantidade);

        // Define o tipo do produto baseado na escolha
        if (tipoProduto == 1) {
            produto.setTipo(TipoProduto.TENIS);
        } else if (tipoProduto == 2) {
            produto.setTipo(TipoProduto.CAMISA);
        } else if (tipoProduto == 3) {
            produto.setTipo(TipoProduto.CALCA);
        } else {
            produto.setTipo(TipoProduto.BONE);
        }

        // Define preço e código
        produto.setPreco(preco);
        produto.setCodigoProduto(codigoProduto);

        // Cria repositório e salva produto
        ProdutoRepository produtoRepository = new ProdutoRepository();
        produtoRepository.salvar(produto);

        // Mensagem de sucesso
        System.out.println("✅ Produto cadastrado com sucesso!");
    }
// fim do case 1




    // case 2
    public static void listarProdutos() {

        // Cria uma instância do repositório de produtos
        ProdutoRepository produtoRepository = new ProdutoRepository();

        // Busca todos os produtos do banco de dados
        List<ProdutoEntity> produtos = produtoRepository.listarTodos();

        // Conta a quantidade total de produtos cadastrados
        long contadorProdutos = produtoRepository.contarProdutosCadastrados();

        // Verifica se existem produtos cadastrados
        if (produtos != null) {
            System.out.println("Produtos registrados no banco");

            // Percorre todos os produtos e exibe suas informações
            for (ProdutoEntity produto : produtos) {
                System.out.println("------------------------");
                System.out.println("Codigo: " + produto.getCodigoProduto());
                System.out.println("------------------------");
                System.out.println("Nome: " + produto.getNome());
                System.out.println("------------------------");
                System.out.println("Quantidade: " + produto.getQuantidade());
                System.out.println("------------------------");
                System.out.println("Tipo: " + produto.getTipo());
                System.out.println("------------------------");
                System.out.println("Preço " + produto.getPreco());
                System.out.println("------------------------");
                System.out.println("\n");
                System.out.println("\n");
                System.out.println("\n");
            }

            // Mostra o total de produtos cadastrados
            System.out.println("Total de produtos cadastrados: " + contadorProdutos);

        } else {
            // Se não houver produtos, exibe mensagem de erro
            System.out.println("Erro! nao foi encontrado nenhum adm no banco");
        }

    } // fim do case 2


    // case 3
    public static void buscarProdutoPorPreco(Scanner scanner) {
        // Limpa a quebra de linha pendente no Scanner
        scanner.nextLine();

        // Solicita valor mínimo
        System.out.printf("Valor minimo:");
        double valor1 = scanner.nextDouble();

        // Solicita valor máximo
        System.out.printf("Valor maximo:");
        double valor2 = scanner.nextDouble();

        try {
            // Instancia o repositório de produtos
            ProdutoRepository produto = new ProdutoRepository();

            produto.buscarPorValor(valor1, valor2);

        } catch (Exception e) {
            // Lança uma RuntimeException caso ocorra erro
            throw new RuntimeException("Erro ao buscar por valor minimo e maximo: " + e.getMessage(), e);
        }
    }


//case 4
public static void removerProduto(Scanner scanner) {
    // Limpa a quebra de linha pendente no Scanner
    scanner.nextLine();

    // Tela de remoção de produto
    System.out.println("\n--Remover Produto ---");
    System.out.print("Nome do Produto: ");
    String nomeProdutoRemover = scanner.nextLine(); // Captura o nome do produto a ser removido

    try {
        // Instancia o repositório de produtos
        ProdutoRepository produto = new ProdutoRepository();

        // Chama o método para deletar o produto pelo nome
        produto.deletarNomeProduto(nomeProdutoRemover);

    } catch (Exception e) {
        // Lança uma RuntimeException caso ocorra erro
        throw new RuntimeException("Erro ao adicionar administrador: " + e.getMessage(), e);
    }

} // fim do case 4



    //case 5
    public static void adicionarAdministrador(Scanner scanner) {
        scanner.nextLine();
        System.out.println("\n--- Adicionar Administrador---");
        System.out.print("Nome do Administrador: ");
        String nomeAdministrador = scanner.nextLine();
        System.out.print("Senha do Administrador: ");
        String senhaAdministrador  = scanner.nextLine();
        System.out.print("CPF do Administrador: ");
        String cpfAdministrador  = scanner.nextLine();
        System.out.print("Data de nascimento do Administrador: ");
        String dataDigitada = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate dataDeNascimentoAdministrador = LocalDate.parse(dataDigitada, formatter);

        try{
            PessoaEntity pessoa = new PessoaEntity();
            pessoa.setNome(nomeAdministrador);
            pessoa.setCpf(cpfAdministrador);
            pessoa.setDataDeNascimento(dataDeNascimentoAdministrador);
            pessoa.setTipo(TipoPessoa.ADMINISTRADOR);
            PessoaRepository pessoaRepository =new PessoaRepository();
            pessoaRepository.salvar(pessoa);


            AdministradorEntity admin = new AdministradorEntity();
            admin.setSenha(senhaAdministrador);
            admin.setPessoaEntity(pessoa); // Associa a pessoa ao admin

            AdministradorRepository administradorRepository = new AdministradorRepository();
            administradorRepository.salvar(admin);

            System.out.println("✅ Administrador adicionado com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar administrador: " + e.getMessage(), e);
        }

    }//fim do case 5-------------------------------------------------------------------



    //case 6-------------------------------------------------------------------
    public static void listarAdministradores() {
        AdministradorRepository administradorRepository = new AdministradorRepository();
        List<AdministradorEntity> admins = administradorRepository.buscarAdm();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(admins!=null){
            for (AdministradorEntity adm : admins) {
                PessoaEntity pessoa = adm.getPessoaEntity();
                System.out.println("------------------------");
                System.out.println("Nome: " + pessoa.getNome());
                System.out.println("------------------------");
                System.out.println("CPF: " + pessoa.getCpf());
                System.out.println("------------------------");
                System.out.println("Data de Nascimento: " + pessoa.getDataDeNascimento().format(formatter));
                System.out.println("------------------------");
                System.out.println("Tipo: " + pessoa.getTipo());
                System.out.println("------------------------");
                System.out.println("Senha: " + adm.getSenha());
                System.out.println("------------------------");
            }
        }else{
            System.out.println("Erro! nao foi encontrado nenhum adm no banco");
        }

    }//fim do case 6-------------------------------------------------------------------



    //case 7-------------------------------------------------------------------
    public static void removerAdministrador(Scanner scanner) {
        scanner.nextLine();
        System.out.println("\n--Remover Administrador---");
        System.out.print("CPF do Administrador: ");
        String cpfAdministradorRemover  = scanner.nextLine();


        try{
            PessoaRepository pessoa = new PessoaRepository();
            pessoa.deletarPorCpf(cpfAdministradorRemover);

            System.out.println("✅ Adm foi removido com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar administrador: " + e.getMessage(), e);
        }

    }//fim do case 7



   //case 8
   public static void cadastrarCliente(Scanner scanner) {
       // Escolha do tipo de cliente (Atacado ou Varejo)
       System.out.print("1 - Tipo Atacado\n");
       System.out.print("2 - Tipo Varejo\n");
       System.out.print("Escolha o tipo de cliente que deseja cadastrar: ");
       int tipoCliente = scanner.nextInt();
       scanner.nextLine(); // Consumir a quebra de linha pendente

       // Coleta de dados pessoais
       System.out.print("Nome: ");
       String nome = scanner.nextLine();

       System.out.print("CPF: ");
       String cpf = scanner.nextLine();

       System.out.print("Data de nascimento (yyyy-MM-dd): ");
       String dataNascimentoStr = scanner.nextLine();
       LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr);

       System.out.print("Senha: ");
       String senha = scanner.nextLine();

       // Coleta de dados do endereço
       System.out.print("Rua: ");
       String rua = scanner.nextLine();

       System.out.print("Bairro: ");
       String bairro = scanner.nextLine();

       System.out.print("Município: ");
       String municipio = scanner.nextLine();

       System.out.print("Estado: ");
       String estado = scanner.nextLine();

       System.out.print("CEP: ");
       String cep = scanner.nextLine();

       try {
           // Criação da primeira instância de PessoaEntity
           PessoaEntity pessoa = new PessoaEntity();
           pessoa.setNome(nome);
           pessoa.setCpf(cpf);
           pessoa.setDataDeNascimento(dataNascimento);

           // Definição do tipo de cliente
           if (tipoCliente == 1) {
               pessoa.setTipo(TipoPessoa.CLIENTE_ATACADO);
           } else if (tipoCliente == 2) {
               pessoa.setTipo(TipoPessoa.CLIENTE_VAREJO);
           } else {
               System.out.println("Erro! Tipo não foi digitado ou digitado incorretamente");
               return;
           }

           // Instanciando repositórios
           PessoaRepository pessoaRepository = new PessoaRepository();
           EnderecoRepository enderecoRepository = new EnderecoRepository();
           ClienteRepository clienteRepository = new ClienteRepository();

           // Criação e salvamento do endereço
           EnderecoEntity endereco = new EnderecoEntity(rua, bairro, municipio, estado, cep);
           enderecoRepository.salvar(endereco);

           // Criação de outra instância de PessoaEntity (outro objeto separado)
           PessoaEntity outrapessoa = new PessoaEntity();
           outrapessoa.setNome(nome);
           outrapessoa.setCpf(cpf);
           outrapessoa.setDataDeNascimento(dataNascimento);
           outrapessoa.setTipo(tipoCliente == 1 ? TipoPessoa.CLIENTE_ATACADO : TipoPessoa.CLIENTE_VAREJO);

           // Salvando a nova pessoa no banco de dados
           pessoaRepository.salvar(outrapessoa);

           // Criação e associação do cliente à pessoa cadastrada
           ClienteEntity cliente = new ClienteEntity();
           cliente.setPessoa(outrapessoa);
           cliente.setSenha(senha);

           // Salvando o cliente
           clienteRepository.salvar(cliente);

           // Mensagem de sucesso
           System.out.println("✅ Cliente cadastrado com sucesso!");
       } catch (Exception e) {
           // Tratamento de erro
           System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
       }
   }//fim do case 8


    //case 9
    public static void listarClientes() {

        ClienteRepository clienteRepository = new ClienteRepository();
        List<ClienteEntity> clientes = clienteRepository.buscarCliente();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (clientes != null && !clientes.isEmpty()) {
            for (ClienteEntity cliente : clientes) {
                PessoaEntity pessoa = cliente.getPessoa();
                System.out.println("------------------------");
                System.out.println("Nome: " + pessoa.getNome());
                System.out.println("------------------------");
                System.out.println("CPF: " + pessoa.getCpf());
                System.out.println("------------------------");
                System.out.println("Data de Nascimento: " + pessoa.getDataDeNascimento().format(formatter));
                System.out.println("------------------------");
                System.out.println("Tipo: " + pessoa.getTipo());
                System.out.println("------------------------");
                System.out.println("Senha: " + cliente.getSenha());
                System.out.println("------------------------");
            }
        } else {
            System.out.println("Erro! Não foi encontrado nenhum cliente no banco");
        }

    }

//fim do case 9



    //case 10
    public static void removerCliente(Scanner scanner) {

        scanner.nextLine();
        System.out.println("\n--Remover Cliente ---");
        System.out.print("Digite o cpf do cliente que voce deseja remover: ");
        String cpfClienteRemover  = scanner.nextLine();

        try{
            ClienteRepository clienteRepository = new ClienteRepository();
            clienteRepository.deletarCpfCliente(cpfClienteRemover);

            System.out.println("✅ Clinte foi removido com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover Cliente: " + e.getMessage(), e);
        }

    }//fim do case 10

    //case 12
    public static void buscarPorPrimeiroNome(Scanner scanner){

        scanner.nextLine();

        System.out.println("\n--Buscar pessoa por nome--");
        System.out.printf("Digite o nome:");
        String nomeParcial = scanner.nextLine();

            try {
                PessoaRepository pessoaRepository = new PessoaRepository();

                List<PessoaEntity> pessoas = pessoaRepository.buscarPorParcialNome(nomeParcial);

                if (!pessoas.isEmpty()) {
                    for (PessoaEntity pessoa : pessoas) {
                        System.out.println("----------------------");
                        System.out.println("Nome: " + pessoa.getNome());
                        System.out.println("CPF: " + pessoa.getCpf());
                        System.out.println("Tipo: " + pessoa.getTipo());
                        System.out.println("Data de Nascimento: " + pessoa.getDataDeNascimento());
                    }
                } else {
                    System.out.println("⚠️ Nenhuma pessoa encontrada com esse nome.");
                }

            } catch (Exception e) {
                throw new RuntimeException("Erro ao Buscar: " + e.getMessage(), e);
            }

    }//fim do case 12



}//fecha o SystemAdm


