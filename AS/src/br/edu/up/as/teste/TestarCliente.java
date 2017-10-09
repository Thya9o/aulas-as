package br.edu.up.as.teste;
import org.junit.Test;

import br.edu.up.as.dao.ClienteDao;
import br.edu.up.as.entidade.Cliente;

import static org.junit.Assert.*;

public class TestarCliente {
	
	@Test
	public void cadastrarCliente() {
		
		Cliente o = new Cliente();
		o.setId(null);
		o.setNome("Thiago");
		
		new ClienteDao().salvar(o);
		
		assertEquals(true, o.getId() != null);
	}
}
