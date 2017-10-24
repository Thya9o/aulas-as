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
	public static clienteService serviceCliente = new clienteService();
	public static produtoService serviceProduto = new produtoService();

	// objetos para teste
	public static Cliente cliente = new Cliente();
	public static Produto produto = new Produto();
	public static Servico testObject = new Servico();
	
    @BeforeClass
    public static void before() throws ServiceException {
      
      // cadastra objetos de testes
      cliente.setNome("Teste de Cliente no Servico");
      serviceCliente.salvar(cliente);;    	
      produto.setDescricao("Teste de Produto no Servico");
      produto.setValor(10.00);
      serviceProduto.salvar(produto);
	
    	// salva um novo servico para testes
    	testObject.setCliente(cliente.getId());
    	testObject.setProduto(produto.getId());
    	testObject.setTotal(produto.getValor());
    	
    	facade.salvar(testObject);
    }

    @AfterClass
    public static void after() {
    	facade.excluir(testObject);
    	serviceCliente.excluir(cliente);
		  serviceProduto.excluir(produto)
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
		assertEquals(true, o.getId().equals(persistedObject.getId()));
		assertEquals(true, o.getCliente().equals(persistedObject.getCliente()));
		assertEquals(true, o.getProduto().equals(persistedObject.getProduto()));
		assertEquals(true, o.getTotal() == persistedObject.getTotal());
		
		// exclui o objeto para nao poluir o banco
		facade.excluir(o);
	}
	
	@Test(expected = ServiceException.class)
	public void cadastrarError() throws ServiceException {
		Servico o = new Servico();
		
		// verifica se o cliente foi cadastrado com erro
		assertEquals(false, o.getId() != null);

		// verifica se não salva o objeto
		facade.salvar(o);
		assertEquals(false, facade.buscar(o.getId()) != null);
		
		// exclui o objeto para nao poluir o banco
		facade.excluir(o);
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
		Cliente novoCliente = new Cliente();
		Produto novoProduto = new Produto();
		
		novoCliente.setNome("Teste Servico");
		novoProduto.setDescricao("Teste Servico");
		novoProduto.setValor(15.00);
		
		// salva o objeto
		serviceCliente.salvar(novoCliente);
		serviceProduto.salvar(novoProduto);
		
		testObject.setCliente(novoCliente.getId());
		testObject.setProduto(novoProduto.getId());
		testObject.setTotal(novoProduto.getValor());
    	
		// altera o objeto
		facade.alterar(testObject);
		
		// verifica se o objeto foi alterado
		Servico persistedObject = facade.buscar(testObject.getId());
		
		assertEquals(true, persistedObject.getCliente().equals(novoCliente.getId()));
		assertEquals(true, persistedObject.getProduto().equals(novoProduto.getId()));
		assertEquals(true, persistedObject.getTotal() == novoProduto.getValor());
		
		// exclui o objeto para nao poluir o banco
		serviceCliente.excluir(novoCliente);
		serviceProduto.excluir(novoProduto);
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
		// verifica se o tamanho da lista encontrada é maior que zero
		assertEquals(true, facade.listar().size() > 0);
	}
	
	@Test
	public void excluirSuccess() throws ServiceException {
		// salva um objeto para ser exluido
		Servico o = new Servico();
		
		o.setCliente(cliente.getId());
    	o.setProduto(produto.getId());
    	o.setTotal(produto.getValor());
    	
		facade.salvar(o);
		
		// verifica se o objeto foi salvo
		assertEquals(true, facade.buscar(o.getId()) != null);
		
		// exclui o objeto
		facade.excluir(o);
		
		// verifica se o objeto foi exluido com sucesso
		assertEquals(true, facade.buscar(o.getId()) == null);
	}
}
