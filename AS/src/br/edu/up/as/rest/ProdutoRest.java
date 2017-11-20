package br.edu.up.as.rest;

import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.edu.up.as.entidade.Produto;
import br.edu.up.as.service.produtoService;
import br.edu.up.as.service.ServiceException;

@Path("/detalhe-produto")
public class ProdutoRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Produto> listarProdutos() {
		List<Produto> lista = new produtoService().listar();
		return new ArrayList<>(lista);
	}
	
	@GET
	@Path("/buscar")
	@Produces(MediaType.APPLICATION_JSON)
	public Produto buscarProduto(@QueryParam("id") Integer id) {
		Produto o = new produtoService().buscar(id);
		return o;
	}
	
	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void cadastrarProduto(Produto o) {
		try {
			new produtoService().salvar(o);
		}catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@POST
	@Path("/alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void alterarProduto(Produto o) {
		try {
			new produtoService().alterar(o);
		}catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@POST
	@Path("/deletar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deletarProduto(String id) {
		Produto o = new produtoService().buscar(Integer.parseInt(id));
		if(o != null) {			
			new produtoService().excluir(o);
		}
	}
}
