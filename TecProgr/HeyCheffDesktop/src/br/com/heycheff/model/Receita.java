package br.com.heycheff.model;

import java.util.ArrayList;
import java.util.List;

public class Receita {
    private String descricao;
    List<Step> listaStep = new ArrayList<>();

    public void adicionaStep(Step newStep) {
        newStep.setNumero(listaStep.size() + 1);
        listaStep.add(newStep);
    }

    public void removeStep(int indice) {
        listaStep.remove(indice);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void listarStep() {
        System.out.println("Receitas Cadastradas: ");
        for (Step step : listaStep) {
            System.out.println(step.getDescricao());
            System.out.println(step.getNumero());
        }
    }

    public List<Step> getListaStep() {
        return listaStep;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
