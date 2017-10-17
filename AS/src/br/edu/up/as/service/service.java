package br.edu.up.as.service;

import java.util.List;

public interface service <O> {
	
	public void salvar(O o) throws ServiceException;
	public void alterar(O o) throws ServiceException;
	public void excluir(O o);
	public List<O> listar();
	public O buscar(Integer id);
	
}
