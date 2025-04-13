package br.com.ecommerce.payments;

import br.com.ecommerce.system.SystemLoginOrCadastro;

public class Pix {

    public double formaDePagamentoPix(Double total) {
        double desconto = 0;

        //if (SystemLoginOrCadastro.SessaoUsuario.tipoClienteLogado == 2) {
       //     // Cliente de Atacado: 15% de desconto
       //     desconto = total * 0.15;
       // } else {
            // Cliente de Varejo: 5% de desconto
      //      desconto = total * 0.05;
      //  }

        double totalComDesconto = total - desconto;
        System.out.printf("💰 Total com desconto (Pix): R$ %.2f\n", totalComDesconto);
        return totalComDesconto;
    }
}
