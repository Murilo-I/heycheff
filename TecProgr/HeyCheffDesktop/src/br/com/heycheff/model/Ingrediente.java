package br.com.heycheff.model;

public class Ingrediente {
    private final String descricao;
    private final int quantidade;

    public Ingrediente(String descricao, int quantidade) {
        super();
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return quantidade + " - " + descricao;
    }
}
