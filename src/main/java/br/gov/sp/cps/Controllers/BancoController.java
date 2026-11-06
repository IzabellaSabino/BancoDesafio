package br.gov.sp.cps.controllers;

import br.gov.sp.cps.models.ContaBancaria;
import br.gov.sp.cps.views.TelaBanco;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class BancoController {
    private final ContaBancaria conta;
    private final TelaBanco tela;

    public BancoController(ContaBancaria conta, TelaBanco tela) {
        this.conta = conta;
        this.tela = tela;
    }

    public ActionListener acaoDepositar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = tela.getTxtValorDeposito().getText().trim();

                if (texto.isEmpty()) {
                    JOptionPane.showMessageDialog(tela, "Digite o valor do depósito.", "Atenção", JOptionPane.WARNING_MESSAGE);
                    tela.getTxtValorDeposito().requestFocus();
                    return;
                }

                try {
                    BigDecimal valor = converterValor(texto);

                    if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                        JOptionPane.showMessageDialog(tela, "O valor do depósito deve ser maior que zero.", "Valor inválido", JOptionPane.WARNING_MESSAGE);
                        tela.getTxtValorDeposito().requestFocus();
                        return;
                    }

                    conta.depositar(valor);
                    tela.atualizarSaldo(formatarMoeda(conta.getSaldo()));
                    tela.adicionarHistorico("Depósito: " + formatarMoeda(valor));
                    tela.getTxtValorDeposito().setText("");
                    tela.getTxtValorDeposito().requestFocus();
                    JOptionPane.showMessageDialog(tela, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(tela, "Digite um valor válido. Exemplo: 150,00.", "Valor inválido", JOptionPane.ERROR_MESSAGE);
                    tela.getTxtValorDeposito().requestFocus();
                }
            }
        };
    }

    private BigDecimal converterValor(String texto) {
        String valor = texto.replace("R$", "").replace(" ", "").trim();

        if (valor.contains(",") && valor.contains(".")) {
            valor = valor.replace(".", "").replace(",", ".");
        } else if (valor.contains(",")) {
            valor = valor.replace(",", ".");
        }

        if (!valor.matches("\\d+(\\.\\d{1,2})?")) {
            throw new NumberFormatException();
        }
        return new BigDecimal(valor);
    }

    private String formatarMoeda(BigDecimal valor) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor);
    }
}

