package br.edu.up.as.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
