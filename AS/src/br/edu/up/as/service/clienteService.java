package br.edu.up.as.service;

import java.util.List;

import br.edu.up.as.dao.Dao;
import br.edu.up.as.dao.FactoryDao;
import br.edu.up.as.entidade.Cliente;

public class clienteService implements service<Cliente> {

	public void salvar(Cliente o) throws ServiceException {
		if (o.getNome() == null || o.getNome().equals("")) {
			throw new 
			ServiceException("ERR01 - O nome precisa ser preenchido.");
		}
	
		Dao<Cliente> dao = FactoryDao.createClienteDao();
		dao.salvar(o);		
	}
	
	public void alterar(Cliente o) throws ServiceException {
		if (o.getNome() == null || o.getNome().equals("")) {
			throw new 
			ServiceException("ERR01 - O nome precisa ser preenchido.");
		}
		
		Dao<Cliente> dao = FactoryDao.createClienteDao();
		dao.alterar(o);
	}

	@Override
	public void excluir(Cliente o) {
		// TODO Auto-generated method stub
		 
	}

	@Override
	public List<Cliente> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente buscar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
