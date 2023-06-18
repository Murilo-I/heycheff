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
        janela.getContentPane().setBackground(new Color(150, 150, 150));

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
        JButton btnDel = new JButton("Remove item");
        btnDel.setBounds(5, 420, 150, 30);

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
        btnSalvar.setBounds(320, 420, 150, 30);

        ActionListener listener = e -> {
            if (step.getLista().isEmpty() && txtDesc.getText().isBlank()) {
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
        JButton btnAdd = new JButton("Add item");
        btnAdd.setBounds(160, 420, 150, 30);

        ActionListener listener = e -> {
            String ingrediente = JOptionPane.showInputDialog("Digite o Igrendiente:");
            String qtd = JOptionPane.showInputDialog("Digite o Quantidade:");

            if (!ingrediente.isEmpty() && !qtd.isEmpty()) {
                Ingrediente ingre = new Ingrediente(ingrediente, Integer.parseInt(qtd));
                step.addIngrediente(ingre);
                listModel.addElement(ingre);
                lbQtdIngr.setText("Quantidade: " + listModel.getSize());
            } else {
                JOptionPane.showMessageDialog(null, "Não pode ser Vazio", "AVISO", JOptionPane.ERROR_MESSAGE);
            }
        };

        btnAdd.addActionListener(listener);
        return btnAdd;
    }

    private JLabel getLbDesc() {
        JLabel lbDesc = new JLabel("Descricão : ");
        lbDesc.setForeground(Color.black);
        lbDesc.setBounds(5, 10, 150, 30);
        lbDesc.setForeground(Color.black);
        return lbDesc;
    }

    private JLabel getLbIngre() {
        JLabel lbIngre = new JLabel("Ingrediente: ");
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
