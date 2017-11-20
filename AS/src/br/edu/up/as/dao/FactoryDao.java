package br.edu.up.as.dao;

import br.edu.up.as.entidade.Cliente;
import br.edu.up.as.entidade.Produto;
import br.edu.up.as.entidade.Servico;

/**
 * Factory
 * 
 * @desc Utiliza o design pattern "factory", atuando como uma interface para criação de objetos de forma dinâmica
 * @author Thiago
 */
public class FactoryDao<O> {
	
	/**
	 * @deprecated
	 */
	public static Dao<?> createDao(String type) {
		switch(type) {
			case "Cliente":
				return (Dao<Cliente>)createClienteDao();
			case "Produto":
				return (Dao<Produto>)createProdutoDao();
			default:
				return null;
		}
	}
	
	// retorna uma instancia do clienteDao para persistir a entidade cliente
	public static Dao<Cliente> createClienteDao() {
		return new ClienteDao();
	}
	
	// retorna uma instancia do servicoDao para persistir a entidade produto
	public static Dao<Produto> createProdutoDao() {
		return new ProdutoDao();
	}
	
	// retorna uma instancia do servicoDao para persistir a entidade servico
	public static Dao<Servico> createServicoDao() {
		return new ServicoDao();
	}
}
