package br.com.welson.banco.autorizador;

import br.com.welson.banco.autorizacao.Autorizacao;
import br.com.welson.banco.autorizacao.AutorizacaoExtrato;
import br.com.welson.banco.conta.Conta;
import br.com.welson.banco.exception.NegocioException;
import br.com.welson.banco.lancamento.Lancamento;
import br.com.welson.banco.transacao.AbstractTransacao;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractAutorizadorExtrato extends AbstractAutorizador {

    @Override
    protected void executaRegrasEspecificas(AbstractTransacao transacao, Autorizacao autorizacao) throws NegocioException {

        AutorizacaoExtrato autorizacaoExtrato = (AutorizacaoExtrato) autorizacao;

        Conta conta = getContaCliente(transacao);

        List<Lancamento> lancamentosFiltrados = conta.getLancamentos().stream()
                .filter(lancamento -> filtroPeriodo(transacao, lancamento))
                .collect(Collectors.toList());

        autorizacaoExtrato.setLancamentos(lancamentosFiltrados);
    }

    protected abstract boolean filtroPeriodo(AbstractTransacao transacao, Lancamento lancamento);
}
