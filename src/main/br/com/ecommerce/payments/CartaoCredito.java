package br.com.ecommerce.payments;

import java.util.Scanner;

public class CartaoCredito {

    public double formaDePagamentoCredito(double total) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o número de parcelas ( Ate 3X sem juros! ):");
        int parcelas = scanner.nextInt();

        if (parcelas <= 0) {
            System.out.println("❌ Número de parcelas inválido.");
            return total;
        }

        if (parcelas > 6) {
            total *= 1.30; // Aumento de 30%
        } else if (parcelas == 6) {
            total *= 1.20; // Aumento de 20%
        } else if (parcelas == 5) {
            total *= 1.15; // Aumento de 15%
        } else if (parcelas == 4) {
            total *= 1.10; // Aumento de 10%
        }

        System.out.printf("💳 Total com acréscimo (Cartão de Crédito - %d parcelas): R$ %.2f\n", parcelas, total);
        return total;
    }
}