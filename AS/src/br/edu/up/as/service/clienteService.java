package br.edu.up.as.service;

import java.util.List;

import br.edu.up.as.dao.Dao;
import br.edu.up.as.dao.FactoryDao;
import br.edu.up.as.entidade.Cliente;

public class clienteService implements service<Cliente> {

	public Dao<Cliente> dao = FactoryDao.createClienteDao();
	
	public void salvar(Cliente o) throws ServiceException {
		if (!o.validar()) {
			throw new 
			ServiceException(o.getError());
		}
	
		dao.salvar(o);		
	}
	
	public void alterar(Cliente o) throws ServiceException {
		if (!o.validar()) {
			throw new 
			ServiceException(o.getError());
		}
		
		dao.alterar(o);
	}

	public void excluir(Cliente o) {
		dao.excluir(o);
	}

	public List<Cliente> listar() {
		return dao.listar();
	}

	public Cliente buscar(Integer id) {
		// verifica se o id não é nulo
		if(id == null) {
			return null;
		}
		return dao.buscar(id);
	}
}
