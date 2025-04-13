package br.com.ecommerce.system;

import br.com.ecommerce.entities.user.AdministradorEntity;
import br.com.ecommerce.entities.user.ClienteEntity;
import br.com.ecommerce.entities.user.PessoaEntity;
import br.com.ecommerce.repository.AdministradorRepository;
import br.com.ecommerce.repository.ClienteRepository;
import br.com.ecommerce.repository.PessoaRepository;

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
        //
    }
}//fecha class SystemLoginOrCadastro

