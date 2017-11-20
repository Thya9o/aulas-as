package br.edu.up.as.dao;

import java.util.List;
import br.edu.up.as.entidade.Cliente;
import javax.persistence.EntityManager;

public class ClienteDao implements Dao<Cliente> {
	
	public void salvar(Cliente o) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
	}
	
	public void excluir(Cliente o) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.getReference(Cliente.class, o.getId()));
		em.getTransaction().commit();
	}
	
	public void alterar(Cliente o) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		em.merge(o);
		em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listar() {
		EntityManager em = Conexao.getEntityManager();
		return (List<Cliente>)em.createQuery("select c from Cliente c order by id").getResultList();
	}
	
	public Cliente buscar(Integer id) {
		EntityManager em = Conexao.getEntityManager();
		return em.find(Cliente.class, id);
	}
}
