package br.edu.up.as.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;

import br.edu.up.as.dao.ProdutoDao;
import br.edu.up.as.entidade.Produto;

public class TestarProduto {
	
	public ProdutoDao dao = new ProdutoDao();
	
	@Test
	public void cadastrarSuccess() {
		Produto o = new Produto();
		
		o.setDescricao("Teste");
		o.setValor(10.00);
		
		this.dao.salvar(o);
		
		// verifica os valores
		assertEquals(true, o.getId() != null);
		assertEquals(true, o.getId() != null);
		
		// verifica se os dados cadastrados sao iguais
		Produto persistedObject = this.dao.buscar(o.getId());
		assertEquals(true, o.getId() == persistedObject.getId());
		assertEquals(true, o.getDescricao() == persistedObject.getDescricao());
		assertEquals(true, o.getValor() == persistedObject.getValor());
	}
	
	@Test
	public void cadastrarError() {
		Produto o = new Produto();
		
		o.setDescricao(null);
		o.setValor(-10.00);
		
		this.dao.salvar(o);
		
		// verifica se o cliente foi cadastrado com erro
		assertEquals(false, o.getValor() > 0);
		assertEquals(false, o.getDescricao() != null);
	}
	
	@Test
	public void buscarSuccess() {
		Produto o = this.dao.buscar(this.dao.listar().size() - 1);
	
		// verifica se um objeto foi encontrado
		assertEquals(true, o.getId() != null);
	}
	
	@Test(expected = NullPointerException.class)
	public void buscarError() {
		Produto o = this.dao.buscar(0);
		
		// verifica se nenhum objeto foi encontrado
		assertEquals(false, o.getId() != null);
	}
	
	@Test
	public void alterarSuccess() {
		Produto o = this.dao.buscar(this.dao.listar().size());
	
		// altera o objeto
		o.setDescricao("Teste Alterado");
		o.setValor(15.00);
		dao.alterar(o);
		
		// verifica se o objeto foi alterado
		assertEquals(true, o.getId() != null);
		assertEquals(true, dao.buscar(o.getId()).getDescricao() == "Teste Alterado");
		assertEquals(true, dao.buscar(o.getId()).getValor() == 15.00);
	}
	
	@Test
	public void listarSuccess() {
		// verifica se o tamanho da lista encontrada é maior que zero
		assertEquals(true, this.dao.listar().size() > 0);
	}
	
	@Test
	public void excluirSuccess() {
		Produto o = null;
		
		// salva um objeto para ser exluido
		o = new Produto();
		o.setDescricao("Teste Excluir");
		this.dao.salvar(o);
		
		// verifica se o objeto foi salvo
		assertEquals(true, this.dao.buscar(o.getId()) == null);
		
		// exclui o objeto
		this.dao.excluir(o);
		
		// verifica se o objeto foi exluido com sucesso
		assertEquals(true, this.dao.buscar(o.getId()) == null);
	}
}
