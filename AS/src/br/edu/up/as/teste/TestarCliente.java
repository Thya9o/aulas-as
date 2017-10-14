package br.edu.up.as.teste;

import org.junit.Test;

import br.edu.up.as.dao.ClienteDao;
import br.edu.up.as.entidade.Cliente;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

public class TestarCliente {
	
	// vars para executar testes
	public static ClienteDao dao = new ClienteDao();
	public static Cliente testObject = new Cliente();
	
    @BeforeClass
    public static void before() {
    	testObject.setNome("Cliente para execução de testes");
        dao.salvar(testObject);
    }

    @AfterClass
    public static void after() {
        dao.excluir(testObject);
    }
    
	@Test
	public void cadastrarSuccess() {
		Cliente o = new Cliente();
		o.setNome("Teste");
		
		// salva o objeto
		dao.salvar(o);
		
		// verifica os valores
		assertEquals(true, o.getId() != null);
		
		// verifica se os dados cadastrados sao iguais
		Cliente persistedObject = dao.buscar(o.getId());
		assertEquals(true, o.getId() == persistedObject.getId());
		
		// exclui o objeto para nao poluir o banco
		dao.excluir(o);
	}
	
	@Test
	public void cadastrarError() {
		Cliente o = new Cliente();
		
		o.setNome(null);
		
		// verifica se o cliente foi cadastrado com erro
		assertEquals(false, o.getId() != null);
		assertEquals(false, o.getNome() != null);
		assertEquals(false, dao.buscar(o.getId()) != null);
	}
	
	@Test
	public void buscarSuccess() {
		Cliente o = dao.buscar(dao.listar().get(0).getId());
	
		// verifica se um objeto foi encontrado
		assertEquals(true, o != null);
		assertEquals(true, o.getId() != null);
	}
	
	@Test(expected = NullPointerException.class)
	public void buscarError() {
		Cliente o = dao.buscar(0);
		
		// verifica se nenhum objeto foi encontrado
		assertEquals(false, o != null);
		assertEquals(false, o.getId() != null);
	}
	
	@Test
	public void alterarSuccess() {
		Cliente o = dao.buscar(dao.listar().get(0).getId());
	
		// altera o objeto
		o.setNome("Teste Alterado");
		dao.alterar(o);
		
		// verifica se o objeto foi alterado
		assertEquals(true, o.getId() != null);
		assertEquals(true, dao.buscar(o.getId()).getNome().equals("Teste Alterado"));
	}
	
	@Test
	public void alterarError() {
		Cliente o = dao.buscar(dao.listar().get(0).getId());
	
		// altera o objeto
		o.setNome(null);
		dao.alterar(o);
		
		// verifica se o objeto foi alterado
		assertEquals(true, o.getId() != null);
		assertEquals(true, dao.buscar(o.getId()).getNome() != null);
	}
	
	@Test
	public void listarSuccess() {		
		// verifica se o tamanho da lista encontrada é maior que zero
		assertEquals(true, dao.listar().size() > 0);
	}
	
	@Test
	public void excluirSuccess() {
		Cliente o = null;
		
		// salva um objeto para ser exluido
		o = new Cliente();
		o.setNome("Teste Excluir");
		dao.salvar(o);
		
		// verifica se o objeto foi salvo
		assertEquals(true, dao.buscar(o.getId()) != null);
		
		// exclui o objeto
		dao.excluir(o);
		
		// verifica se o objeto foi exluido com sucesso
		assertEquals(true, dao.buscar(o.getId()) == null);
	}
}
