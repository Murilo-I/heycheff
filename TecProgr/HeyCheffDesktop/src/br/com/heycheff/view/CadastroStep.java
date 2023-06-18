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
import javax.swing.JTextField;

import br.com.heycheff.model.Ingrediente;
import br.com.heycheff.model.Receita;
import br.com.heycheff.model.Step;


public class CadastroStep {
	private JLabel lbDesc, lbIngre, lbQtdIngr;
	private JTextField txtDesc;
	private JList listStep;
	private JButton bquant, bindice, btnDel, btnAdd, btnSalvar;
	private DefaultListModel listModel;
	private JFrame janela;
	private int larJanela, altJanela;
	private Step step = new Step();
	private Receita receita;


	public CadastroStep(Receita receita) {
		this.receita = receita;
		
	}

	public void montaJanela() {
		larJanela = 1000;
		altJanela = 500;
		janela = new JFrame();
		janela.setTitle("Adicionar Step");
		janela.setBounds(20, 20, larJanela, altJanela);
		janela.getContentPane().setBackground(new Color(150, 150, 150));

		janela.getContentPane().setLayout(null);

		janela.getContentPane().add(getLbDesc());
		janela.getContentPane().add(getTxtDesc());

		janela.getContentPane().add(getLbIngre());
		janela.getContentPane().add(getLbQtdIngr());

		janela.getContentPane().add(getPainel());

		// janela.getContentPane().add(getLabel1());

		janela.getContentPane().add(getBtnSalvar());
		janela.getContentPane().add(getBtnDel());
		janela.getContentPane().add(getBtnAdd());

		janela.setUndecorated(true);
		janela.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		// janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);

	}

	private JScrollPane getPainel() {
		listModel = new DefaultListModel();

		listStep = new JList(listModel);

//		ListSelectionListener listener = new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent e) {
//				if (listStep.getSelectedValue() != null)
//					label1.setText("Selecionado: " + listStep.getSelectedValue());
//			}
//		};

//		listStep.addListSelectionListener(listener);

		JScrollPane painel = new JScrollPane(listStep);
		painel.setBounds(10, 100, larJanela - 20, 150);
		return painel;
	}

	private JButton getBtnDel() {
		btnDel = new JButton("Remove item");
		btnDel.setBounds(5, 420, 150, 30);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listStep.getSelectedIndex() >= 0) {
					int index = listStep.getSelectedIndex();
					// label1.setText("Removido: " + listStep.getSelectedValue());
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

	private JButton getBtnSalvar() {
		btnSalvar = new JButton("Salvar Step ");
		btnSalvar.setBounds(320, 420, 150, 30);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				step.setDescricao(txtDesc.getText());
				receita.adicionaStep(step);
				janela.dispose();
			}
		};

		btnSalvar.addActionListener(listener);
		return btnSalvar;
	}

	private JButton getBtnAdd() {
		btnAdd = new JButton("Add item");
		btnAdd.setBounds(160, 420, 150, 30);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ingrediente = "";
				String qtd = "";
				ingrediente = JOptionPane.showInputDialog("Digite o Igrendiente:");
				qtd = JOptionPane.showInputDialog("Digite o Quantidade:");

				if (!ingrediente.isEmpty() && !qtd.isEmpty()) {
					Ingrediente ingre = new Ingrediente(ingrediente, Integer.parseInt(qtd));
					step.adicionaNaLista(ingre);
					listModel.addElement(ingre.getQuantidade() + " - " + ingre.getDescricao());
					lbQtdIngr.setText("Quantidade: " + listModel.getSize());
				} else {
					JOptionPane.showMessageDialog(null, "Não pode ser Vazio", "AVISO", JOptionPane.ERROR_MESSAGE);
				}

			}
		};

		btnAdd.addActionListener(listener);
		return btnAdd;
	}

	private JLabel getLbDesc() {
		lbDesc = new JLabel("Descricão : ");
		lbDesc.setForeground(Color.black);
		lbDesc.setBounds(5, 10, 150, 30);
		lbDesc.setForeground(Color.black);
		return lbDesc;
	}

	private JLabel getLbIngre() {
		lbIngre = new JLabel("Ingrediente: ");
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

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

}
