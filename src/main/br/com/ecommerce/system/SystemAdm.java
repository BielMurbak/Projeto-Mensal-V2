package br.com.ecommerce.system;

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
            System.out.println("\n");
            System.out.println("\n=== Sistema E-commerce ===");
            System.out.println("---------------------");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos ");
            System.out.println("3 - Remover Produto");
            System.out.println("--------------------");
            System.out.println("4 - Adicionar Administrador");
            System.out.println("5 - Listar Administradores");
            System.out.println("6 - Remover Administrador");
            System.out.println("--------------------");
            System.out.println("7 - Cadastrar Cliente");
            System.out.println("8 - Listar Clientes");
            System.out.println("9 - Remover Cliente");
            System.out.println("--------------------");
            System.out.println("10 - Acessar Sistema de Usuário");
            System.out.println("11 - Encerrar Programa");
            System.out.print("Escolha uma opção: ");
            op = scanner.nextInt();

            switch (op) {
                case 1:
                    cadastrarProduto(scanner);
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    removerProduto(scanner);
                    break;
                case 4:
                    adicionarAdministrador(scanner);
                    break;
                case 5:
                    listarAdministradores();
                    break;
                case 6:
                    removerAdministrador(scanner);
                    break;
                case 7:
                    cadastrarCliente(scanner);
                    break;
                case 8:
                    listarClientes();
                    break;
                case 9:
                    removerCliente(scanner);
                    break;
                case 10:
                    System.out.println("Acessando sistema usuario...");
                    SystemUser.main(null);
                    break;
                case 11:
                    System.out.println("✅ Programa foi encerrado com sucesso.");
                    exit(0);
                    break;
                default:
                    System.out.println("❌ Erro! Número digitado é inválido.");

            }//fecha o switch

        } while (op != 11);//fecha o while

    }//fecha a public static void SystemAdm



   //case 1
    public static void cadastrarProduto(Scanner scanner) {
        scanner.nextLine();
        System.out.println("\n--- Adicionar Produto ---");
        System.out.print("Nome do Produto: ");
        String nomeProduto = scanner.nextLine();


        System.out.println("1-Tenis");
        System.out.println("2-Camiseta");
        System.out.println("3-Calça ");
        System.out.println("4-Boné");
        System.out.print("Escolha o tipo:");
        int tipoProduto = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        System.out.print("Preço: ");
        double preco = Double.parseDouble(scanner.nextLine());

        System.out.print("Código do Produto: ");
        int codigoProduto = Integer.parseInt(scanner.nextLine());

        ProdutoEntity produto = new ProdutoEntity();
        produto.setNome(nomeProduto);
        produto.setQuantidade(quantidade);

        if(tipoProduto==1){
            produto.setTipo(TipoProduto.TENIS);
        }else if (tipoProduto==2){
            produto.setTipo(TipoProduto.CAMISA);
        }else if(tipoProduto==3)
            produto.setTipo(TipoProduto.CALCA);
        else{
            produto.setTipo(TipoProduto.BONE);
        }

        produto.setPreco(preco);
        produto.setCodigoProduto(codigoProduto);

        ProdutoRepository produtoRepository = new ProdutoRepository();
        produtoRepository.salvar(produto);

        System.out.println("✅ Produto cadastrado com sucesso!");


    }//fim do case 1



    //case 2
    public static void listarProdutos() {

        ProdutoRepository produtoRepository = new  ProdutoRepository();
        List<ProdutoEntity> produtos = produtoRepository.listarTodos();

        if(produtos!=null){
            System.out.println("Produtos registrados no banco");
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
        }else{
            System.out.println("Erro! nao foi encontrado nenhum adm no banco");
        }
    }//fim do case 2



    //case 3
    public static void removerProduto(Scanner scanner) {
        scanner.nextLine();
        System.out.println("\n--Remover Produto ---");
        System.out.print("Nome do Produto: ");
        String nomeProdutoRemover  = scanner.nextLine();

        try{
            ProdutoRepository produto = new  ProdutoRepository();
            produto.deletarNomeProduto( nomeProdutoRemover);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar administrador: " + e.getMessage(), e);
        }


    }//fim do case 3



    //case 4
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

    }//fim do case 4-------------------------------------------------------------------



    //case 5-------------------------------------------------------------------
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

    }//fim do case 5-------------------------------------------------------------------



    //case 6-------------------------------------------------------------------
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

    }//fim do case 6



   //case 7
    public static void cadastrarCliente(Scanner scanner) {
        System.out.print("1 - Tipo Atacado\n");
        System.out.print("2 - Tipo Varejo\n");
        System.out.print("Escolha o tipo de cliente que deseja cadastrar: ");
        int tipoCliente = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Data de nascimento (yyyy-MM-dd): ");
        String dataNascimentoStr = scanner.nextLine();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr);

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

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
                PessoaEntity pessoa = new PessoaEntity();
                pessoa.setNome(nome);
                pessoa.setCpf(cpf);
                pessoa.setDataDeNascimento(dataNascimento);

                if (tipoCliente == 1) {
                    pessoa.setTipo(TipoPessoa.CLIENTE_ATACADO);
                } else if (tipoCliente == 2) {
                    pessoa.setTipo(TipoPessoa.CLIENTE_VAREJO);
                } else {
                    System.out.println("Erro! Tipo não foi digitado ou digitado incorretamente");
                    return;
                }

            PessoaRepository pessoaRepository = new PessoaRepository();
            EnderecoRepository enderecoRepository = new EnderecoRepository();
            ClienteRepository clienteRepository = new ClienteRepository();

            EnderecoEntity endereco = new EnderecoEntity(rua, bairro, municipio, estado, cep);
            enderecoRepository.salvar(endereco);

            PessoaEntity outrapessoa = new PessoaEntity();

            outrapessoa.setNome(nome);
            outrapessoa.setCpf(cpf);
            outrapessoa.setDataDeNascimento(dataNascimento);
            outrapessoa.setTipo(tipoCliente == 1 ? TipoPessoa.CLIENTE_ATACADO : TipoPessoa.CLIENTE_VAREJO);
            outrapessoa.setEndereco(endereco);

         pessoaRepository.salvar(outrapessoa);

            ClienteEntity cliente = new ClienteEntity();
            cliente.setPessoa(outrapessoa);
            cliente.setSenha(Integer.parseInt(senha));

            clienteRepository.salvar(cliente);


            System.out.println("✅ Cliente cadastrado com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
            }

}//fim do case 7


    //case 8
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

//fim do case 8



    //case 9
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

    }//fim do case 9



}//fecha o SystemAdm
