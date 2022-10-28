package br.com.fiap.model.dao;

import java.util.List;

import br.com.fiap.model.entities.Usuario;

public interface UsuarioDAO {
	
	public List<Usuario> listar();
	public Usuario buscaPorId(Integer id);
	public void cadastrar(Usuario obj);
	public void atualizar(Usuario obj);
	public void remover(Integer id);

}