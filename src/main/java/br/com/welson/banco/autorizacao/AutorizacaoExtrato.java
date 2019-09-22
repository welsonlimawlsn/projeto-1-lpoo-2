package br.com.welson.banco.autorizacao;

import br.com.welson.banco.lancamento.Lancamento;
import br.com.welson.banco.transacao.TipoTransacao;
import br.com.welson.banco.transacao.Transacao;

import java.util.List;

@Transacao({
        TipoTransacao.EXTRATO_ULTIMOS_LANCAMENTOS,
        TipoTransacao.EXTRATO_POR_PERIODO
})
public class AutorizacaoExtrato extends AbstractAutorizacao {

    private List<Lancamento> lancamentos;

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Extrato:\n");

        lancamentos.forEach(lancamento -> stringBuilder.append(lancamento).append("\n"));

        return stringBuilder.toString();
    }
}
