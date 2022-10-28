package br.com.fiap.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.dao.UsuarioDAO;
import br.com.fiap.model.db.DB;
import br.com.fiap.model.db.DbException;
import br.com.fiap.model.entities.Usuario;

public class UsuarioDaoOracle implements UsuarioDAO {

	private Connection conexao;

	public UsuarioDaoOracle(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Usuario> listar() {
		List<Usuario> lista = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = DB.getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_FIN_USUARIO");
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID_USUARIO");
				String nome = rs.getString("DS_NOME");
				String email = rs.getString("DS_EMAIL");
				String senha = rs.getString("DS_SENHA");
				String cpf = rs.getString("DS_CPF");
				String genero = rs.getString("DS_GENERO");
				Date dataNascimento = rs.getDate("DT_NASCIMENTO");
				Usuario usuario = new Usuario(id, nome, email, senha, cpf, genero, dataNascimento);
				lista.add(usuario);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

		return lista;
	}

	@Override
	public Usuario buscaPorId(Integer idUsuario) {
		Usuario usuario = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = DB.getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM t_fin_usuario WHERE id_usuario = ?");
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt("ID_USUARIO");
				String nome = rs.getString("DS_NOME");
				String email = rs.getString("DS_EMAIL");
				String senha = rs.getString("DS_SENHA");
				String cpf = rs.getString("DS_CPF");
				String genero = rs.getString("DS_GENERO");
				Date dataNascimento = rs.getDate("DT_NASCIMENTO");
				usuario = new Usuario(id, nome, email, senha, cpf, genero, dataNascimento);
			} else {
				return null;
			}
					
		} catch (SQLException e) {
			throw new DbException(e.getMessage()); 		
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return usuario;
	}

	@Override
	public void cadastrar(Usuario obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void atualizar(Usuario obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Integer id) {
		// TODO Auto-generated method stub

	}
}
