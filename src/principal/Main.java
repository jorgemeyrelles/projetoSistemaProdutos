package principal;

import java.util.Scanner;

import controllers.ProdutoController;

public class Main {

	public static void main(String[] args) {
		ProdutoController produtoController = new ProdutoController();
		Scanner scanner = new Scanner(System.in);

		System.out.println("\nSISTEMA DE CONTROLE DE PRODUTOS");
		System.out.println("(1) CADASTRAR PRODUTO");
		System.out.println("(2) ATUALIZAR PRODUTO");
		System.out.println("(3) EXCLUIR PRODUTO");
		System.out.println("(4) BUSCAR UM PRODUTO");
		System.out.println("(5) BUSCAR TODOS OS PRODUTOS");

		System.out.println("\nInforme sua opção: ");
		String opcao = scanner.nextLine();

		switch (opcao) {
		case "1": {
			produtoController.cadastrarProduto();
			break;
		}
		case "2": {
			produtoController.atualizarProduto();
			break;
		}
		case "3": {
			produtoController.excluirProduto();
			break;
		}
		case "4": {
			produtoController.buscarUmProduto();
			break;
		}
		case "5": {
			produtoController.buscarProdutos();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + opcao);
		}

		System.out.println("\nDESEJA REALIZAR MAIS UMA OPERAÇÃO? (S,N) ");
		String continuar = scanner.nextLine();

		if (continuar.equalsIgnoreCase("S")) {
			main(args);
		} else {
			System.out.println("\nFIM DO PROGRAMA!");
		}

		// produtoController.buscarProdutos();

	}

}
