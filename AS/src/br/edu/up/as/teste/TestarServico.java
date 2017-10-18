package br.edu.up.as.teste;

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import br.edu.up.as.entidade.Servico;
import br.edu.up.as.service.servicoService;
import br.edu.up.as.service.ServiceException;

public class TestarServico {
	// vars para executar testes
	public static servicoService service = new servicoService();
	public static Servico testObject = new Servico();
	
    @BeforeClass
    public static void before() throws ServiceException {
    	service.salvar(testObject);
    }

    @AfterClass
    public static void after() {
    	service.excluir(testObject);
    }
    
	@Test
	public void cadastrarSuccess() throws ServiceException {
		Servico o = new Servico();

		// salva o objeto
		service.salvar(o);
		
		// verifica os valores
		assertEquals(true, o.getId() != null);
		
		// verifica se os dados cadastrados sao iguais
		Servico persistedObject = service.buscar(o.getId());
		assertEquals(true, o.getId() == persistedObject.getId());
		
		// exclui o objeto para nao poluir o banco
		service.excluir(o);
	}
	
	@Test(expected = ServiceException.class)
	public void cadastrarError() throws ServiceException {
		Servico o = new Servico();
		
		// verifica se o cliente foi cadastrado com erro
		assertEquals(false, o.getId() != null);

		// verifica se não salva o objeto
		service.salvar(o);
		assertEquals(false, service.buscar(o.getId()) != null);
	}
	
	@Test
	public void buscarSuccess() {
		Servico o = service.buscar(service.listar().get(0).getId());
	
		// verifica se um objeto foi encontrado
		assertEquals(true, o != null);
		assertEquals(true, o.getId() != null);
	}
	
	@Test(expected = NullPointerException.class)
	public void buscarError() {
		Servico o = service.buscar(0);
		
		// verifica se nenhum objeto foi encontrado
		assertEquals(false, o != null);
		assertEquals(false, o.getId() != null);
	}
	
	@Test
	public void alterarSuccess() throws ServiceException {
		Servico o = service.buscar(service.listar().get(0).getId());
	
		// altera o objeto
		service.alterar(o);
		
		// verifica se o objeto foi alterado
		assertEquals(true, o.getId() != null);
	}
	
	@Test(expected = ServiceException.class)
	public void alterarError() throws ServiceException {
		Servico o = service.buscar(service.listar().get(0).getId());
	
		// verifica se o objeto foi alterado
		assertEquals(true, o.getId() != null);

		service.alterar(o);
	}
	
	@Test
	public void listarSuccess() {		
		// verifica se o tamanho da lista encontrada é maior que zero
		assertEquals(true, service.listar().size() > 0);
	}
	
	@Test
	public void excluirSuccess() throws ServiceException {
		Servico o = null;
		
		// salva um objeto para ser exluido
		o = new Servico();
		service.salvar(o);
		
		// verifica se o objeto foi salvo
		assertEquals(true, service.buscar(o.getId()) != null);
		
		// exclui o objeto
		service.excluir(o);
		
		// verifica se o objeto foi exluido com sucesso
		assertEquals(true, service.buscar(o.getId()) == null);
	}
}
