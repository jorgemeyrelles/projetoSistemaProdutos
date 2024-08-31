package factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public static Connection getConnection() {
		Connection connection = null;
		try {
			String driver = "org.postgresql.Driver";
			String url = "jdbc:postgresql://localhost:5433/bd_aula_03";
			String user = "postgres";
			String password = "root";

			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			System.out.println("Falha ao tentar conectar com DB!");
		}
		
		return connection;
	}
}
