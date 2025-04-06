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
                    cadastrarUser();
                    break;
                case 3:
                    System.out.println("✅ Programa foi encerrado com sucesso.");
                    break;
                default:
                    System.out.println("❌Erro! Número digitado é inválido.");
             }//fecha o switch

        } while (op != 3);//fecha o while

    }//fecha public void SystemLoginOrCadastro



    public static  void cadastrarUser() {
    }

    public static void loginUserOrAdm() {
    }

}//fecha class SystemLoginOrCadastro

