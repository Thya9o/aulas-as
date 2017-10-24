package br.edu.up.as.dao;

import java.util.List;
import br.edu.up.as.entidade.Cliente;
import br.edu.up.as.entidade.Servico;
import javax.persistence.EntityManager;

public class ServicoDao implements Dao<Servico> {

	public void salvar(Servico o) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
	}

	public void alterar(Servico o) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.getReference(Cliente.class, o.getId()));
		em.getTransaction().commit();
	}

	public void excluir(Servico o) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		em.merge(o);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Servico> listar() {
		EntityManager em = Conexao.getEntityManager();
		return (List<Servico>)em.createQuery("SELECT s FROM Servico s").getResultList();
	}

	public Servico buscar(Integer id) {
		EntityManager em = Conexao.getEntityManager();
		return em.find(Servico.class, id);
	}
}
