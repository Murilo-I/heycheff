package br.com.heycheff.view;

import br.com.heycheff.model.Ingrediente;
import br.com.heycheff.model.Receita;
import br.com.heycheff.model.Step;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CadastroStep {
	private JLabel lbQtdIngr;
	private JTextField txtDesc;
	private JList<Ingrediente> listIngrediente;
	private DefaultListModel<Ingrediente> listModel;
	private JFrame janela;
	private int larJanela;
	private final Step step = new Step();
	private final Receita receita;

	public CadastroStep(Receita receita) {
		this.receita = receita;
	}

	public void montaJanela() {
		larJanela = 1000;
		int altJanela = 500;
		janela = new JFrame();
		janela.setTitle("Adicionar Step");
		janela.setBounds(20, 20, larJanela, altJanela);
		janela.getContentPane().setBackground(new Color(255, 209, 134));

		janela.getContentPane().setLayout(null);

		janela.getContentPane().add(getLbDesc());
		janela.getContentPane().add(getTxtDesc());

		janela.getContentPane().add(getLbIngre());
		janela.getContentPane().add(getLbQtdIngr());

		janela.getContentPane().add(getPainel());

		janela.getContentPane().add(getBtnSalvar());
		janela.getContentPane().add(getBtnDel());
		janela.getContentPane().add(getBtnAdd());

		janela.setUndecorated(true);
		janela.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		janela.setVisible(true);
	}

	private JScrollPane getPainel() {
		listModel = new DefaultListModel<>();

		listIngrediente = new JList<>(listModel);

		JScrollPane painel = new JScrollPane(listIngrediente);
		painel.setBounds(10, 100, larJanela - 30, 150);
		return painel;
	}

	private JButton getBtnDel() {
		JButton btnDel = new JButton("Remover item");
		btnDel.setForeground(Color.WHITE);
		btnDel.setBackground(new Color(244, 70, 70));
		btnDel.setBounds(10, 420, 150, 30);

		ActionListener listener = e -> {
			if (listIngrediente.getSelectedIndex() >= 0) {
				int index = listIngrediente.getSelectedIndex();
				listModel.remove(index);
				lbQtdIngr.setText("Quantidade: " + listModel.getSize());
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um item", "AVISO", JOptionPane.ERROR_MESSAGE);
			}
		};

		btnDel.addActionListener(listener);
		return btnDel;
	}

	private JButton getBtnSalvar() {
		JButton btnSalvar = new JButton("Salvar Step ");
		btnSalvar.setBounds(larJanela - 170, 420, 150, 30);
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setBackground(new Color(244, 70, 70));
		ActionListener listener = e -> {
			if (txtDesc.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos", "AVISO", JOptionPane.ERROR_MESSAGE);
			} else {
				step.setDescricao(txtDesc.getText());
				receita.adicionaStep(step);
				janela.dispose();
			}
		};

		btnSalvar.addActionListener(listener);
		return btnSalvar;
	}

	private JButton getBtnAdd() {
		JButton btnAdd = new JButton("Adicionar Item");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(244, 70, 70));
		btnAdd.setBounds(165, 420, 150, 30);

		ActionListener listener = e -> {
			String ingrediente = JOptionPane.showInputDialog("Digite o Igrendiente e/ou Utensílio:");
			String qtd = JOptionPane.showInputDialog("Digite o Quantidade:");

			if (ingrediente == null || qtd == null || ingrediente.isBlank() || qtd.isBlank()) {
				JOptionPane.showMessageDialog(null, "Não pode ser Vazio", "AVISO", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					Ingrediente ingre = new Ingrediente(ingrediente, Integer.parseInt(qtd));
					step.addIngrediente(ingre);
					listModel.addElement(ingre);
					lbQtdIngr.setText("Quantidade: " + listModel.getSize());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Campo Quantidade deve ser numérico ", "AVISO", JOptionPane.ERROR_MESSAGE);
				}
			}
		};

		btnAdd.addActionListener(listener);
		return btnAdd;
	}

	private JLabel getLbDesc() {
		JLabel lbDesc = new JLabel("Descricão: ");
		lbDesc.setForeground(Color.black);
		lbDesc.setBounds(10, 10, 150, 30);
		lbDesc.setForeground(Color.black);
		return lbDesc;
	}

	private JLabel getLbIngre() {
		JLabel lbIngre = new JLabel("Ingredientes / Utensílios: ");
		lbIngre.setForeground(Color.black);
		lbIngre.setBounds(10, 70, 150, 30);
		lbIngre.setForeground(Color.black);
		return lbIngre;
	}

	private JLabel getLbQtdIngr() {
		lbQtdIngr = new JLabel("Quantidade: 0");
		lbQtdIngr.setForeground(Color.black);
		lbQtdIngr.setBounds(larJanela - 100, 70, 150, 30);
		lbQtdIngr.setForeground(Color.black);
		return lbQtdIngr;
	}

	private JTextField getTxtDesc() {
		txtDesc = new JTextField("", JTextField.RIGHT);
		txtDesc.setBounds(150, 10, larJanela - 250, 30);
		return txtDesc;
	}
}
