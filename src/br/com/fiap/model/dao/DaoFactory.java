package br.com.fiap.model.dao;

import br.com.fiap.model.dao.impl.DespesaDaoOracle;
import br.com.fiap.model.dao.impl.InvestimentoDaoOracle;
import br.com.fiap.model.dao.impl.ReceitaDaoOracle;
import br.com.fiap.model.dao.impl.UsuarioDaoOracle;
import br.com.fiap.model.db.DB;

public class DaoFactory {
	
	public static UsuarioDAO createUsuarioDao() {
		return new UsuarioDaoOracle(DB.getConnection());
	}
	
	public static ReceitaDAO createReceitaDao() {
		return new ReceitaDaoOracle(DB.getConnection());
	}
	
	public static DespesaDAO createDespesaDao() {
		return new DespesaDaoOracle(DB.getConnection());
	}
	
	public static InvestimentoDAO createInvestimentoDao() {
		return new InvestimentoDaoOracle(DB.getConnection());
	}
}
