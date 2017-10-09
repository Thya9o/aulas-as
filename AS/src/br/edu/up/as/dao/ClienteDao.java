package br.edu.up.as.dao;
import java.util.List;

import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.edu.up.as.entidade.Cliente;

public class ClienteDao implements Dao<Cliente> {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("as");
	
	public void salvar(Cliente o) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
	}
	
	public void excluir(Cliente o) {
		
	}
	
	public void alterar(Cliente o) {
		
	}
	
	public List<Cliente> listar() {
		return null;
	}
	
	public Cliente buscar(Integer id) {
		return null;
	}
}
