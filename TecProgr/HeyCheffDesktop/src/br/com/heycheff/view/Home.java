package br.com.heycheff.view;

import br.com.heycheff.DAO.ReceitaDAO;
import br.com.heycheff.model.Receita;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class Home {
	private JLabel lbQtdRec;
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

		janela.getContentPane().add(getLogo());
		janela.getContentPane().add(getTitulo());
		janela.getContentPane().add(getLbReceita());
		janela.getContentPane().add(getLbQtdRec());
		janela.getContentPane().add(getPainel());

		janela.getContentPane().add(getBtnDel());
		janela.getContentPane().add(getBtnAdd());
		janela.getContentPane().add(getBtnUpdate());

		janela.setUndecorated(true);

		recDAO.getLista().forEach(r -> listModel.addElement(r));
		lbQtdRec.setText("Quantidade: " + listModel.getSize());

		janela.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);

	}

	private JScrollPane getPainel() {
		listModel = new DefaultListModel<>();

		listReceita = new JList<>(listModel);

		JScrollPane painel = new JScrollPane(listReceita);
		painel.setBounds(10, 100, larJanela - 30, altJanela - 210);
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
				lbQtdRec.setText("Quantidade: " + listModel.getSize());
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

	private JLabel getLbReceita() {
		JLabel lbReceita = new JLabel("Receitas: ");
		lbReceita.setForeground(Color.black);
		lbReceita.setBounds(10, 75, 150, 30);
		lbReceita.setForeground(Color.black);
		return lbReceita;
	}

	private JLabel getLbQtdRec() {
		lbQtdRec = new JLabel("Quantidade: 0");
		lbQtdRec.setForeground(Color.black);
		lbQtdRec.setBounds(larJanela - 105, 75, 150, 30);
		lbQtdRec.setForeground(Color.black);
		return lbQtdRec;
	}

	private JLabel getTitulo() {
		JLabel titulo = new JLabel("HEY CHEFF", JLabel.CENTER);
		titulo.setBounds(0, 10, larJanela, 60);
		titulo.setFont(new Font("Serif", Font.PLAIN, 60));
		return titulo;
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
