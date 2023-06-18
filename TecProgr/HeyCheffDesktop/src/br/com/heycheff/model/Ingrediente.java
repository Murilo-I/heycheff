package br.com.heycheff.model;

public class Ingrediente {
	private String descricao;
	private int quantidade;
	
	
	public Ingrediente(String descricao, int quantidade) {
		super();
		this.descricao = descricao;
		this.quantidade = quantidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
