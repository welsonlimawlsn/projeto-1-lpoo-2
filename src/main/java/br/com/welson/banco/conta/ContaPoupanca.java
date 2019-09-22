package br.com.welson.banco.conta;

import br.com.welson.banco.comum.MensagemErro;
import br.com.welson.banco.exception.NegocioException;

public class ContaPoupanca extends Conta {

    public ContaPoupanca() {
        super(TipoConta.POUPANCA);
    }

    @Override
    protected void executaRegraQuantidadeSaque() throws NegocioException {

        if (getQuantidadeSaqueMes() >= 3) {
            throw new NegocioException(MensagemErro.CONTA_EXCEDEU_NUMERO_DE_SAQUES);
        }
    }
}
