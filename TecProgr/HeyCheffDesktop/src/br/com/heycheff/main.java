package br.com.heycheff;


import br.com.heycheff.DAO.ReceitaDAO;
import br.com.heycheff.view.Home;

public class main {

	public static void main(String[] args) {
		Home home = new Home(new ReceitaDAO());
		home.montaJanela();

	}

}
