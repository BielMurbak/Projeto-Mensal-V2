package br.com.ecommerce.system;

import java.util.List;
import java.util.Scanner;
import br.com.ecommerce.entities.produto.ProdutoEntity;
import br.com.ecommerce.payments.Pix;
import br.com.ecommerce.repository.ProdutoRepository;


public class SystemUser {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int op = 0;
        double total = 0;

        // Menu principal

        do {
            System.out.println("\n");
            System.out.println("Sistema E-commerce");
            System.out.println("1 - Nossos Produtos");
            System.out.println("2 - Suporte aos Clientes");
            System.out.println("3 - Seguraça e Privacidade");
            System.out.println("4 - Sair");
            op = scanner.nextInt();

            switch (op) {
                case 1:
                    nossosProdutos(total);
                    break;
                case 2:
                    //suporteClienteVarejo();
                    break;
                case 3:
                    //seguracaPrivacidade();
                    break;
                case 4:
                    System.out.println("✅ Programa foi encerrado com sucesso.");
                default:
                    System.out.println("❌ Erro! Número digitado é inválido.");
            }//fecha o switch

        } while (op != 4);//fecha o while

    }//fecha a public static void SystemUser


    public static void nossosProdutos(double total ) {
        Scanner scanner = new Scanner(System.in);
        ProdutoRepository produtoRepo = new ProdutoRepository();

        boolean continuarComprando = true;

        while (continuarComprando) {
            System.out.println("\n======= Lista de Produtos Disponíveis =======\n");
            List<ProdutoEntity> produtos = produtoRepo.listarTodos();

            for (ProdutoEntity p : produtos) {
                System.out.println("Codigo do Produto: " + p.getCodigoProduto()
                        + " | Tipo: " + p.getTipo()
                        + " | Nome: " + p.getNome()
                        + " | Preço: R$" + p.getPreco()
                        + " | Quantidade: " + p.getQuantidade());
            }

            System.out.print("\nDigite o código do produto que deseja comprar: ");
            int codigoProduto = scanner.nextInt();

            ProdutoEntity produto = produtoRepo.buscarPorCodigo(codigoProduto);

            if (produto == null) {
                System.out.println("Produto não encontrado.");
                return;
            }

            System.out.print("Digite a quantidade que deseja comprar: ");
            int quantidadeDesejada = scanner.nextInt();

            if (quantidadeDesejada <= 0) {
                System.out.println("Quantidade inválida.");
                return;
            }

            if (produto.getQuantidade() < quantidadeDesejada) {
                System.out.println("Quantidade insuficiente em estoque.");
                return;
            }

// Atualiza a quantidade
            produto.setQuantidade(produto.getQuantidade() - quantidadeDesejada);

// Salva a atualização no banco
            produtoRepo.atualizar(produto); // usando merge aqui

            System.out.println("Compra realizada com sucesso!");

            total += quantidadeDesejada * produto.getPreco();

            scanner.nextLine(); // limpa o buffer antes de ler string
            System.out.println("Deseja continuar comprando? (sim ou nao) ");
            String op = scanner.nextLine().toLowerCase();

            if (op.equals("sim") || op.equals("s")) {
                continuarComprando = true;
            } else if (op.equals("nao") || op.equals("n")) {
                continuarComprando = false;
            } else {
                System.out.println("Erro! Entrada inválida.");
                continuarComprando = false;
            }

            Pix pix = new Pix();


            System.out.println("1-Pix:");
            System.out.println("2-Dinheiro");
            System.out.println("3-Cartao de debito");
            System.out.println("4-Cartao de credito");
            System.out.println("Escolha a forma de pagamento:");
            int formaDePagamento = scanner.nextInt();

            switch (formaDePagamento) {
                case 1:
                    pix.formaDePagamentoPix(total);
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                default:
                    System.out.println("Erro! Entrava invalida ");
            }

        }
    }



    public static void suporteClienteVarejo () {
}

public static void seguracaPrivacidade () {
}

}//fecha a class System Uuser