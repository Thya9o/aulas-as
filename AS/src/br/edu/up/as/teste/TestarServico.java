package br.edu.up.as.teste;

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import br.edu.up.as.entidade.Cliente;
import br.edu.up.as.entidade.Produto;
import br.edu.up.as.entidade.Servico;
import br.edu.up.as.service.servicoFacade;
import br.edu.up.as.service.ServiceException;
import br.edu.up.as.service.clienteService;
import br.edu.up.as.service.produtoService;

public class TestarServico {
	// vars para executar testes
	public static servicoFacade facade = new servicoFacade();
	
	public static Cliente cliente = new clienteService().listar().get(0);
	public static Produto produto = new produtoService().listar().get(0);
	public static Servico testObject = new Servico();
	
    @BeforeClass
    public static void before() throws ServiceException {
    	testObject.setCliente(cliente.getId());
    	testObject.setProduto(produto.getId());
    	testObject.setTotal(produto.getValor());
    	
    	facade.salvar(testObject);
    }

    @AfterClass
    public static void after() {
    	facade.excluir(testObject);
    }
    
	@Test
	public void cadastrarSuccess() throws ServiceException {
		Servico o = new Servico();
		o.setCliente(cliente.getId());
    	o.setProduto(produto.getId());
    	o.setTotal(produto.getValor());
    	
		// salva o objeto
		facade.salvar(o);
		
		// verifica os valores
		assertEquals(true, o.getId() != null);
		
		// verifica se os dados cadastrados sao iguais
		Servico persistedObject = facade.buscar(o.getId());
		assertEquals(true, o.getId() == persistedObject.getId());
		assertEquals(true, o.getCliente() == persistedObject.getCliente());
		assertEquals(true, o.getProduto() == persistedObject.getProduto());
		assertEquals(true, o.getTotal() == persistedObject.getTotal());
		
		// exclui o objeto para nao poluir o banco
		facade.excluir(o);
	}
	
	@Test(expected = ServiceException.class)
	public void cadastrarError() throws ServiceException {
		Servico o = new Servico();
		
		// verifica se o cliente foi cadastrado com erro
		assertEquals(false, o.getId() != null);

		// verifica se n�o salva o objeto
		facade.salvar(o);
		assertEquals(false, facade.buscar(o.getId()) != null);
	}
	
	@Test
	public void buscarSuccess() {
		Servico o = facade.buscar(facade.listar().get(0).getId());
	
		// verifica se um objeto foi encontrado
		assertEquals(true, o != null);
		assertEquals(true, o.getId() != null);
	}
	
	@Test(expected = NullPointerException.class)
	public void buscarError() {
		Servico o = facade.buscar(0);
		
		// verifica se nenhum objeto foi encontrado
		assertEquals(false, o != null);
		assertEquals(false, o.getId() != null);
	}
	
	@Test
	public void alterarSuccess() throws ServiceException {
		Servico o = facade.buscar(facade.listar().get(0).getId());
		Cliente novoCliente = new Cliente();
		Produto novoProduto = new Produto();
		
		novoCliente.setNome("Teste Servico");
		novoProduto.setDescricao("Teste Servico");
		novoProduto.setValor(15.00);
		
		// salva o objeto
		new clienteService().salvar(novoCliente);
		new produtoService().salvar(novoProduto);
		
		o.setCliente(novoCliente.getId());
    	o.setProduto(novoProduto.getId());
    	o.setTotal(novoProduto.getValor());
    	
		// altera o objeto
		facade.alterar(o);
		
		// verifica se o objeto foi alterado
		Servico persistedObject = facade.buscar(o.getId());
		assertEquals(true, persistedObject.getCliente() == novoCliente.getId());
		assertEquals(true, persistedObject.getProduto() == novoProduto.getId());
		assertEquals(true, persistedObject.getTotal() == novoProduto.getValor());
		
		new clienteService().excluir(novoCliente);
		new produtoService().excluir(novoProduto);
	}
	
	@Test(expected = ServiceException.class)
	public void alterarError() throws ServiceException {
		Servico o = facade.buscar(facade.listar().get(0).getId());
	
		o.setCliente(null);
		o.setProduto(null);
		o.setTotal(0);
		
		// verifica se o objeto foi alterado
		assertEquals(true, o.getId() != null);
		assertEquals(true, o.getCliente() == null);
		assertEquals(true, o.getProduto() == null);
		assertEquals(true, o.getTotal() == 0);
		
		facade.alterar(o);
		
		Servico persistedObject = facade.buscar(facade.listar().get(0).getId());
		assertEquals(true, persistedObject.getId() != null);
		assertEquals(true, persistedObject.getCliente() != null);
		assertEquals(true, persistedObject.getProduto() != null);
		assertEquals(true, persistedObject.getTotal() > 0);
	}
	
	@Test
	public void listarSuccess() {		
		// verifica se o tamanho da lista encontrada � maior que zero
		assertEquals(true, facade.listar().size() > 0);
	}
	
	@Test
	public void excluirSuccess() throws ServiceException {
		// salva um objeto para ser exluido
		Servico o = new Servico();
		Cliente novoCliente = new Cliente();
		Produto novoProduto = new Produto();
		
		novoCliente.setNome("Teste Servico");
		novoProduto.setDescricao("Teste Servico");
		novoProduto.setValor(15.00);
		
		// salva o objeto
		new clienteService().salvar(novoCliente);
		new produtoService().salvar(novoProduto);
		
		o.setCliente(novoCliente.getId());
    	o.setProduto(novoProduto.getId());
    	o.setTotal(novoProduto.getValor());
    	
		facade.salvar(o);
		
		// verifica se o objeto foi salvo
		assertEquals(true, facade.buscar(o.getId()) != null);
		
		// exclui o objeto
		facade.excluir(o);
		
		// verifica se o objeto foi exluido com sucesso
		assertEquals(true, facade.buscar(o.getId()) == null);
		
		new clienteService().excluir(novoCliente);
		new produtoService().excluir(novoProduto);
	}
}
