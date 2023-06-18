package br.com.heycheff.view;

import br.com.heycheff.DAO.ReceitaDAO;
import br.com.heycheff.model.Ingrediente;
import br.com.heycheff.model.Receita;
import br.com.heycheff.model.Step;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class CadastroReceita {
    private JLabel lbQtdStep;
    private JTextField txtTitulo;
    private JList<Step> listStep;
    private DefaultListModel<Step> listModelStep;
    private DefaultListModel<Ingrediente> listModelIngre;
    private JFrame janela;
    private int larJanela;

    private Receita receita = new Receita();
    private final ReceitaDAO recDAO = new ReceitaDAO();

    public CadastroReceita() {
    }

    public CadastroReceita(Receita receita) {
        this.receita = receita;
    }

    public void montaJanela() {
        larJanela = 1000;
        int altJanela = 500;
        janela = new JFrame();
        janela.setTitle("Cadastro de Receita");
        janela.setBounds(20, 20, larJanela, altJanela);
        janela.getContentPane().setBackground(new Color(150, 150, 150));

        janela.getContentPane().setLayout(null);

        janela.getContentPane().add(getLbTitulo());
        janela.getContentPane().add(getTxtTitulo());

        janela.getContentPane().add(getLbStep());
        janela.getContentPane().add(getLbQtdStep());

        janela.getContentPane().add(getPainelStep());
        janela.getContentPane().add(getPainelIngre());

        janela.getContentPane().add(getBtnReload());
        janela.getContentPane().add(getBtnDelStep());
        janela.getContentPane().add(getBtnAddStep());
        janela.getContentPane().add(getBtnsalvar());

        receita.getListaStep().forEach(step -> listModelStep.addElement(step));
        lbQtdStep.setText("Quantidade: " + listModelStep.getSize());

        janela.setUndecorated(true);
        janela.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        janela.setVisible(true);
    }

    private JScrollPane getPainelStep() {
        listModelStep = new DefaultListModel<>();
        listStep = new JList<>(listModelStep);

        ListSelectionListener listener = e -> {
            Step step = listModelStep.get(listStep.getSelectedIndex());
            listModelIngre.clear();
            step.getLista().forEach(i -> listModelIngre.addElement(i));
        };

        listStep.addListSelectionListener(listener);
        JScrollPane painel = new JScrollPane(listStep);
        painel.setBounds(10, 100, larJanela - 30, 150);
        return painel;
    }

    private JScrollPane getPainelIngre() {
        listModelIngre = new DefaultListModel<>();
        JList<Ingrediente> listIngre = new JList<>(listModelIngre);
        JScrollPane painel = new JScrollPane(listIngre);
        painel.setBounds(10, 260, larJanela - 30, 150);
        return painel;
    }

    private JButton getBtnDelStep() {
        JButton btnDelStep = new JButton("Remove Step");
        btnDelStep.setBounds(5, 420, 150, 30);

        ActionListener listener = e -> {
            if (listStep.getSelectedIndex() >= 0) {
                int index = listStep.getSelectedIndex();
                listModelStep.remove(index);
                receita.removeStep(index);
                lbQtdStep.setText("Quantidade: " + listModelStep.getSize());
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um item", "AVISO", JOptionPane.ERROR_MESSAGE);
            }
        };

        btnDelStep.addActionListener(listener);
        return btnDelStep;
    }

    private JButton getBtnReload() {
        JButton btnReload = new JButton("Reload");
        btnReload.setBounds(320, 420, 150, 30);

        ActionListener listener = e -> {
            listModelStep.clear();
            receita.getListaStep().forEach(step -> listModelStep.addElement(step));
            lbQtdStep.setText("Quantidade: " + listModelStep.getSize());
        };

        btnReload.addActionListener(listener);
        return btnReload;
    }

    private JButton getBtnAddStep() {
        JButton btnAddStep = new JButton("Adicionar Step");
        btnAddStep.setBounds(160, 420, 150, 30);

        ActionListener listener = e -> new CadastroStep(receita).montaJanela();

        btnAddStep.addActionListener(listener);
        return btnAddStep;
    }

    private JButton getBtnsalvar() {
        JButton btnSalvar = new JButton("Salvar Receita");
        btnSalvar.setBounds(larJanela - 170, 420, 150, 30);

        ActionListener listener = e -> {
            if (receita.getListaStep().isEmpty() && txtTitulo.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos", "AVISO", JOptionPane.ERROR_MESSAGE);
            } else {
                receita.setDescricao(txtTitulo.getText());
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
        JLabel lbTitulo = new JLabel("Titulo : ");
        lbTitulo.setForeground(Color.black);
        lbTitulo.setBounds(5, 10, 150, 30);
        lbTitulo.setForeground(Color.black);
        return lbTitulo;
    }

    private JLabel getLbStep() {
        JLabel lbStep = new JLabel("Step's : ");
        lbStep.setForeground(Color.black);
        lbStep.setBounds(5, 70, 150, 30);
        lbStep.setForeground(Color.black);
        return lbStep;
    }

    private JLabel getLbQtdStep() {
        lbQtdStep = new JLabel("Quantidade: 0");
        lbQtdStep.setForeground(Color.black);
        lbQtdStep.setBounds(larJanela - 100, 70, 150, 30);
        lbQtdStep.setForeground(Color.black);
        return lbQtdStep;
    }

    private JTextField getTxtTitulo() {
        txtTitulo = new JTextField(receita.getDescricao() == null ? "" : receita.getDescricao(),
                JTextField.RIGHT);
        txtTitulo.setBounds(150, 10, larJanela - 250, 30);
        return txtTitulo;
    }
}
