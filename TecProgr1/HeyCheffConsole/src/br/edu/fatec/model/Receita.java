package br.edu.fatec.model;

public class Receita {
	private int id;
	private String descricao;
	private Step[] steps;
	private int totalReceitas = 0;

	public Receita(String descricao) {
		this.id = this.totalReceitas;
		this.totalReceitas++;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public int getId() {
		return id;
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
