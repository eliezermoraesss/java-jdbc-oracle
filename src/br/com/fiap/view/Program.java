package br.com.fiap.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fiap.model.dao.DaoFactory;
import br.com.fiap.model.dao.DespesaDAO;
import br.com.fiap.model.dao.InvestimentoDAO;
import br.com.fiap.model.dao.ReceitaDAO;
import br.com.fiap.model.db.DB;
import br.com.fiap.model.db.DbException;
import br.com.fiap.model.entities.Despesa;
import br.com.fiap.model.entities.Investimento;
import br.com.fiap.model.entities.Receita;

public class Program {

	public static void main(String[] args) {

		try {

			ReceitaDAO receitaDao = DaoFactory.createReceitaDao();
			DespesaDAO despesaDao = DaoFactory.createDespesaDao();
			InvestimentoDAO investimentoDao = DaoFactory.createInvestimentoDao();

			// RECEITA
			System.out.println("\n=== TAREFA 1: CADASTRAR RECEITAS ===");
			for (int i = 1; i <= 5; i++) {
				
				Receita novaReceita = new Receita(null, 1, "Salário -> " + i, new Date(), "FIXO",
						new BigDecimal(2345.67 * i));
				receitaDao.cadastrar(novaReceita);
				System.out.println("Receita inserida com sucesso! Receita ID = " + novaReceita.getId());

			}

			System.out.println("\n=== TAREFA 2: LISTAR RECEITAS ===");
			List<Receita> listaReceitas = new ArrayList<>();
			listaReceitas = receitaDao.listar();
			for (Receita obj : listaReceitas) {
				System.out.println(obj);
			}

//		System.out.println("\n=== TAREFA 3: Buscar Receitas por ID ===");
//		Receita receita = receitaDao.buscaPorId(41);
//		System.out.println(receita);

			// DESPESA
			System.out.println("\n=== TAREFA 1: CADASTRAR DESPESA ===");
			for (int i = 1; i <= 5; i++) {

				Despesa novaDespesa = new Despesa(null, 1, "Aluguel -> " + i, new Date(), "FIXO",
						new BigDecimal(1050.60 * i));
				despesaDao.cadastrar(novaDespesa);
				System.out.println("Despesa inserida com sucesso! Despesa ID = " + novaDespesa.getId());

			}

			System.out.println("\n=== TAREFA 2: LISTAR DESPESAS ===");
			List<Despesa> listaDespesas = new ArrayList<>();
			listaDespesas = despesaDao.listar();
			for (Despesa obj : listaDespesas) {
				System.out.println(obj);
			}

//				System.out.println("\n=== TAREFA 3: Buscar Despesas por ID ===");
//				Despesa despesa = despesaDao.buscaPorId(41);
//				System.out.println(despesa);

			// INVESTIMENTO
			System.out.println("\n=== TAREFA 1: CADASTRAR INVESTIMENTO ===");
			for (int i = 1; i <= 5; i++) {

				Investimento novaInvestimento = new Investimento(null, 1, "Fundo imobiliário -> " + i, new Date(),
						"FIXO", new BigDecimal(2345.67 * i));
				investimentoDao.cadastrar(novaInvestimento);
				System.out.println("Investimento inserida com sucesso! Investimento ID = " + novaInvestimento.getId());

			}

			System.out.println("\n=== TAREFA 2: LISTAR INVESTIMENTOS ===");
			List<Investimento> listaInvestimentos = new ArrayList<>();
			listaInvestimentos = investimentoDao.listar();
			for (Investimento obj : listaInvestimentos) {
				System.out.println(obj);
			}

//			System.out.println("\n=== TAREFA 3: Buscar Investimentos por ID ===");
//			Investimento investimento = investimentoDao.buscaPorId(41);
//			System.out.println(investimento);
		} catch (RuntimeException e) {
			throw new DbException("Erro inesperado! - " + e.getMessage());
		} finally {
			DB.closeConnection();
		}
	}
}
