package br.edu.fatec.model;

public class Receita {
	private String descricao;
	private Step[] steps;

	public Receita(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Step[] getSteps() {
		return steps;
	}

	public void setSteps(Step[] steps) {
		this.steps = steps;
	}

	public void addStep(Step step) {
		Step[] novoStep;
		if(this.steps == null) {
			novoStep = new Step[1];
			novoStep[0] = step;
		}else {
			novoStep = new Step[this.steps.length + 1];
			for (int i = 0; i < this.steps.length; i++) {
				novoStep[i] = this.steps[i];
			}
			novoStep[novoStep.length-1] = step;
		}
		this.steps = novoStep;
	}
}
