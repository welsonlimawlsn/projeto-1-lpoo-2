package br.com.welson.banco.conta;

import br.com.welson.banco.comum.MensagemErro;
import br.com.welson.banco.exception.NegocioException;

public class ContaSalario extends Conta {
    public ContaSalario() {
        super(TipoConta.SALARIO);
    }

    @Override
    protected void executaRegraQuantidadeSaque() throws NegocioException {

        if (getQuantidadeSaqueMes() >= 1) {
            throw new NegocioException(MensagemErro.CONTA_EXCEDEU_NUMERO_DE_SAQUES);
        }
    }
}
