package br.com.ecommerce.system;

import java.util.Scanner;

public class SystemUser {

    public static void SystemUser (String[] args) {

        Scanner scanner = new Scanner(System.in);
        int op = 0;

        // Menu principal

        do {
        System.out.println("\n");
            System.out.println("Sistema E-commerce");
            System.out.println("1 - Nossos Produtos");
            System.out.println("2 - Suporte ao ClienteVarejo");
            System.out.println("3 - Seguraça e Privacidade");
            System.out.println("4 - Sair");
            op = scanner.nextInt();

        switch (op) {
            case 1:
                nossosProdutos();
                break;
            case 2:
                suporteClienteVarejo();
                break;
            case 3:
                seguracaPrivacidade();
                break;
            case 4:
                System.out.println("✅ Programa foi encerrado com sucesso.");
            default:
                System.out.println("❌ Erro! Número digitado é inválido.");
        }//fecha o switch

    } while (op != 4);//fecha o while

}//fecha a public static void SystemUser





public static void nossosProdutos () {
}

public static void suporteClienteVarejo () {
}

public static void seguracaPrivacidade () {
}

}//fecha a class System Uuser



