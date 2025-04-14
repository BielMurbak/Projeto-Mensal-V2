package br.com.ecommerce.payments;

import br.com.ecommerce.system.SystemLoginOrCadastro;
import br.com.ecommerce.tipos.TipoPessoa;

public class Pix {

    public double formaDePagamentoPix(Double total) {
        double desconto = 0;

        if (SystemLoginOrCadastro.clienteLogado.getPessoa().getTipo() == TipoPessoa.CLIENTE_ATACADO) {
            // Cliente de Atacado: 15% de desconto
            desconto = total * 0.15;
        } else {
            //Cliente de Varejo: 5% de desconto
            desconto = total * 0.05;
        }

        total = total - desconto;
        System.out.printf("💰 Total com desconto (Pix): R$ %.2f\n", total);
        return total;
    }
}
