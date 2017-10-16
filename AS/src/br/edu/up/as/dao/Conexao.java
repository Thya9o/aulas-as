package br.edu.up.as.dao;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Conexao {
	
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("as");
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
