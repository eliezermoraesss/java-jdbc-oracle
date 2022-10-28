package br.com.fiap.model.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.dao.InvestimentoDAO;
import br.com.fiap.model.db.DB;
import br.com.fiap.model.db.DbException;
import br.com.fiap.model.entities.Investimento;

public class InvestimentoDaoOracle implements InvestimentoDAO {
	
	private Connection conexao;

	public InvestimentoDaoOracle(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Investimento> listar() {
		List<Investimento> lista = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = DB.getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM t_fin_investimento");
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id_investimento");
				int idUsuario = rs.getInt("id_usuario");
				String descricaoInvestimento = rs.getString("ds_investimento");
				Date dataEntrada = rs.getDate("dt_investimento");
				String tipoInvestimento = rs.getString("ds_tipo_investimento");
				BigDecimal valorInvestimento = rs.getBigDecimal("val_investimento");
				Investimento investimento = new Investimento(id, idUsuario, descricaoInvestimento, dataEntrada, tipoInvestimento, valorInvestimento);
				lista.add(investimento);
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
	public Investimento buscaPorId(Integer idInvestimento) {
		Investimento investimento = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = DB.getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM t_fin_investimento WHERE id_investimento = ?");
			stmt.setInt(1, idInvestimento);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id_investimento");
				int idUsuario = rs.getInt("id_usuario");
				String descricaoInvestimento = rs.getString("ds_investimento");
				Date dataEntrada = rs.getDate("dt_investimento");
				String tipoInvestimento = rs.getString("ds_tipo_investimento");
				BigDecimal valorInvestimento = rs.getBigDecimal("val_investimento");
				investimento = new Investimento(id, idUsuario, descricaoInvestimento, dataEntrada, tipoInvestimento, valorInvestimento);
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
		return investimento;
	}

	@Override
	public void cadastrar(Investimento obj) {
		PreparedStatement stmt = null;
		
		try {
			conexao = DB.getConnection();
			String sql = "INSERT INTO t_fin_investimento (id_investimento, id_usuario, ds_investimento, dt_investimento, ds_tipo_investimento, val_investimento) \r\n"
					+ "	VALUES (SEQ_RECEITA.NEXTVAL, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, obj.getIdUsuario());
			stmt.setString(2, obj.getDescricaoInvestimento());
			stmt.setDate(3, new Date(obj.getDataInvestimento().getTime()));
			stmt.setString(4, obj.getTipoInvestimento());
			stmt.setBigDecimal(5, obj.getValorInvestimento());

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
	public void atualizar(Investimento obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(Integer id) {
		// TODO Auto-generated method stub

	}
}
