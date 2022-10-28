package br.com.fiap.model.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.dao.DespesaDAO;
import br.com.fiap.model.db.DB;
import br.com.fiap.model.db.DbException;
import br.com.fiap.model.entities.Despesa;

public class DespesaDaoOracle implements DespesaDAO {

	private Connection conexao;

	public DespesaDaoOracle(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Despesa> listar() {
		List<Despesa> lista = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = DB.getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM t_fin_despesa");
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id_despesa");
				int idUsuario = rs.getInt("id_usuario");
				String descricaoDespesa = rs.getString("ds_despesa");
				Date dataEntrada = rs.getDate("dt_saida_despesa");
				String tipoDespesa = rs.getString("ds_tipo_despesa");
				BigDecimal valorDespesa = rs.getBigDecimal("val_despesa");
				Despesa despesa = new Despesa(id, idUsuario, descricaoDespesa, dataEntrada, tipoDespesa, valorDespesa);
				lista.add(despesa);
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
	public Despesa buscaPorId(Integer idDespesa) {
		Despesa despesa = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = DB.getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM t_fin_despesa WHERE id_despesa = ?");
			stmt.setInt(1, idDespesa);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id_despesa");
				int idUsuario = rs.getInt("id_usuario");
				String descricaoDespesa = rs.getString("ds_despesa");
				Date dataEntrada = rs.getDate("dt_saida_despesa");
				String tipoDespesa = rs.getString("ds_tipo_despesa");
				BigDecimal valorDespesa = rs.getBigDecimal("val_despesa");
				despesa = new Despesa(id, idUsuario, descricaoDespesa, dataEntrada, tipoDespesa, valorDespesa);
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
		return despesa;
	}

	@Override
	public void cadastrar(Despesa obj) {
		PreparedStatement stmt = null;
		
		try {
			conexao = DB.getConnection();
			String sql = "INSERT INTO t_fin_despesa (id_despesa, id_usuario, ds_despesa, dt_saida_despesa, ds_tipo_despesa, val_despesa) \r\n"
					+ "	VALUES (SEQ_RECEITA.NEXTVAL, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, obj.getIdUsuario());
			stmt.setString(2, obj.getDescricaoDespesa());
			stmt.setDate(3, new Date(obj.getDataSaida().getTime()));
			stmt.setString(4, obj.getTipoDespesa());
			stmt.setBigDecimal(5, obj.getValorDespesa());

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
	public void atualizar(Despesa obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Integer id) {
		// TODO Auto-generated method stub

	}
}
