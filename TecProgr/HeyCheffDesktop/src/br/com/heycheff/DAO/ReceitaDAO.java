package br.com.heycheff.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.heycheff.model.Receita;

public class ReceitaDAO {
	List<Receita> lista = new ArrayList<Receita>();

	public void adiciona(Receita receita) {
		lista.add(receita);

	}

	public void listar() {
		System.out.println("Receitas Cadastradas: ");
		for (Receita receita : lista) {
			System.out.println(receita.getDescricao());
			receita.listarStep();
		}
		
	}
	
	public List<Receita> getLista() {
		return lista;
	}

}
