package br.gov.sp.cps.models;

import java.math.BigDecimal;

public class ContaBancaria {
    private BigDecimal saldo = new BigDecimal("500.00");

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void depositar(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Informe um valor maior que zero.");
        }
        saldo = saldo.add(valor);
    }
}
