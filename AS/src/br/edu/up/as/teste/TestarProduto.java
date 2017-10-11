package br.edu.up.as.teste;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.up.as.dao.ProdutoDao;
import br.edu.up.as.entidade.Produto;

public class TestarProduto {
	
	// vars para executar testes
	public static ProdutoDao dao = new ProdutoDao();
	public static Produto testObject = new Produto();
	
    @BeforeClass
    public static void before() {
    	// cria um produto para realizar os testes
    	testObject.setDescricao("Produto para execução de testes");
        dao.salvar(testObject);
    }

    @AfterClass
    public static void after() {
    	// exclui o produto criado apos executar todos os testes
        dao.excluir(testObject);
    }
    
	@Test
	public void cadastrarSuccess() {
		Produto o = new Produto();
		
		o.setDescricao("Teste");
		o.setValor(10.00);
		
		// salva o objeto
		dao.salvar(o);
		
		// verifica os valores
		assertEquals(true, o.getId() != null);
		assertEquals(true, o.getDescricao() != null);
		assertEquals(true, o.getValor() > 0);
		
		// verifica se os dados cadastrados sao iguais
		Produto persistedObject = dao.buscar(o.getId());
		assertEquals(true, o.getId() == persistedObject.getId());
		assertEquals(true, o.getDescricao().equals(persistedObject.getDescricao()));
		assertEquals(true, o.getValor() == persistedObject.getValor());
		
		// exclui o objeto para nao poluir o banco
		dao.excluir(o);
	}
	
	@Test
	public void cadastrarError() {
		Produto o = new Produto();
		o.setDescricao(null);
		o.setValor(-10.00);
		
		// tenta salvar um produto invalido
		dao.salvar(o);
		
		// verifica se o cliente foi cadastrado com erro
		assertEquals(false, o.getId() != null);
		assertEquals(false, o.getValor() > 0);
		assertEquals(false, o.getDescricao() != null);
		assertEquals(false, dao.buscar(o.getId()) != null);
	}
	
	@Test
	public void buscarSuccess() {
		Produto o = dao.buscar(dao.listar().get(0).getId());
	
		// verifica se um objeto foi encontrado
		assertEquals(true, o != null);
		assertEquals(true, o.getId() != null);
	}
	
	@Test(expected = NullPointerException.class)
	public void buscarError() {
		Produto o = dao.buscar(0);
		
		// verifica se nenhum objeto foi encontrado
		assertEquals(false, o != null);
		assertEquals(false, o.getId() != null);
	}
	
	@Test
	public void alterarSuccess() {
		Produto o = dao.buscar(dao.listar().get(0).getId());
	
		// altera o objeto
		o.setDescricao("Teste Alterado");
		o.setValor(15.00);
		dao.alterar(o);
		
		// verifica se o objeto foi alterado
		assertEquals(true, o.getId() != null);
		assertEquals(true, dao.buscar(o.getId()).getDescricao().equals("Teste Alterado"));
		assertEquals(true, dao.buscar(o.getId()).getValor() == 15.00);
	}
	
	@Test
	public void alterarError() {
		Produto o = dao.buscar(dao.listar().get(0).getId());
	
		// altera o objeto
		o.setDescricao(null);
		o.setValor(-15.00);
		dao.alterar(o);
		
		// verifica se o objeto foi alterado
		assertEquals(true, o.getId() != null);
		assertEquals(true, dao.buscar(o.getId()).getDescricao() != null);
		assertEquals(true, dao.buscar(o.getId()).getValor() >= 0.00);
	}
	
	@Test
	public void listarSuccess() {		
		// verifica se o tamanho da lista encontrada é maior que zero
		assertEquals(true, dao.listar().size() > 0);
	}
	
	@Test
	public void excluirSuccess() {
		Produto o = null;
		
		// salva um objeto para ser exluido
		o = new Produto();
		o.setDescricao("Teste Excluir");
		dao.salvar(o);
		
		// verifica se o objeto foi salvo
		assertEquals(true, dao.buscar(o.getId()) != null);
		
		// exclui o objeto
		dao.excluir(o);
		
		// verifica se o objeto foi exluido com sucesso
		assertEquals(true, dao.buscar(o.getId()) == null);
	}
}
