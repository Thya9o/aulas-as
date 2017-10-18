package br.edu.up.as.service;

import java.util.List;

import br.edu.up.as.dao.Dao;
import br.edu.up.as.dao.FactoryDao;
import br.edu.up.as.entidade.Produto;

public class produtoService implements service<Produto> {

	public Dao<Produto> dao = FactoryDao.createProdutoDao();

	public void salvar(Produto o) throws ServiceException {
		// verifica se o objeto e valido
		if(!o.validar()) {
			throw new 
			ServiceException(o.getError());
		}
		
		dao.salvar(o);
	}

	public void alterar(Produto o) throws ServiceException {
		// verifica se o objeto e valido
		if(!o.validar()) {
			throw new 
			ServiceException(o.getError());
		}
		
		dao.alterar(o);	
	}

	public void excluir(Produto o) {
		dao.excluir(o);
	}

	public List<Produto> listar() {
		return dao.listar();
	}

	public Produto buscar(Integer id) {
		// verifica se o id não é nulo
		if(id == null) {
			return null;
		}
		
		return dao.buscar(id);
	}
}
