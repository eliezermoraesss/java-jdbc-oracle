package br.com.fiap.model.dao;

import java.util.List;

import br.com.fiap.model.entities.Despesa;

public interface DespesaDAO {
	
	public List<Despesa> listar();
	public Despesa buscaPorId(Integer id);
	public void cadastrar(Despesa obj);
	public void atualizar(Despesa obj);
	public void remover(Integer id);

}