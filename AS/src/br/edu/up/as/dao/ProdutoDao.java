package br.edu.up.as.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.edu.up.as.entidade.Produto;

public class ProdutoDao implements Dao<Produto> {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("as");
	
	public void salvar(Produto o) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
	}

	public void excluir(Produto o) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.getReference(Produto.class, o.getId()));
		em.getTransaction().commit();
	}

	public void alterar(Produto o) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(o);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Produto> listar() {
		EntityManager em = emf.createEntityManager();
		return (List<Produto>)em.createQuery("SELECT p FROM Produto p").getResultList();
	}

	public Produto buscar(Integer id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Produto.class, id);
	}

}
