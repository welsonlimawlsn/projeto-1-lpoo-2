package br.com.welson.banco.conta;

import br.com.welson.banco.comum.MensagemErro;
import br.com.welson.banco.exception.NegocioException;

public class ContaCorrente extends Conta {

    public ContaCorrente() {
        super(TipoConta.CORRENTE);
    }

    @Override
    protected void executaRegraQuantidadeSaque() throws NegocioException {

        if (getQuantidadeSaqueMes() >= 10) {
            throw new NegocioException(MensagemErro.CONTA_EXCEDEU_NUMERO_DE_SAQUES);
        }
    }
}
