package br.edu.up.as.service;

import java.util.List;

import br.edu.up.as.entidade.Cliente;
import br.edu.up.as.entidade.Produto;
import br.edu.up.as.entidade.Servico;

public class servicoFacade implements service<Servico> {

	public Cliente cliente;
	public Produto produto;
	
	public clienteService serviceCliente = new clienteService();
	public produtoService serviceProduto = new produtoService();
	public servicoService serviceServico = new servicoService();
	
	public void salvar(Servico o) throws ServiceException {
		// verifica se o produto e valido
		if(!o.validar()) {
			throw new 
			ServiceException(o.getError());
		}
		
		cliente = serviceCliente.buscar(o.getCliente());
		produto = serviceProduto.buscar(o.getProduto());
		
		// verifica se o cliente existe e é valido
		// se nao existir, cadastra um novo cliente
		if(cliente != null && !cliente.validar()) {
			throw new 
			ServiceException(cliente.getError());
		}
		
		// verifica se o produto existe e é valido
		// se nao existir, cadastra um novo produto
		if(produto != null && !produto.validar()) {
			throw new 
			ServiceException(produto.getError());
		}
		
		serviceServico.salvar(o);
	}

	public void alterar(Servico o) throws ServiceException {
		// verifica se o produto e valido
		if(!o.validar()) {
			throw new 
			ServiceException(o.getError());
		}
		
		cliente = serviceCliente.buscar(o.getCliente());
		produto = serviceProduto.buscar(o.getProduto());
		
		// verifica se o cliente existe e é valido
		// se nao existir, cadastra um novo cliente
		if(cliente != null && !cliente.validar()) {
			throw new 
			ServiceException(cliente.getError());
		}
		
		// verifica se o produto existe e é valido
		// se nao existir, cadastra um novo produto
		if(produto != null && !produto.validar()) {
			throw new 
			ServiceException(produto.getError());
		}
		
		serviceServico.alterar(o);
	}

	public void excluir(Servico o) {
		serviceServico.excluir(o);
	}

	public List<Servico> listar() {
		return serviceServico.listar();
	}

	public Servico buscar(Integer id) {
		return serviceServico.buscar(id);
	}
}
