package br.com.ecommerce.system;

import java.util.Scanner;

public class SystemLoginOrCadastro {
    public static void SystemLoginOrCadastro (String[] args) {

        Scanner scanner = new Scanner(System.in);
        int op = 0;

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
                    loginUserOrAdm();
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

    public static void loginUserOrAdm() {
    }

    public static void cadastrarUser(Scanner scanner) {

        //nome
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine().trim();

        // Idade

        while (true) {
            System.out.print("Qual sua idade? ");
            int idade = scanner.nextInt();
            try {
                idade = Integer.parseInt(scanner.nextLine());
                if (idade >= 18) {
                    break; // idade válida, sai do loop
                } else {
                    System.out.println("Você precisa ter 18 anos ou mais para continuar.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite uma idade válida (número).");
            }
        }

        // CEP
        while (true) {
            System.out.print("Digite seu CEP (somente números, ex: 12345678): ");
            String cep = scanner.nextLine().trim();

            if (cep.matches("\\d{8}")) {
                break;
            } else {
                System.out.println("CEP inválido. Deve conter exatamente 8 números.");
            }
        }

        while (true) {
            System.out.print("Digite seu CPF (somente números, ex: 12345678901): ");
            String cpf = scanner.nextLine().trim();

            if (cpf.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("CPF inválido. Deve conter exatamente 11 números.");
            }
        }


        // Senha
        while (true) {
            System.out.print("Crie uma senha (mínimo 4 caracteres): ");
            String senha = scanner.nextLine().trim();

            if (senha.length() >= 4) {
                break;
            } else {
                System.out.println("Senha muito curta. Tente novamente.");
            }
        }

    }
}//fecha class SystemLoginOrCadastro

