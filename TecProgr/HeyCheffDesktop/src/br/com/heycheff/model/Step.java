package br.com.heycheff.model;

import java.util.ArrayList;
import java.util.List;

public class Step {
	private String descricao;
	private int numero;

	List<Ingrediente> lista = new ArrayList<Ingrediente>();

	public void adicionaNaLista(Ingrediente newIngre) {
		lista.add(newIngre);

	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void adicionar(Ingrediente ingrediente) {
		lista.add(ingrediente);

	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return numero + " - "+ descricao;
	}
	

}
