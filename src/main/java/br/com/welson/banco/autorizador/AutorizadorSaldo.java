package br.com.welson.banco.autorizador;

import br.com.welson.banco.autorizacao.Autorizacao;
import br.com.welson.banco.autorizacao.AutorizacaoSaldo;
import br.com.welson.banco.conta.Conta;
import br.com.welson.banco.exception.NegocioException;
import br.com.welson.banco.transacao.AbstractTransacao;
import br.com.welson.banco.transacao.TipoTransacao;
import br.com.welson.banco.transacao.Transacao;

@Transacao(TipoTransacao.SALDO)
public class AutorizadorSaldo extends AbstractAutorizador {
    @Override
    protected void executaRegrasEspecificas(AbstractTransacao transacao, Autorizacao autorizacao) throws NegocioException {
        AutorizacaoSaldo autorizacaoSaldo = (AutorizacaoSaldo) autorizacao;

        Conta conta = getContaCliente(transacao);

        autorizacaoSaldo.setSaldo(conta.getSaldo());
    }
}
