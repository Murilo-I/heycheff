package br.edu.fatec.model;

public class Receita {
	private int id;
	private String descricao;
	public Step[] steps;
	private int totalReceitas = 0;

	public Receita(int qtSteps) {
		this.id = this.totalReceitas;
		this.totalReceitas++;
		if (qtSteps > 0) this.steps = new Step[qtSteps];
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void adicionarStep(Step step) {
		this.steps[this.totalReceitas]
	}
}
