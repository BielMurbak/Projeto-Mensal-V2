package br.com.ecommerce.system;

import br.com.ecommerce.entities.user.AdministradorEntity;
import br.com.ecommerce.entities.user.PessoaEntity;
import br.com.ecommerce.repository.AdministradorRepository;
import br.com.ecommerce.repository.PessoaRepository;
import br.com.ecommerce.tipos.TipoPessoa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SystemAdm {

    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);
        int op = 0;

        // Menu principal

        do {
            System.out.println("\n");
            System.out.println("\n=== Sistema E-commerce ===");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Remover Produto");
            System.out.println("3 - Remover ClienteVarejo");
            System.out.println("4 - Adicionar Administrador");
            System.out.println("5 - Remover Administrador");
            System.out.println("6 - Listar Administradores");
            System.out.println("7 - Listar Produtos");
            System.out.println("8 - Acessar Sistema de Usuário");
            System.out.println("9 - Cadastrar ClienteVarejo");
            System.out.println("10 - Listar Clientes");
            System.out.println("11 - Encerrar Programa");
            System.out.print("Escolha uma opção: ");
            op = scanner.nextInt();

            switch (op) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    removerProduto();
                    break;
                case 3:
                    removerClienteVarejo();
                    break;
                case 4:
                    adicionarAdministrador(scanner);
                    break;
                case 5:
                    removerAdministrador(scanner);
                    break;
                case 6:
                    listarAdministradores();
                    break;
                case 7:
                    listarProdutos();
                    break;
                case 8:
                    acessarSistemaUsuario();
                    break;
                case 9:
                    cadastrarClienteVarejo();
                    break;
                case 10:
                    listarClientes();
                    break;
                case 11:
                    System.out.println("✅ Programa foi encerrado com sucesso.");
                    break;
                default:
                    System.out.println("❌ Erro! Número digitado é inválido.");

            }//fecha o switch

        } while (op != 11);//fecha o while

    }//fecha a public static void SystemAdm




    public static void cadastrarProduto() {


    }

    public static void removerProduto() {
    }

    public static void removerClienteVarejo() {
    }

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

    }

    public static void removerAdministrador(Scanner scanner) {
        scanner.nextLine();
        System.out.println("\n--Remover Administrador---");
        System.out.print("CPF do Administrador: ");
        String cpfAdministradorRemover  = scanner.nextLine();


        try{
            PessoaRepository pessoa = new PessoaRepository();
            pessoa.deletarPorCpf(cpfAdministradorRemover);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar administrador: " + e.getMessage(), e);
        }

    }

    public static void listarAdministradores() {
    }

    public static void listarProdutos() {
    }

    public static void acessarSistemaUsuario() {
    }

    public static void cadastrarClienteVarejo() {
    }

    public static void listarClientes() {
    }

}//fecha o SystemAdm
