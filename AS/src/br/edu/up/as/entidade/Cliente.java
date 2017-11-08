package br.edu.up.as.entidade;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

@Entity
@XmlRootElement
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nome;
	private String cpf;
	
	@Transient
	private String error;
		
	// getters
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
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
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
		
		if(this.getNome() == null || this.getNome().equals("")) {
			this.setError("ERR01 - O nome precisa ser preenchido.");
			return false;
		}
		if(this.getCpf() == null || this.getCpf().equals("")) {
			this.setError("ERR02 - O cpf precisa ser preenchido.");
			return false;
		}
		return true;
	}
}
