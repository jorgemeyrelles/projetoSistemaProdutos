package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Produto;
import factories.ConnectionFactory;

public class ProdutoRepository {

	public void inserir(Produto produto) throws Exception {
		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("INSERT INTO tb_produto (id, nome, preco, quantidade) VALUES (?,?,?,?)");

		statement.setObject(1, produto.getId());
		statement.setString(2, produto.getNome());
		statement.setDouble(3, produto.getPreco());
		statement.setInt(4, produto.getQuantidade());
		statement.execute();

		connection.close();

		System.out.println("\nPRODUTO EXECUTADO COM SUCESSO!");
	}

	public void atualizar(Produto produto) {
		try {
			Connection connection = ConnectionFactory.getConnection();

			PreparedStatement statement = connection
					.prepareStatement("UPDATE tb_produto SET nome=?, preco=?, quantidade=? WHERE id=?");

			statement.setString(1, produto.getNome());
			statement.setDouble(2, produto.getPreco());
			statement.setInt(3, produto.getQuantidade());
			statement.setObject(4, produto.getId());

			statement.execute();

			connection.close();

			System.out.println("\nPRODUTO ATUALIZADO COM SUCESSO!");
		} catch (Exception e) {
			System.out.println("Falha ao tentar atualizar: " + e.getMessage());
		}
	}

	public void excluir(UUID id) {
		try {
			Connection connection = ConnectionFactory.getConnection();

			PreparedStatement statement = connection.prepareStatement("DELETE FROM tb_produto WHERE id=?");

			statement.setObject(1, id);

			statement.execute();

			connection.close();

			System.out.println("\nPRODUTO EXCLUIDO COM SUCESSO!");
		} catch (Exception e) {
			System.out.println("Falha ao tentar excluir: " + e.getMessage());
		}
	}

	public List<Produto> consultar() {
		List<Produto> lista = new ArrayList<Produto>();

		try {
			Connection connection = ConnectionFactory.getConnection();

			PreparedStatement statement = connection
					.prepareStatement("SELECT id, nome, preco, quantidade FROM tb_produto ORDER BY nome");

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Produto produto = new Produto();
				produto.setId(UUID.fromString(resultSet.getString("id")));
				produto.setNome(resultSet.getString("nome"));
				produto.setPreco(resultSet.getDouble("preco"));
				produto.setQuantidade(resultSet.getInt("quantidade"));
				lista.add(produto);
			}
			
			connection.close();

		} catch (Exception e) {
			System.out.println("Falha ao buscar produtos: " + e.getMessage());
		}

		return lista;
	}

	public Produto buscarById(UUID id) {
		Produto produto = null;
		try {
			Connection connection = ConnectionFactory.getConnection();

			PreparedStatement statement = connection
					.prepareStatement("SELECT id, nome, preco, quantidade FROM tb_produto WHERE id=?");

			statement.setObject(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				produto = new Produto();

				produto.setId(UUID.fromString(resultSet.getString("id")));
				produto.setNome(resultSet.getString("nome"));
				produto.setPreco(resultSet.getDouble("preco"));
				produto.setQuantidade(resultSet.getInt("quantidade"));
			}

			connection.close();
		} catch (Exception e) {
			System.out.println("Falha ao tentar atualizar: " + e.getMessage());
		}

		return produto;
	}
}
