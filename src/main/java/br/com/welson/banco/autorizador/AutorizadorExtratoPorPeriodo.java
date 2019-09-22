package br.com.welson.banco.autorizador;

import br.com.welson.banco.lancamento.Lancamento;
import br.com.welson.banco.transacao.AbstractTransacao;
import br.com.welson.banco.transacao.TipoTransacao;
import br.com.welson.banco.transacao.Transacao;
import br.com.welson.banco.transacao.TransacaoExtratoPorPeriodo;

import java.time.LocalDate;

@Transacao(TipoTransacao.EXTRATO_POR_PERIODO)
public class AutorizadorExtratoPorPeriodo extends AbstractAutorizadorExtrato {
    @Override
    protected boolean filtroPeriodo(AbstractTransacao transacao, Lancamento lancamento) {
        TransacaoExtratoPorPeriodo transacaoExtratoPorPeriodo = (TransacaoExtratoPorPeriodo) transacao;
        LocalDate data = lancamento.getData();
        LocalDate dataInicial = transacaoExtratoPorPeriodo.getDataInicial();
        LocalDate dataFinal = transacaoExtratoPorPeriodo.getDataFinal();
        return (data.isAfter(dataInicial) || data.isEqual(dataInicial)) && (data.isBefore(dataFinal) || data.isEqual(dataFinal));
    }
}
