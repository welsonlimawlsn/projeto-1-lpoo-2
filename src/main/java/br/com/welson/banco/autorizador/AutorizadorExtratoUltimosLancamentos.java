package br.com.welson.banco.autorizador;

import br.com.welson.banco.lancamento.Lancamento;
import br.com.welson.banco.transacao.AbstractTransacao;
import br.com.welson.banco.transacao.TipoTransacao;
import br.com.welson.banco.transacao.Transacao;

import java.time.LocalDate;

@Transacao(TipoTransacao.EXTRATO_ULTIMOS_LANCAMENTOS)
public class AutorizadorExtratoUltimosLancamentos extends AbstractAutorizadorExtrato {

    @Override
    protected boolean filtroPeriodo(AbstractTransacao transacao, Lancamento lancamento) {
        LocalDate dataAtual = LocalDate.now();
        LocalDate data = lancamento.getData();
        return data.isEqual(dataAtual) || (data.isBefore(dataAtual) && data.isAfter(dataAtual.minusDays(7)));
    }
}
