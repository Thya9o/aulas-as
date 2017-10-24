package br.edu.up.as.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import br.edu.up.as.entidade.Produto;
import br.edu.up.as.service.produtoService;
import br.edu.up.as.service.ServiceException;

public class TestarProduto {
	
	// vars para executar testes
	public static produtoService service = new produtoService();
	public static Produto testObject = new Produto();
	
    @BeforeClass
    public static void before() throws ServiceException {
    	// cria um produto para realizar os testes
    	testObject.setDescricao("Produto para execução de testes");
    	testObject.setValor(1);
        service.salvar(testObject);
    }

    @AfterClass
    public static void after() {
    	// exclui o produto criado apos executar todos os testes
        service.excluir(testObject);
    }
    
	@Test
	public void cadastrarSuccess() throws ServiceException {
		Produto o = new Produto();
		
		o.setDescricao("Teste");
		o.setValor(10.00);
		
		// salva o objeto
		service.salvar(o);
			
		// verifica se os dados cadastrados sao iguais
		Produto persistedObject = service.buscar(o.getId());
		assertEquals(true, o.getId().equals(persistedObject.getId()));
		assertEquals(true, o.getDescricao().equals(persistedObject.getDescricao()));
		assertEquals(true, o.getValor() == persistedObject.getValor());
		
		// exclui o objeto para nao poluir o banco
		service.excluir(o);
	}
	
	@Test(expected = ServiceException.class)
	public void cadastrarError() throws ServiceException {
		Produto o = new Produto();
		o.setDescricao(null);
		o.setValor(-10.00);
		
		// verifica se o cliente foi cadastrado com erro
		assertEquals(false, o.getId() != null);
		assertEquals(false, o.getValor() > 0);
		assertEquals(false, o.getDescricao() != null);
		assertEquals(false, service.buscar(o.getId()) != null);

		// tenta salvar um produto invalido
		service.salvar(o);
	}
	
	@Test
	public void buscarSuccess() {
		Produto o = service.buscar(service.listar().get(0).getId());
	
		// verifica se um objeto foi encontrado
		assertEquals(true, o != null);
		assertEquals(true, o.getId() != null);
	}
	
	@Test(expected = NullPointerException.class)
	public void buscarError() {
		Produto o = service.buscar(0);
		
		// verifica se nenhum objeto foi encontrado
		assertEquals(false, o != null);
		assertEquals(false, o.getId() != null);
	}
	
	@Test
	public void alterarSuccess() throws ServiceException {
		// altera o objeto
		testObject.setDescricao("Teste Alterado");
		testObject.setValor(15.00);
		service.alterar(testObject);
		
		// verifica se o objeto foi alterado
		assertEquals(true, testObject.getId() != null);
		assertEquals(true, service.buscar(testObject.getId()).getDescricao().equals("Teste Alterado"));
		assertEquals(true, service.buscar(testObject.getId()).getValor() == 15.00);
	}
	
	@Test(expected = ServiceException.class)
	public void alterarError() throws ServiceException {
		// altera o objeto
		testObject.setDescricao(null);
		testObject.setValor(-15.00);
		service.alterar(testObject);
		
		// verifica se o objeto foi alterado
		assertEquals(true, testObject.getId() != null);
		assertEquals(true, service.buscar(testObject.getId()).getDescricao() != null);
		assertEquals(true, service.buscar(testObject.getId()).getValor() >= 0.00);
	}
	
	@Test
	public void listarSuccess() {		
		// verifica se o tamanho da lista encontrada é maior que zero
		assertEquals(true, service.listar().size() > 0);
	}
	
	@Test
	public void excluirSuccess() throws ServiceException {
		Produto o = null;
		
		// salva um objeto para ser exluido
		o = new Produto();
		o.setDescricao("Teste Excluir");
		o.setValor(1);
		service.salvar(o);
		
		// verifica se o objeto foi salvo
		assertEquals(true, service.buscar(o.getId()) != null);
		
		// exclui o objeto
		service.excluir(o);
		
		// verifica se o objeto foi exluido com sucesso
		assertEquals(true, service.buscar(o.getId()) == null);
	}
}
