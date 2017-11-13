package br.edu.up.as.rest;

import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.edu.up.as.entidade.Cliente;
import br.edu.up.as.service.clienteService;
import br.edu.up.as.service.ServiceException;

@Path("/detalhe-cliente")
public class ClienteRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Cliente> listarClientes() {
		List<Cliente> lista = new clienteService().listar();
		return new ArrayList<>(lista);
	}
		
	@GET
	@Path("/buscar")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente buscarSuccess(@QueryParam("id") Integer id) {
		Cliente o = new clienteService().buscar(id);
		return o;
	}
	
	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void cadastrarCliente(Cliente o) {
		try {
			new clienteService().salvar(o);
		}catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@POST
	@Path("/alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void alterarCliente(Cliente o) {
		try {
			new clienteService().alterar(o);
		}catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	/*@DELETE
	@Path("/deletar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deletarCliente(Cliente o) {
		new clienteService().excluir(o);
	}*/
	
	@DELETE
	@Path("/deletar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deletarCliente(@QueryParam("id") Integer id) {
		Cliente o = new clienteService().buscar(id);
		if(o != null) {			
			new clienteService().excluir(o);
		}
	}
}
