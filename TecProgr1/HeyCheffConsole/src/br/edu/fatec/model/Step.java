package br.edu.fatec.model;

public class Step {
	private int id;
	private String descricao;
	public String[] ingredientes;
	private int totalSteps = 0;

	public Step(int qtIngredientes) {
		this.id = this.totalSteps;
		this.totalSteps++;
		if (qtIngredientes > 0) this.ingredientes = new String[qtIngredientes];
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
