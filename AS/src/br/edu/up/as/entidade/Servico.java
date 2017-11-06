package br.edu.up.as.entidade;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@XmlRootElement
public class Servico {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer cliente;
	private Integer produto;
	private double total;
	
	@Transient
	private String error;
	
	// getters
	public Integer getId() {
		return id;
	}
	public Integer getCliente() {
		return cliente;
	}
	public Integer getProduto() {
		return produto;
	}
	public double getTotal() {
		return total;
	}
	public String getError() {
		return error;
	}
	
	// setters
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	public void setProduto(Integer produto) {
		this.produto = produto;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	/**
	 * Validar Objeto
	 * @desc valida se o objeto é valido para ser persistido
	 * @return boolean
	 */
	public boolean validar() {
		// msg de erro default
		this.setError("Erro0 - Houve um erro não previsto.");
		
		if(this.getCliente() == null) {
			this.setError("ERR01 - O cliente precisa ser selecionado");
			return false;
		}else if(this.getProduto() == null) {
			this.setError("ERR02 - O produto precisa ser selecionado");
			return false;
		}else if(this.getTotal() <= 0) {
			this.setError("ERR03 - O valor total precisa ser preenchido e maior que zero.");
			return false;
		}
		return true;
	}
}
