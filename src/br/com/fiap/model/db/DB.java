package br.com.fiap.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	private static Connection conexao = null;

	public static Connection getConnection() {
		if (conexao == null) {
			try {
				// Registra o Driver
				Class.forName("oracle.jdbc.driver.OracleDriver");

				// Abre uma conexão
				
				//conexao = DriverManager.getConnection("jdbc:oracle:thin:FIAP/fiap@localhost:1521/xepdb1"); //MEU BANCO DE DADOS LOCAL => ELIEZER MORAES SILVA
				conexao = DriverManager.getConnection("jdbc:oracle:thin:@192.168.60.15:1521:ORCL", "OPS$PF0392", "123456");		

				System.out.println("Conectado!");

				// Tratamento de erro
			} catch (SQLException e) {
				System.err.println("Não foi possível conectar no Banco de Dados");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.err.println("O Driver JDBC não foi encontrado!");
				e.printStackTrace();
			}
		}
		return conexao;
	}
	
	public static void closeConnection() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());		
			}
		}
	}
	
	public static void closeStatament(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
