package br.com.ecommerce.system;

import br.com.ecommerce.entities.endereco.EnderecoEntity;
import br.com.ecommerce.entities.user.AdministradorEntity;
import br.com.ecommerce.entities.user.ClienteEntity;
import br.com.ecommerce.entities.user.PessoaEntity;
import br.com.ecommerce.repository.AdministradorRepository;
import br.com.ecommerce.repository.ClienteRepository;
import br.com.ecommerce.repository.EnderecoRepository;
import br.com.ecommerce.repository.PessoaRepository;
import br.com.ecommerce.tipos.TipoPessoa;

import java.time.LocalDate;
import java.util.Scanner;

public class SystemLoginOrCadastro {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int op = 0;
        int tipoUsuario = 0;
        int contadorAtacado = 0;
        int contadorVarejo = 0;
        // Menu principal

        do {
            System.out.println("\n");
            System.out.println("---Sistema e-commerce---");
            System.out.println("1 - Fazer Login");
            System.out.println("2 - Cadastrar");
            System.out.println("3 - Encerrar programa");
            System.out.println("Escolha uma opção:");

            op = scanner.nextInt();

            switch (op) {
                case 1:
                    loginUserOrAdm(scanner,tipoUsuario);
                    break;
                case 2:
                    cadastrarUser(scanner);
                    break;
                case 3:
                    System.out.println("✅ Programa foi encerrado com sucesso.");
                    break;
                default:
                    System.out.println("❌Erro! Número digitado é inválido.");
            }//fecha o switch

        } while (op != 3);//fecha o while

    }//fecha public void SystemLoginOrCadastro


    public static void loginUserOrAdm(Scanner scanner, int tipoUsuario) {

        do {
            System.out.println("\n");
            System.out.println("Escolha o tipo de usuário para entrar:");
            System.out.println("1 - Cliente");
            System.out.println("2 - Administrador");
            System.out.println("3 -Encerrar programa");
            System.out.print("Digite a opção desejada: ");
            tipoUsuario = scanner.nextInt();

            switch (tipoUsuario) {
                case 1:
                    scanner.nextLine();
                    System.out.println("Digite seu nome Cliente: ");
                    String nomeLoginCliente = scanner.nextLine();

                    System.out.println("Digite a senha (Cliente): ");
                    String senhaLoginCliente = scanner.nextLine();

                    System.out.println("Digite o cpf do (Cliente)");
                    String cpfLoginCliente = scanner.nextLine();

                    PessoaRepository pessoaClienteRepository = new PessoaRepository();
                    PessoaEntity clientePorCpf = pessoaClienteRepository.buscarPorCpf(cpfLoginCliente);
                    PessoaEntity clientePorNome = pessoaClienteRepository.buscarPorNome(nomeLoginCliente);
                    ClienteRepository clienteRepository = new ClienteRepository();
                    ClienteEntity cliente = clienteRepository.buscarPorSenha(senhaLoginCliente);

                    if(clientePorNome==null){
                        System.out.println("Nome do Usuário não encontrado. Tente novamente.\n");
                        break;
                    }else if(clientePorCpf==null){
                        System.out.println("CPF do Usuário não encontrado. Tente novamente.\n");
                        break;
                    }else if (cliente==null){
                        System.out.println("Senha do Usuário não encontrado. Tente novamente.\n");
                        break;
                    }else{
                        System.out.println("✅ Login realizado com sucesso como Cliente.");
                        SystemUser su = new SystemUser();
                        su.main(null);
                    }

                    break;

                case 2:
                    scanner.nextLine();

                    System.out.println("Digite seu nome de Administrador: ");
                    String nomeLoginAdmin = scanner.nextLine();

                    System.out.println("Digite sua senha de Administrador: ");
                    String senhaLoginAdmin = scanner.nextLine();

                    System.out.println("Digite o cpf do Adiministrador:");
                    String cpfLoginAdmin = scanner.nextLine();

                    PessoaRepository pessoaRepository = new PessoaRepository();
                    PessoaEntity pessoaPorCpf = pessoaRepository.buscarPorCpf(cpfLoginAdmin);
                    PessoaEntity pessoaPorNome = pessoaRepository.buscarPorNome(nomeLoginAdmin);

                    AdministradorRepository administradorRepository = new AdministradorRepository();
                    AdministradorEntity administrador = administradorRepository.buscarPorSenha(senhaLoginAdmin);

                    if(pessoaPorNome==null){
                        System.out.println("Nome do Usuário não encontrado. Tente novamente.\n");
                        break;
                    }else if(administrador==null){
                        System.out.println("Senha do Usuário não encontrado. Tente novamente.\n");
                        break;
                    }else if (pessoaPorCpf==null){
                        System.out.println("Cpf do Usuário não encontrado. Tente novamente.\n");
                        break;
                    }else{
                        System.out.println("✅ Login realizado com sucesso como Administrador.");
                        SystemAdm sAdm = new SystemAdm();
                        sAdm.main(null);
                    }
                    break;


                case 3:
                    System.out.println("✅ Programa foi encerrado com sucesso.");
                    break;

                default:
                    System.out.println("❌Erro! Número digitado é inválido.");
            }
        }while(tipoUsuario!=4);

    }


    public static void cadastrarUser(Scanner scanner) {


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

            pessoaRepository.salvar(outrapessoa);

            ClienteEntity cliente = new ClienteEntity();
            cliente.setPessoa(outrapessoa);
            cliente.setSenha(senha);
            cliente.setEnderecoEntity(endereco);

            clienteRepository.salvar(cliente);


            System.out.println("✅ Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }

    }
}//fecha class SystemLoginOrCadastro

