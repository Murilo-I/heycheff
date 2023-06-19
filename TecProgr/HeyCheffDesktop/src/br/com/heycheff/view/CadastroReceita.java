package br.com.heycheff.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;

import br.com.heycheff.DAO.ReceitaDAO;
import br.com.heycheff.model.Ingrediente;
import br.com.heycheff.model.Receita;
import br.com.heycheff.model.Step;

public class CadastroReceita {
	private JLabel lbQtdStep;
	private JTextField txtTitulo;
	private JList<Step> listStep;
	private DefaultListModel<Step> listModelStep;
	private DefaultListModel<Ingrediente> listModelIngre;
	private JFrame janela;
	private int larJanela;
	private ListSelectionListener listenerReloadStep;

	private Receita receita = new Receita();
	private final ReceitaDAO recDAO;

	public CadastroReceita(ReceitaDAO recDAO) {
		this.recDAO = recDAO;
	}

	public CadastroReceita(Receita receita, ReceitaDAO recDAO) {
		this.receita = receita;
		this.recDAO = recDAO;
	}

	public void montaJanela() {
		larJanela = 1000;
		int altJanela = 500;
		janela = new JFrame();
		janela.setTitle("Cadastro de Receita");
		janela.setBounds(20, 20, larJanela, altJanela);
		janela.getContentPane().setBackground(new Color(255, 209, 134));

		janela.getContentPane().setLayout(null);

		janela.getContentPane().add(getLogo());
		janela.getContentPane().add(getLbTitulo());
		janela.getContentPane().add(getTxtTitulo());

		janela.getContentPane().add(getLbStep());
		janela.getContentPane().add(getLbQtdStep());
		janela.getContentPane().add(getLbIngre());

		janela.getContentPane().add(getPainelStep());
		janela.getContentPane().add(getPainelIngre());

		janela.getContentPane().add(getBtnReload());
		janela.getContentPane().add(getBtnDelStep());
		janela.getContentPane().add(getBtnAddStep());
		janela.getContentPane().add(getBtnsalvar());

		receita.getListaStep().forEach(step -> listModelStep.addElement(step));
		lbQtdStep.setText("Quantidade: " + listModelStep.getSize());

		janela.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Home home = new Home(recDAO);
				home.montaJanela();
			}			
		});
		
		janela.setUndecorated(true);
		janela.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		janela.setVisible(true);
	}

	private JScrollPane getPainelStep() {
		listModelStep = new DefaultListModel<>();
		listStep = new JList<>(listModelStep);

		listenerReloadStep = e -> {
			Step step = listModelStep.get(listStep.getSelectedIndex());
			listModelIngre.clear();
			step.getLista().forEach(i -> listModelIngre.addElement(i));
		};

		listStep.addListSelectionListener(listenerReloadStep);
		JScrollPane painel = new JScrollPane(listStep);
		painel.setBounds(10, 90, larJanela - 30, 140);
		return painel;
	}

	private JScrollPane getPainelIngre() {
		listModelIngre = new DefaultListModel<>();
		JList<Ingrediente> listIngre = new JList<>(listModelIngre);
		JScrollPane painel = new JScrollPane(listIngre);
		painel.setBounds(10, 270, larJanela - 30, 140);
		return painel;
	}

	private JButton getBtnDelStep() {
		JButton btnDelStep = new JButton("Remove Step");
		btnDelStep.setForeground(Color.WHITE);
		btnDelStep.setBackground(new Color(244, 70, 70));
		btnDelStep.setBounds(10, 420, 150, 30);

		ActionListener listener = e -> {
			if (listStep.getSelectedIndex() >= 0) {
				int index = listStep.getSelectedIndex();
				listStep.removeListSelectionListener(this.listenerReloadStep);
				listModelStep.remove(index);
				receita.removeStep(index);
				lbQtdStep.setText("Quantidade: " + listModelStep.getSize());
				listModelIngre.clear();
				listStep.addListSelectionListener(this.listenerReloadStep);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um item", "AVISO", JOptionPane.ERROR_MESSAGE);
			}
		};

		btnDelStep.addActionListener(listener);
		return btnDelStep;
	}

	private JButton getBtnReload() {
		JButton btnReload = new JButton("Atualizar Lista");
		btnReload.setForeground(Color.WHITE);
		btnReload.setBackground(new Color(244, 70, 70));
		btnReload.setBounds(320, 420, 150, 30);

		ActionListener listener = e -> {
			listStep.removeListSelectionListener(this.listenerReloadStep);
			listModelStep.clear();
			receita.getListaStep().forEach(step -> listModelStep.addElement(step));
			lbQtdStep.setText("Quantidade: " + listModelStep.getSize());
			listStep.addListSelectionListener(this.listenerReloadStep);
		};

		btnReload.addActionListener(listener);
		return btnReload;
	}

	private JButton getBtnAddStep() {
		JButton btnAddStep = new JButton("Adicionar Step");
		btnAddStep.setForeground(Color.WHITE);
		btnAddStep.setBackground(new Color(244, 70, 70));
		btnAddStep.setBounds(165, 420, 150, 30);

		ActionListener listener = e -> new CadastroStep(receita).montaJanela();

		btnAddStep.addActionListener(listener);
		return btnAddStep;
	}

	private JButton getBtnsalvar() {
		JButton btnSalvar = new JButton("Salvar Receita");
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setBackground(new Color(244, 70, 70));
		btnSalvar.setBounds(larJanela - 170, 420, 150, 30);

		ActionListener listener = e -> {
			if (receita.getListaStep().isEmpty() || txtTitulo.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos", "AVISO", JOptionPane.ERROR_MESSAGE);
			} else {
				receita.setDescricao(txtTitulo.getText());
				recDAO.remove(receita);
				recDAO.adiciona(receita);
				Home home = new Home(recDAO);
				home.montaJanela();
				janela.dispose();
			}
		};

		btnSalvar.addActionListener(listener);
		return btnSalvar;
	}

	private JLabel getLbTitulo() {
		JLabel lbTitulo = new JLabel("Titulo: ");
		lbTitulo.setForeground(Color.black);
		lbTitulo.setBounds(10, 10, 150, 30);
		lbTitulo.setForeground(Color.black);
		return lbTitulo;
	}

	private JLabel getLbStep() {
		JLabel lbStep = new JLabel("Step's: ");
		lbStep.setForeground(Color.black);
		lbStep.setBounds(10, 60, 150, 30);
		lbStep.setForeground(Color.black);
		return lbStep;
	}
	
	private JLabel getLbIngre() {
		JLabel lbIngre = new JLabel("Ingredientes / Utensílios: ");
		lbIngre.setForeground(Color.black);
		lbIngre.setBounds(10, 240, 150, 30);
		lbIngre.setForeground(Color.black);
		return lbIngre;
	}

	private JLabel getLbQtdStep() {
		lbQtdStep = new JLabel("Quantidade: 0");
		lbQtdStep.setForeground(Color.black);
		lbQtdStep.setBounds(larJanela - 105, 60, 150, 30);
		lbQtdStep.setForeground(Color.black);
		return lbQtdStep;
	}

	private JTextField getTxtTitulo() {
		txtTitulo = new JTextField(receita.getDescricao() == null ? "" : receita.getDescricao(), JTextField.RIGHT);
		txtTitulo.setBounds(150, 10, larJanela - 250, 30);
		return txtTitulo;
	}

	private JLabel getLogo() {
		try {
			InputStream stream = getClass().getResourceAsStream("/assets/hey_cheff_black.png");
			Image image = ImageIO.read(stream);
			ImageIcon icon = new ImageIcon(image);
			JLabel logo = new JLabel(icon);
			logo.setBounds(larJanela - 62, 10, 42, 56);
			return logo;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
