package br.edu.up.as.dao;

import br.edu.up.as.entidade.Cliente;
import br.edu.up.as.entidade.Produto;

public class FactoryDao<O> {
	
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
	
	public static Dao<Cliente> createClienteDao() {
		return new ClienteDao();
	}
	
	public static Dao<Produto> createProdutoDao() {
		return new ProdutoDao();
	}
}
