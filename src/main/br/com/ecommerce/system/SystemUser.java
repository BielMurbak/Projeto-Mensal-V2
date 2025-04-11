package br.com.ecommerce.system;


import java.util.Scanner;


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


    public static void nossosProdutos() {

        // Scanner scanner = new Scanner(System.in);
        //ProdutoRepository produtoRepo = new ProdutoRepository();

        //   boolean continuarComprando = true;

        //   while (continuarComprando) {
        //System.out.println("\n--- Lista de Produtos Disponíveis ---");
        //   List<ProdutoEntity> produtos = produtoRepo.listarTodos(); // Você precisa implementar isso no repositório
//
        //  for (ProdutoEntity p : produtos) {
        //  System.out.println("Nome: " + p.getNome()
        //       + " | Preço: R$" + p.getPreco()
        //     + " | Quantidade: " + p.getQuantidade()
        //   + " | ID: " + p.getId());

    // System.out.print("\nDigite o ID do produto que deseja comprar: ");
    // Long idProduto = scanner.nextLong();

    // ProdutoEntity produto = produtoRepo.buscarPorId(idProduto);

    //  if (produto == null) {
    //       System.out.println("Produto não encontrado.");
    //     continue;

    //  System.out.print("Quantidade que deseja comprar: ");
    //  int qtd = scanner.nextInt();

    //  if (produto.getQuantidade() >= qtd) {
    //    produto.setQuantidade(produto.getQuantidade() - qtd);
    //     produtoRepo.salvar(produto);
    //     System.out.println("Compra realizada com sucesso!");
    //  } else {
    //      System.out.println("Estoque insuficiente.");
    //  }
//
    //  System.out.print("\nDeseja comprar outro produto? (s/n): ");
    // String resp = scanner.next();

    //   if (!resp.equalsIgnoreCase("s")) {
    //      continuarComprando = false;
    //    }
    //  }

    //   scanner.close();
}


public static void suporteClienteVarejo () {
}

public static void seguracaPrivacidade () {
}

}//fecha a class System Uuser