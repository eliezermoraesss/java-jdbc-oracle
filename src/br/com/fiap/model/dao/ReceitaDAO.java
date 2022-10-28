package br.com.fiap.model.dao;

import java.util.List;

import br.com.fiap.model.entities.Receita;

public interface ReceitaDAO {
	
	public List<Receita> listar();
	public Receita buscaPorId(Integer idReceita);
	public void cadastrar(Receita obj);
	public void atualizar(Receita obj);
	public void remover(Integer id);
	
}