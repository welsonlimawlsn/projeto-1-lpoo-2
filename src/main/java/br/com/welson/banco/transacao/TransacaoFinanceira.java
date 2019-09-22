package br.com.welson.banco.transacao;

import java.math.BigDecimal;

public class TransacaoFinanceira extends AbstractTransacao {

    private BigDecimal valor;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
