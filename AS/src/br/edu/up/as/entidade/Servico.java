package br.edu.up.as.entidade;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Servico {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Cliente cliente;
	private List<Produto> produto;
	private float total;
	
	// getters
	public Integer getId() {
		return id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public List<Produto> getProduto() {
		return produto;
	}
	public float getTotal() {
		return total;
	}
	
	// setters
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	public void setTotal(float total) {
		this.total = total;
	}	
}
