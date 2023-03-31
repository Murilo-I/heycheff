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
	
}
