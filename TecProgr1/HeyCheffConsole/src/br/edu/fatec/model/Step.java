package br.edu.fatec.model;

public class Step {
	private int id;
	private String descricao;
	public String[] ingredientes;
	private int totalSteps = 0;

	public Step(String[] ingredientes, String descricao) {
		this.id = this.totalSteps;
		this.totalSteps++;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
