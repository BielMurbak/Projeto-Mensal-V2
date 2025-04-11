package br.com.ecommerce.system;

import java.util.List;
import java.util.Set;
import java.util.Scanner;
import br.com.ecommerce.entities.produto.ProdutoEntity;
import br.com.ecommerce.repository.ProdutoRepository;
//import br.com.ecommerce.repository.ProdutoRepository;

public class SystemUser {

    public static void main(String[] args) {

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


    public static void nossosProdutos() {
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
        }
    }



    public static void suporteClienteVarejo () {
}

public static void seguracaPrivacidade () {
}

}//fecha a class System Uuser