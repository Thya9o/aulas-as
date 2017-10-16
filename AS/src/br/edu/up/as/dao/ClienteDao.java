package br.edu.up.as.dao;
import java.util.List;

import javax.persistence.EntityManager;
import br.edu.up.as.entidade.Cliente;

public class ClienteDao implements Dao<Cliente> {
	
	public void salvar(Cliente o) {
		if(o.getNome() != null) {			
			EntityManager em = Conexao.getEntityManager();
			em.getTransaction().begin();
			em.persist(o);
			em.getTransaction().commit();
		}
	}
	
	public void excluir(Cliente o) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.getReference(Cliente.class, o.getId()));
		em.getTransaction().commit();
	}
	
	public void alterar(Cliente o) {
		if(o.getNome() != null) {
			EntityManager em = Conexao.getEntityManager();
			em.getTransaction().begin();
			em.merge(o);
			em.getTransaction().commit();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listar() {
		EntityManager em = Conexao.getEntityManager();
		return (List<Cliente>)em.createQuery("SELECT c FROM Cliente c").getResultList();
	}
	
	public Cliente buscar(Integer id) {
		// valida se o id nao e nulo
		if(id == null) {
			return null;
		}
		
		EntityManager em = Conexao.getEntityManager();
		return em.find(Cliente.class, id);
	}
}
