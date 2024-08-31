package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import entities.Produto;
import repositories.ProdutoRepository;

public class ProdutoController {
	private Scanner scanner = new Scanner(System.in);

	public void cadastrarProduto() {
		try {
			System.out.println("\nCADASTRO DO PRODUTO:");

			System.out.print("Nome do produto....: ");
			String nome = scanner.nextLine();

			System.out.print("Preço do produto...: ");
			Double preco = Double.parseDouble(scanner.nextLine());

			System.out.print("Quantidade.........: ");
			Integer quantidade = Integer.parseInt(scanner.nextLine());

			Produto produto = new Produto(UUID.randomUUID(), nome, preco, quantidade);

			System.out.println(produto.toString());

			ProdutoRepository repository = new ProdutoRepository();

			repository.inserir(produto);

		} catch (Exception e) {
			System.out.println("Falha ao cadastrar produto: " + e.getMessage());
		}
	}

	public void atualizarProduto() {
		try {
			System.out.println("\nATUALIZAR PRODUTO:");

			System.out.println("\nInforme o ID do produto:");
			UUID id = UUID.fromString(scanner.nextLine());

			ProdutoRepository repository = new ProdutoRepository();
			Produto produto = repository.buscarById(id);

			if (produto != null) {
				System.out.print("\nID do produto......: " + produto.getId());
				System.out.print("\nNome do produto....: " + produto.getNome());
				System.out.print("\nPreço do produto...: " + produto.getPreco());
				System.out.print("\nQuantidade.........: " + produto.getQuantidade());
				System.out.println("");
				System.out.println("ALTERE O NOME (" + produto.getNome() + "): ");
				produto.setNome(scanner.nextLine());
				System.out.println("ALTERE O PREÇO (" + produto.getPreco() + "): ");
				produto.setPreco(Double.parseDouble(scanner.nextLine()));
				System.out.println("ALTERE A QUANTIDADE: (" + produto.getQuantidade() + "): ");
				produto.setQuantidade(Integer.parseInt(scanner.nextLine()));

				repository.atualizar(produto);
			} else {
				System.out.println("\nProduto não encontrado");
			}

		} catch (Exception e) {
			System.out.println("Falha ao atualizar produto: " + e.getMessage());
		}
	}

	public void excluirProduto() {
		try {
			System.out.println("\nEXCLUIR PRODUTO:");

			System.out.println("\nInforme o ID do produto:");
			UUID id = UUID.fromString(scanner.nextLine());

			ProdutoRepository repository = new ProdutoRepository();
			Produto produto = repository.buscarById(id);

			if (produto != null) {
				System.out.println("\nPRODUTO SENDO EXCLUIDO...");
				System.out.print("\nID do produto......: " + produto.getId());
				System.out.print("\nNome do produto....: " + produto.getNome());
				System.out.print("\nPreço do produto...: " + produto.getPreco());
				System.out.print("\nQuantidade.........: " + produto.getQuantidade());
				System.out.println("");

				repository.excluir(produto.getId());
			} else {
				System.out.println("\nProduto não encontrado");
			}

		} catch (Exception e) {
			System.out.println("Falha ao excluir produto: " + e.getMessage());
		}
	}

	public void buscarProdutos() {
		try {

			ProdutoRepository repository = new ProdutoRepository();

			List<Produto> lista = new ArrayList<Produto>();

			lista = repository.consultar();

			System.out.println("\nPRODUTOS:");
			System.out.println("");

			for (Produto produto : lista) {
				System.out.println("ID: " + produto.getId());
				System.out.println("Nome: " + produto.getNome());
				System.out.println("Preço: " + produto.getPreco());
				System.out.println("Quantidade: " + produto.getQuantidade());
				System.out.println("");
			}

		} catch (Exception e) {
			System.out.println("Falha ao atualizar produto: " + e.getMessage());
		}
	}

	public void buscarUmProduto() {
		try {
			System.out.println("\nBUSCAR PRODUTO:");

			System.out.println("\nInforme o ID do produto:");
			UUID id = UUID.fromString(scanner.nextLine());

			ProdutoRepository repository = new ProdutoRepository();
			Produto produto = repository.buscarById(id);

			if (produto != null) {
				System.out.println("\nPRODUTO: ");
				System.out.print("\nID do produto......: " + produto.getId());
				System.out.print("\nNome do produto....: " + produto.getNome());
				System.out.print("\nPreço do produto...: " + produto.getPreco());
				System.out.print("\nQuantidade.........: " + produto.getQuantidade());
				System.out.println("");
			} else {
				System.out.println("\nProduto não encontrado");
			}

		} catch (Exception e) {
			System.out.println("Falha ao buscar um produto: " + e.getMessage());
		}
	}
}
