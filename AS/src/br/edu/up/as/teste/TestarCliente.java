package br.edu.up.as.teste;
 
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import br.edu.up.as.entidade.Cliente;
import br.edu.up.as.service.clienteService;
import br.edu.up.as.service.ServiceException;


public class TestarCliente {
	
	// vars para executar testes
	public static clienteService service= new clienteService();
	public static Cliente testObject = new Cliente();
	
    @BeforeClass
    public static void before() throws ServiceException {
    	testObject.setNome("Cliente para execução de testes");
    	testObject.setCpf("00000000000");
    	service.salvar(testObject);
    }

    @AfterClass
    public static void after() {
    	service.excluir(testObject);
    }
    
	@Test
	public void cadastrarSuccess() throws ServiceException {
		Cliente o = new Cliente();
		o.setNome("Teste");
		o.setCpf("00000000000");
		
		// salva o objeto
		service.salvar(o);
		
		// verifica os valores
		assertEquals(true, o.getId() != null);
		
		// verifica se os dados cadastrados sao iguais
		Cliente persistedObject = service.buscar(o.getId());
		assertEquals(true, o.getId() == persistedObject.getId());
		
		// exclui o objeto para nao poluir o banco
		service.excluir(o);
	}
	
	@Test(expected = ServiceException.class)
	public void cadastrarError() throws ServiceException {
		Cliente o = new Cliente();
		
		o.setNome(null);
		o.setCpf(null);
		
		// verifica se o cliente foi cadastrado com erro
		assertEquals(false, o.getId() != null);
		assertEquals(false, o.getNome() != null);
		assertEquals(false, o.getCpf() != null);
		
		// verifica se não salva o objeto
		service.salvar(o);
		assertEquals(false, service.buscar(o.getId()) != null);
	}
	
	@Test
	public void buscarSuccess() {
		Cliente o = service.buscar(service.listar().get(0).getId());
	
		// verifica se um objeto foi encontrado
		assertEquals(true, o != null);
		assertEquals(true, o.getId() != null);
	}
	
	@Test
	public void alterarSuccess() throws ServiceException {
		// altera o objeto
		testObject.setNome("Teste Alterado");
		testObject.setCpf("00000000001");
		service.alterar(testObject);
		
		// verifica se o objeto foi alterado
		assertEquals(true, testObject.getId() != null);
		assertEquals(true, service.buscar(testObject.getId()).getNome().equals("Teste Alterado"));
		assertEquals(true, service.buscar(testObject.getId()).getCpf().equals("00000000001"));
	}
	
	@Test(expected = ServiceException.class)
	public void alterarError() throws ServiceException {
		// altera o objeto
		testObject.setNome(null);
		testObject.setCpf(null);
		
		// verifica se o objeto foi alterado
		assertEquals(true, testObject.getId() != null);
		assertEquals(true, service.buscar(testObject.getId()).getNome() != null);
		assertEquals(true, service.buscar(testObject.getId()).getCpf() != null);
		
		service.alterar(testObject);
	}
	
	@Test
	public void listarSuccess() {		
		// verifica se o tamanho da lista encontrada é maior que zero
		assertEquals(true, service.listar().size() > 0);
	}
	
	@Test
	public void excluirSuccess() throws ServiceException {
		// salva um objeto para ser exluido
		Cliente o = new Cliente();
		o.setNome("Teste Excluir");
		o.setCpf("00000000000");
		service.salvar(o);
		
		// verifica se o objeto foi salvo
		assertEquals(true, service.buscar(o.getId()) != null);
		
		// exclui o objeto
		service.excluir(o);
		
		// verifica se o objeto foi exluido com sucesso
		assertEquals(true, service.buscar(o.getId()) == null);
	}
}
