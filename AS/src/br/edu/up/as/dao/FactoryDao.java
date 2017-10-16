package br.edu.up.as.dao;

import br.edu.up.as.entidade.Cliente;
import br.edu.up.as.entidade.Produto;

public class FactoryDao {
	
	public static Dao<?> createDao(String type) {
		switch(type) {
			case "Cliente":
				return createClienteDao();
			case "Produto":
				return createProdutoDao();
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
