package br.com.welson.banco.autorizador;

import br.com.welson.banco.autorizacao.AbstractAutorizacao;
import br.com.welson.banco.conta.Conta;
import br.com.welson.banco.exception.NegocioException;
import br.com.welson.banco.lancamento.Lancamento;
import br.com.welson.banco.transacao.AbstractTransacao;
import br.com.welson.banco.transacao.TipoTransacao;
import br.com.welson.banco.transacao.Transacao;
import br.com.welson.banco.transacao.TransacaoFinanceira;

@Transacao(TipoTransacao.SAQUE)
public class AutorizadorSaque extends AbstractAutorizador {

    @Override
    protected void executaRegrasEspecificas(AbstractTransacao transacao, AbstractAutorizacao autorizacao) throws NegocioException {
        TransacaoFinanceira transacaoFinanceira = (TransacaoFinanceira) transacao;
        Conta conta = getContaCliente(transacao);
        Lancamento lancamento = conta.debita(transacaoFinanceira.getValor());
        lancamento.setDescricao("Saque em conta");
        conta.registraSaque();
    }
}
