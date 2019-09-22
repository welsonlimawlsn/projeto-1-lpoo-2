package br.com.welson.banco.autorizador;

import br.com.welson.banco.autorizacao.Autorizacao;
import br.com.welson.banco.conta.Conta;
import br.com.welson.banco.exception.NegocioException;
import br.com.welson.banco.lancamento.Lancamento;
import br.com.welson.banco.transacao.AbstractTransacao;
import br.com.welson.banco.transacao.TipoTransacao;
import br.com.welson.banco.transacao.Transacao;
import br.com.welson.banco.transacao.TransacaoFinanceira;

@Transacao(TipoTransacao.DEPOSITO)
public class AutorizadorDeposito extends AbstractAutorizador {

    @Override
    protected void executaRegrasEspecificas(AbstractTransacao transacao, Autorizacao autorizacao) throws NegocioException {
        TransacaoFinanceira transacaoFinanceira = (TransacaoFinanceira) transacao;
        Conta conta = getContaCliente(transacao);
        Lancamento lancamento = conta.credita(transacaoFinanceira.getValor());
        lancamento.setDescricao("Deposito em conta");
    }
}
