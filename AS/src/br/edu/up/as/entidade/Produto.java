package br.edu.up.as.entidade;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.sun.istack.internal.NotNull;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@NotNull
	private String descricao;
	@NotNull
	private double valor; 
	
	@Transient
	private String error;
	
	/**
	 * Constructor
	 * @desc seta uma mensagem de erro default
	 */
	public Produto() {
		this.setError("Erro0 - Houve um erro não previsto.");
	}
	
	// getters
	public Integer getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}
	public double getValor() {
		return valor;
	}
	public String getError() {
		return error;
	}
	
	// setters
	public void setId(Integer id) {
		this.id = id;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setValor(double valor) {
		this.valor = valor;
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
		if(this.getDescricao() == null || this.getDescricao().equals("")) {
			this.setError("ERR01 - A descrição precisa ser preenchida");
			return false;
		}else if(this.getValor() <= 0) {
			this.setError("ERR02 - O valor precisa ser preenchido e maior que zero.");
			return false;
		}
		
		return true;
	}
}
