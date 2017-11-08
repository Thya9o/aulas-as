package br.edu.up.as.rest;

import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import br.edu.up.as.entidade.Servico;
import br.edu.up.as.service.servicoService;
import br.edu.up.as.service.ServiceException;

@Path("/detalhe-servico")
public class ServicoRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Servico> listarServicos() {
		List<Servico> lista = new servicoService().listar();
		return new ArrayList<>(lista);
	}
	
	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void cadastrarServico(Servico o) {
		try {
			new servicoService().salvar(o);
		}catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@GET
	@Path("/buscar")
	@Produces(MediaType.APPLICATION_JSON)
	public Servico buscarServico(@QueryParam("id") Integer id) {
		Servico o = new servicoService().buscar(id);
		return o;
	}
	
	@POST
	@Path("/alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void alterarServico(Servico o) {
		try {
			new servicoService().alterar(o);
		}catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Path("/deletar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deletarServico(Servico o) {
		new servicoService().excluir(o);
	}
	
	/*@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void deletarServico(@QueryParam("id") Integer id) {
		Servico o = new servicoService().buscar(id);
		if(o != null) {			
			new servicoService().excluir(o);
		}
	}*/
}
