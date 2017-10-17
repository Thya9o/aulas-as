package br.edu.up.as.dao;
import java.util.List;

public interface Dao<O> {
	
	public void salvar(O o);
	public void alterar(O o);
	public void excluir(O o);
	public List<O> listar();
	public O buscar(Integer id);

}
