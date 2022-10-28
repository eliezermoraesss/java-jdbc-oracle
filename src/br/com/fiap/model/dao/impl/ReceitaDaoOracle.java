package br.com.fiap.model.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.dao.ReceitaDAO;
import br.com.fiap.model.db.DB;
import br.com.fiap.model.db.DbException;
import br.com.fiap.model.entities.Receita;

public class ReceitaDaoOracle implements ReceitaDAO {

	private Connection conexao;

	public ReceitaDaoOracle(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Receita> listar() {
		List<Receita> lista = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = DB.getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM t_fin_receita");
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id_receita");
				int idUsuario = rs.getInt("id_usuario");
				String descricaoReceita = rs.getString("ds_receita");
				Date dataEntrada = rs.getDate("dt_entrada_receita");
				String tipoReceita = rs.getString("ds_tipo_receita");
				BigDecimal valorReceita = rs.getBigDecimal("val_receita");
				Receita receita = new Receita(id, idUsuario, descricaoReceita, dataEntrada, tipoReceita, valorReceita);
				lista.add(receita);
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
	public Receita buscaPorId(Integer idReceita) {
		Receita receita = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = DB.getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM t_fin_receita WHERE id_receita = ?");
			stmt.setInt(1, idReceita);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id_receita");
				int idUsuario = rs.getInt("id_usuario");
				String descricaoReceita = rs.getString("ds_receita");
				Date dataEntrada = rs.getDate("dt_entrada_receita");
				String tipoReceita = rs.getString("ds_tipo_receita");
				BigDecimal valorReceita = rs.getBigDecimal("val_receita");
				receita = new Receita(id, idUsuario, descricaoReceita, dataEntrada, tipoReceita, valorReceita);
			} else {
				return null;
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
		return receita;
	}

	@Override
	public void cadastrar(Receita obj) {
		PreparedStatement stmt = null;
		
		try {
			conexao = DB.getConnection();
			String sql = "INSERT INTO t_fin_receita (id_receita, id_usuario, ds_receita, dt_entrada_receita, ds_tipo_receita, val_receita) \r\n"
					+ "	VALUES (SEQ_RECEITA.NEXTVAL, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, obj.getIdUsuario());
			stmt.setString(2, obj.getDescricaoReceita());
			stmt.setDate(3, new Date(obj.getDataEntrada().getTime()));
			stmt.setString(4, obj.getTipoReceita());
			stmt.setBigDecimal(5, obj.getValorReceita());

			int linhasAfetadas = stmt.executeUpdate();

			if (linhasAfetadas > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					System.out.println(id);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha afetada!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatament(stmt);
		}
	}

	@Override
	public void atualizar(Receita obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Integer id) {
		// TODO Auto-generated method stub

	}
}
