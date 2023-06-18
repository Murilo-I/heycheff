package br.com.heycheff.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.heycheff.DAO.ReceitaDAO;

public class Home {
	private JLabel lbIngre, lbQtdIngr;
	private JList listReceita;
	private JButton btnDel, btnAdd, btnReload;
	private DefaultListModel listModel;
	private JFrame janela;
	private int larJanela, altJanela;
	private ReceitaDAO recDAO;

	public Home(ReceitaDAO recDAO) {
		this.recDAO = recDAO;
	}

	public void montaJanela() {
		larJanela = 1000;
		altJanela = 500;
		janela = new JFrame();
		janela.setTitle("Hey Cheff - Desktop");
		janela.setBounds(20, 20, larJanela, altJanela);
		janela.getContentPane().setBackground(new Color(150, 150, 150));

		janela.getContentPane().setLayout(null);

		janela.getContentPane().add(getLbIngre());
		janela.getContentPane().add(getLbQtdIngr());

		janela.getContentPane().add(getPainel());

		// janela.getContentPane().add(getLabel1());

		janela.getContentPane().add(getBtnDel());
		janela.getContentPane().add(getBtnAdd());

		janela.setUndecorated(true);
		
		listModel.clear();
		listModel.addElement("teste");
		recDAO.getLista().forEach(r -> listModel.addElement(r.getDescricao()));
		
		janela.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		// janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);

	}

	private JScrollPane getPainel() {
		listModel = new DefaultListModel();

		listReceita = new JList(listModel);

		ListSelectionListener listener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				listModel.clear();
				listModel.addElement("teste-esya");
				recDAO.getLista().forEach(r -> listModel.addElement(r.getDescricao()));

			}
		};

		listReceita.addListSelectionListener(listener);

		JScrollPane painel = new JScrollPane(listReceita);
		painel.setBounds(10, 35, larJanela - 25, altJanela - 150);
		return painel;
	}

	private JButton getBtnDel() {
		btnDel = new JButton("Remove item");
		btnDel.setBounds(5, 420, 150, 30);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listReceita.getSelectedIndex() >= 0) {
					int index = listReceita.getSelectedIndex();
					// label1.setText("Removido: " + listReceita.getSelectedValue());
					listModel.remove(index);
					lbQtdIngr.setText("Quantidade: " + listModel.getSize());
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um item", "AVISO", JOptionPane.ERROR_MESSAGE);
				}
			}
		};

		btnDel.addActionListener(listener);
		return btnDel;

	}

	private JButton getBtnAdd() {
		btnAdd = new JButton("Cadastrar Receita");
		btnAdd.setBounds(160, 420, 150, 30);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroReceita cadReceita = null;
				cadReceita = new CadastroReceita();
				cadReceita.montaJanela();
				janela.dispose();

			}
		};

		btnAdd.addActionListener(listener);
		return btnAdd;
	}

	private JLabel getLbIngre() {
		lbIngre = new JLabel("Receitas: ");
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
