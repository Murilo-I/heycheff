package br.edu.fatec.model;

public class Step {
	private String descricao;
	public String[] ingredientes;

	public Step(String[] ingredientes, String descricao) {
		this.ingredientes = ingredientes;
		this.descricao = descricao;
	}
	
	public Step(String descricao) {
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
