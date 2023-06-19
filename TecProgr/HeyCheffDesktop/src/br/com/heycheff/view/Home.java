package br.com.heycheff.view;

import br.com.heycheff.DAO.ReceitaDAO;
import br.com.heycheff.model.Receita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Home {
	private JLabel lbQtdIngr;
	private JList<Receita> listReceita;
	private DefaultListModel<Receita> listModel;
	private JFrame janela;
	private int larJanela, altJanela;
	private final ReceitaDAO recDAO;

	public Home(ReceitaDAO recDAO) {
		this.recDAO = recDAO;
	}

	public void montaJanela() {
		larJanela = 1000;
		altJanela = 500;
		janela = new JFrame();
		janela.setTitle("Hey Cheff - Desktop");
		janela.setBounds(20, 20, larJanela, altJanela);
		janela.getContentPane().setBackground(new Color(255, 209, 134));

		janela.getContentPane().setLayout(null);

		janela.getContentPane().add(getLbIngre());
		janela.getContentPane().add(getLbQtdIngr());

		janela.getContentPane().add(getPainel());

		janela.getContentPane().add(getBtnDel());
		janela.getContentPane().add(getBtnAdd());
		janela.getContentPane().add(getBtnUpdate());

		janela.setUndecorated(true);

		recDAO.getLista().forEach(r -> listModel.addElement(r));
		lbQtdIngr.setText("Quantidade: " + listModel.getSize());

		janela.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);

	}

	private JScrollPane getPainel() {
		listModel = new DefaultListModel<>();

		listReceita = new JList<>(listModel);

		JScrollPane painel = new JScrollPane(listReceita);
		painel.setBounds(10, 35, larJanela - 25, altJanela - 150);
		return painel;
	}

	private JButton getBtnDel() {
		JButton btnDel = new JButton("Remove Receita");
		btnDel.setBounds(10, 420, 150, 30);
		btnDel.setForeground(Color.WHITE);
		btnDel.setBackground(new Color(244, 70, 70));

		ActionListener listener = e -> {
			if (listReceita.getSelectedIndex() >= 0) {
				int index = listReceita.getSelectedIndex();
				listModel.remove(index);
				lbQtdIngr.setText("Quantidade: " + listModel.getSize());
				recDAO.remove(index);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma Receita", "AVISO", JOptionPane.ERROR_MESSAGE);
			}
		};

		btnDel.addActionListener(listener);
		return btnDel;

	}

	private JButton getBtnAdd() {
		JButton btnAdd = new JButton("Cadastrar Receita");
		btnAdd.setBounds(165, 420, 150, 30);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(244, 70, 70));

		ActionListener listener = e -> {
			CadastroReceita cadReceita = new CadastroReceita(recDAO);
			cadReceita.montaJanela();
			janela.dispose();
		};

		btnAdd.addActionListener(listener);
		return btnAdd;
	}

	private JButton getBtnUpdate() {
		JButton btnReload = new JButton("Editar Receita");
		btnReload.setBackground(new Color(244, 70, 70));
		btnReload.setForeground(Color.WHITE);
		btnReload.setBounds(320, 420, 150, 30);

		ActionListener listener = e -> {
			if (listReceita.getSelectedIndex() >= 0) {
				CadastroReceita cadReceita = new CadastroReceita(listModel.get(listReceita.getSelectedIndex()), recDAO);
				cadReceita.montaJanela();
				janela.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma Receita", "AVISO", JOptionPane.ERROR_MESSAGE);
			}
		};

		btnReload.addActionListener(listener);
		return btnReload;
	}

	private JLabel getLbIngre() {
		JLabel lbIngre = new JLabel("Receitas: ");
		lbIngre.setForeground(Color.black);
		lbIngre.setBounds(10, 10, 150, 30);
		lbIngre.setForeground(Color.black);
		return lbIngre;
	}

	private JLabel getLbQtdIngr() {
		lbQtdIngr = new JLabel("Quantidade: 0");
		lbQtdIngr.setForeground(Color.black);
		lbQtdIngr.setBounds(larJanela - 100, 10, 150, 30);
		lbQtdIngr.setForeground(Color.black);
		return lbQtdIngr;
	}

}
