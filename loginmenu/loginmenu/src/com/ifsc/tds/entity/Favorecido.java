package com.ifsc.tds.entity;

public class Favorecido {

	private Integer id;
	private String nome;
	private String numero_titular;
	private String valor;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNumeroTitular() {
		return numero_titular;
	}

	public void setNumeroTitular(String numero_titular) {
		this.numero_titular = numero_titular;
	}
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}



}
