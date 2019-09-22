package br.com.welson.banco.autorizacao;

import br.com.welson.banco.transacao.TipoTransacao;
import br.com.welson.banco.transacao.Transacao;

import java.math.BigDecimal;
import java.text.NumberFormat;

@Transacao(TipoTransacao.SALDO)
public class AutorizacaoSaldo extends AbstractAutorizacao {

    private BigDecimal saldo;

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Saldo:" + NumberFormat.getCurrencyInstance().format(saldo);
    }
}
