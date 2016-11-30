package com.persistence;

public class Dados {

	int chavePrimaria;
	String nome;
	String telefone;

	public Dados(int chavePrimaria, String nome, String telefone) {
		this.chavePrimaria = chavePrimaria;
		this.nome = nome;
		this.telefone = telefone;
	}

	public Dados(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}

	public int getChavePrimaria() {
		return chavePrimaria;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

}
