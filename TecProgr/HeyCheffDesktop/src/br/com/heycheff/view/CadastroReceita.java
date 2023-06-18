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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.heycheff.DAO.ReceitaDAO;
import br.com.heycheff.model.Receita;

public class CadastroReceita {
	private JLabel lbTitulo, lbStep, lbQtdStep;
	private JTextField t1, txtTitulo;
	private JList listStep;
	private JButton bindice, btnDelStep, btnAddStep, btnReload, btnSalvar;
	private DefaultListModel listModel;
	private JFrame janela;
	private int larJanela, altJanela;

	private Receita receita = new Receita();
	private ReceitaDAO recDAO = new ReceitaDAO();

	public void montaJanela() {
		larJanela = 1000;
		altJanela = 500;
		janela = new JFrame();
		janela.setTitle("Cadastro de Receita");
		janela.setBounds(20, 20, larJanela, altJanela);
		janela.getContentPane().setBackground(new Color(150, 150, 150));

		janela.getContentPane().setLayout(null);

		janela.getContentPane().add(getLbTitulo());
		janela.getContentPane().add(getTxtTitulo());

		janela.getContentPane().add(getLbStep());
		janela.getContentPane().add(getLbQtdStep());

		janela.getContentPane().add(getPainel());

		janela.getContentPane().add(getBtnReload());
		janela.getContentPane().add(getBtnDelStep());
		janela.getContentPane().add(getBtnAddStep());
		janela.getContentPane().add(getBtnsalvar());
		
		// janela.getContentPane().add(getBindice());

		janela.setUndecorated(true);
		janela.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		// janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);

	}

	public JScrollPane getPainel() {
		listModel = new DefaultListModel();

		listStep = new JList(listModel);

		ListSelectionListener listener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listStep.getSelectedValue() != null)
					txtTitulo.setText("Selecionado: " + listStep.getSelectedValue());
			}
		};

		listStep.addListSelectionListener(listener);

		JScrollPane painel = new JScrollPane(listStep);
		painel.setBounds(10, 100, larJanela - 20, 150);
		return painel;
	}

	private JButton getBtnDelStep() {
		btnDelStep = new JButton("Remove Step");
		btnDelStep.setBounds(5, 420, 150, 30);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listStep.getSelectedIndex() >= 0) {
					int index = listStep.getSelectedIndex();
					listModel.remove(index);
					receita.removeStep(index);
					lbQtdStep.setText("Quantidade: " + listModel.getSize());
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um item", "AVISO", JOptionPane.ERROR_MESSAGE);
				}
			}
		};

		btnDelStep.addActionListener(listener);
		return btnDelStep;

	}

	public JButton getBindice() {
		bindice = new JButton("Indice selecionado");
		bindice.setBounds(500, 420, 150, 30);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listStep.getSelectedIndex() >= 0) {
					txtTitulo.setText("Indice selecionado: " + listStep.getSelectedIndex());
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um item", "AVISO", JOptionPane.ERROR_MESSAGE);
				}
			}
		};

		bindice.addActionListener(listener);
		return bindice;
	}

	public JButton getBtnReload() {
		btnReload = new JButton("Reload");
		btnReload.setBounds(320, 420, 150, 30);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				receita.getListaStep().forEach(r -> listModel.addElement(r.toString()));
			}
		};

		btnReload.addActionListener(listener);
		return btnReload;
	}

	public void reload() {
		listModel.clear();
		receita.getListaStep().forEach(r -> listModel.addElement(r.toString()));
	}

	public JButton getBtnAddStep() {
		btnAddStep = new JButton("Adicionar Step");
		btnAddStep.setBounds(160, 420, 150, 30);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastroStep(receita).montaJanela();

			}
		};

		btnAddStep.addActionListener(listener);
		return btnAddStep;
	}
	
	public JButton getBtnsalvar() {
		btnSalvar = new JButton("Salvar Receita");
		btnSalvar.setBounds(larJanela - 170, 420, 150, 30);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recDAO.adiciona(receita);
				Home home = new Home(recDAO);
				home.montaJanela();
				janela.dispose();

			}
		};

		btnSalvar.addActionListener(listener);
		return btnSalvar;
	}

	public JLabel getLbTitulo() {
		lbTitulo = new JLabel("Titulo : ");
		lbTitulo.setForeground(Color.black);
		lbTitulo.setBounds(5, 10, 150, 30);
		lbTitulo.setForeground(Color.black);
		return lbTitulo;
	}

	public JLabel getLbStep() {
		lbStep = new JLabel("Step's : ");
		lbStep.setForeground(Color.black);
		lbStep.setBounds(5, 70, 150, 30);
		lbStep.setForeground(Color.black);
		return lbStep;
	}

	public JLabel getLbQtdStep() {
		lbQtdStep = new JLabel("Quantidade: 0");
		lbQtdStep.setForeground(Color.black);
		lbQtdStep.setBounds(larJanela - 100, 70, 150, 30);
		lbQtdStep.setForeground(Color.black);
		return lbQtdStep;
	}

	public JTextField getTxtTitulo() {
		txtTitulo = new JTextField("", JTextField.RIGHT);
		txtTitulo.setBounds(150, 10, larJanela - 250, 30);
		return txtTitulo;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

}
