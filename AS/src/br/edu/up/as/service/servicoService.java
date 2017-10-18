package br.edu.up.as.service;

import java.util.List;

import br.edu.up.as.dao.Dao;
import br.edu.up.as.dao.FactoryDao;
import br.edu.up.as.entidade.Servico;

public class servicoService implements service<Servico> {

	public Dao<Servico> dao = FactoryDao.createServicoDao();
	
	public void salvar(Servico o) throws ServiceException {
		dao.salvar(o);
	}

	public void alterar(Servico o) throws ServiceException {
		dao.alterar(o);
	}

	public void excluir(Servico o) {
		dao.excluir(o);
	}

	public List<Servico> listar() {
		return dao.listar();
	}

	public Servico buscar(Integer id) {
		return dao.buscar(id);
	}
}
