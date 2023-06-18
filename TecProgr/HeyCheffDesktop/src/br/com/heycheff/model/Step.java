package br.com.heycheff.model;

import java.util.ArrayList;
import java.util.List;

public class Step {
    private String descricao;
    private int numero;

    List<Ingrediente> lista = new ArrayList<>();

    public void addIngrediente(Ingrediente newIngre) {
        lista.add(newIngre);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Ingrediente> getLista() {
        return lista;
    }

    @Override
    public String toString() {
        return numero + " - " + descricao;
    }
}
