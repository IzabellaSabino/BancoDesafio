package br.gov.sp.cps.views;

import br.gov.sp.cps.controllers.BancoController;
import br.gov.sp.cps.models.ContaBancaria;
import javax.swing.*;
import java.awt.*;

public class TelaBanco extends JFrame {
    private static final long serialVersionUID = 1L;

    private JLabel lblSaldo;
    private JTextField txtValorDeposito;
    private JButton btnDepositar;
    private JTextArea txtHistorico;

    public TelaBanco() {
        setTitle("MeuBanco");
        setSize(440, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        criarComponentes();

        ContaBancaria conta = new ContaBancaria();
        BancoController controller = new BancoController(conta, this);
        btnDepositar.addActionListener(controller.acaoDepositar());
        atualizarSaldo("R$ 500,00");
    }

    private void criarComponentes() {
        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(240, 243, 247));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(painel);

        JLabel lblTitulo = new JLabel("MEU BANCO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBackground(new Color(27, 36, 48));
        lblTitulo.setOpaque(true);
        lblTitulo.setBounds(20, 20, 384, 45);
        painel.add(lblTitulo);

        JLabel lblSaldoTitulo = new JLabel("SALDO DISPONÍVEL");
        lblSaldoTitulo.setFont(new Font("Arial", Font.BOLD, 11));
        lblSaldoTitulo.setForeground(new Color(90, 100, 115));
        lblSaldoTitulo.setBounds(35, 88, 350, 20);
        painel.add(lblSaldoTitulo);

        lblSaldo = new JLabel();
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 27));
        lblSaldo.setForeground(new Color(235, 112, 37));
        lblSaldo.setBounds(35, 110, 350, 40);
        painel.add(lblSaldo);

        JLabel lblValor = new JLabel("Valor do depósito");
        lblValor.setFont(new Font("Arial", Font.PLAIN, 12));
        lblValor.setForeground(new Color(75, 85, 100));
        lblValor.setBounds(20, 180, 384, 20);
        painel.add(lblValor);

        txtValorDeposito = new JTextField();
        txtValorDeposito.setFont(new Font("Arial", Font.PLAIN, 14));
        txtValorDeposito.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(195, 205, 218)),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        txtValorDeposito.setBounds(20, 205, 384, 38);
        painel.add(txtValorDeposito);

        btnDepositar = new JButton("REALIZAR DEPÓSITO");
        btnDepositar.setFont(new Font("Arial", Font.BOLD, 12));
        btnDepositar.setForeground(Color.WHITE);
        btnDepositar.setBackground(new Color(27, 36, 48));
        btnDepositar.setFocusPainted(false);
        btnDepositar.setBounds(20, 260, 384, 42);
        painel.add(btnDepositar);

        JLabel lblHistorico = new JLabel("HISTÓRICO DE MOVIMENTAÇÕES");
        lblHistorico.setFont(new Font("Arial", Font.BOLD, 11));
        lblHistorico.setForeground(new Color(90, 100, 115));
        lblHistorico.setBounds(20, 325, 384, 20);
        painel.add(lblHistorico);

        txtHistorico = new JTextArea("Aguardando movimentações...");
        txtHistorico.setEditable(false);
        txtHistorico.setFont(new Font("Arial", Font.PLAIN, 12));
        txtHistorico.setForeground(new Color(55, 65, 80));
        txtHistorico.setBackground(Color.WHITE);
        txtHistorico.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JScrollPane scroll = new JScrollPane(txtHistorico);
        scroll.setBounds(20, 350, 384, 105);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(210, 218, 228)));
        painel.add(scroll);
    }

    public JTextField getTxtValorDeposito() {
        return txtValorDeposito;
    }

    public void atualizarSaldo(String saldo) {
        lblSaldo.setText(saldo);
    }

    public void adicionarHistorico(String movimentacao) {
        if (txtHistorico.getText().equals("Aguardando movimentações...")) {
            txtHistorico.setText("");
        }
        txtHistorico.append(movimentacao + "\n");
    }
}
