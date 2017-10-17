package br.edu.up.as.service;

import java.util.List;

import br.edu.up.as.dao.Dao;
import br.edu.up.as.dao.FactoryDao;
import br.edu.up.as.entidade.Cliente;

public class clienteService implements service<Cliente> {

	public void salvar(Cliente o) throws ServiceException {
		if (!o.validar()) {
			throw new 
			ServiceException(o.getError());
		}
	
		Dao<Cliente> dao = FactoryDao.createClienteDao();
		dao.salvar(o);		
	}
	
	public void alterar(Cliente o) throws ServiceException {
		if (!o.validar()) {
			throw new 
			ServiceException(o.getError());
		}
		
		Dao<Cliente> dao = FactoryDao.createClienteDao();
		dao.alterar(o);
	}

	public void excluir(Cliente o) {
		Dao<Cliente> dao = FactoryDao.createClienteDao();
		dao.excluir(o);
	}

	public List<Cliente> listar() {
		Dao<Cliente> dao = FactoryDao.createClienteDao();
		return dao.listar();
	}

	public Cliente buscar(Integer id) {
		// verifica se o id não é nulo
		if(id == null) {
			return null;
		}
		
		Dao<Cliente> dao = FactoryDao.createClienteDao();
		return dao.buscar(id);
	}
}
