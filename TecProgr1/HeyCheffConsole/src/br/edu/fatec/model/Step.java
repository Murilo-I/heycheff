package br.edu.fatec.model;

public class Step {
	private String descricao;
	public String[] ingredientes;

	public Step(String[] ingredientes, String descricao) {
		this.ingredientes = ingredientes;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String[] getIngredientes() {
		return ingredientes;
	}

}
