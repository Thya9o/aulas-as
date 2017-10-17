package br.edu.up.as.entidade;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nome;
	
	@Transient
	private String error;
	
	/**
	 * Constructor
	 * @desc seta uma mensagem de erro default
	 */
	public Cliente() {
		this.setError("Erro0 - Houve um erro não previsto.");
	}
	
	// getters
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getError() {
		return error;
	}
	
	// setters
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
		if(this.getNome() == null || this.getNome().equals("")) {
			this.setError("ERR01 - O nome precisa ser preenchido.");
			return false;
		}
		return true;
	}
}
