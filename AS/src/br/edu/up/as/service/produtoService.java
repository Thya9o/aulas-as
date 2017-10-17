package br.edu.up.as.service;

import java.util.List;

import br.edu.up.as.dao.Dao;
import br.edu.up.as.dao.FactoryDao;
import br.edu.up.as.entidade.Produto;

public class produtoService implements service<Produto> {

	public void salvar(Produto o) throws ServiceException {
		// verifica se o objeto e valido
		if(o.getDescricao() == null || o.getDescricao().equals("") || o.getValor() >= 0) {
			throw new 
			ServiceException("ERR01 - O nome precisa ser preenchido.");
		}
		
		Dao<Produto> dao = FactoryDao.createProdutoDao();
		dao.salvar(o);
	}

	public void alterar(Produto o) throws ServiceException {
		// verifica se o objeto e valido
		if(o.getDescricao() == null || o.getDescricao().equals("") || o.getValor() >= 0) {
			throw new 
			ServiceException("ERR01 - O nome precisa ser preenchido.");
		}
		
		Dao<Produto> dao = FactoryDao.createProdutoDao();
		dao.alterar(o);	
	}

	public void excluir(Produto o) {
		Dao<Produto> dao = FactoryDao.createProdutoDao();
		dao.excluir(o);
	}

	public List<Produto> listar() {
		Dao<Produto> dao = FactoryDao.createProdutoDao();
		return dao.listar();
	}

	public Produto buscar(Integer id) {
		// verifica se o id não é nulo
		if(id == null) {
			return null;
		}
		
		Dao<Produto> dao = FactoryDao.createProdutoDao();
		return dao.buscar(id);
	}
}
