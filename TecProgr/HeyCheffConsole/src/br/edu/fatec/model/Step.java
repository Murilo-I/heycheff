package br.edu.fatec.model;

import java.util.ArrayList;
import java.util.List;

public class Step {
	private String descricao;
	public String[] ingredientes;
	private List<String> listIngredientes;

	public Step(String[] ingredientes, String descricao) {
		this.ingredientes = ingredientes;
		this.descricao = descricao;
		this.listIngredientes = new ArrayList<String>();
	}
	
	public Step(String descricao) {
		this.descricao = descricao;
	}
	
	public Step(List<String> ingredientes, String descricao) {
		this.listIngredientes = ingredientes;
		this.descricao = descricao;
		this.listIngredientes = new ArrayList<String>();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<String> getListIngrediente() {
		return this.listIngredientes;
	}
	
	public void setListIngrediente(List<String> listIngredientes) {
		 this.listIngredientes = listIngredientes;
	}
	
	public void addListIngrediente(String ingrediente) {
		this.listIngredientes.add(ingrediente);
	}

	public String[] getIngredientes() {
		return ingredientes;
	}

	public void addIngrediente(String ingrediente) {
		String[] novoIngrediente;
		if(this.ingredientes == null) {
			novoIngrediente = new String[1];
			novoIngrediente[0] = ingrediente;
		}else {
			novoIngrediente = new String[this.ingredientes.length + 1];
			for (int i = 0; i < this.ingredientes.length; i++) {
				novoIngrediente[i] = this.ingredientes[i];
			}
			novoIngrediente[novoIngrediente.length-1] = ingrediente;
		}
		this.ingredientes = novoIngrediente;
	}
}
