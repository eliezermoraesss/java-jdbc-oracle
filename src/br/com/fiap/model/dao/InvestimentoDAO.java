package br.com.fiap.model.dao;

import java.util.List;

import br.com.fiap.model.entities.Investimento;

public interface InvestimentoDAO {
	
	public List<Investimento> listar();
	public Investimento buscaPorId(Integer id);
	public void cadastrar(Investimento obj);
	public void atualizar(Investimento obj);
	public void remover(Integer id);

}