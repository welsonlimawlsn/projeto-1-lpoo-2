package br.com.welson.banco.autorizacao;

import br.com.welson.banco.transacao.TipoTransacao;
import br.com.welson.banco.transacao.Transacao;

@Transacao({
        TipoTransacao.SAQUE,
        TipoTransacao.DEPOSITO,
        TipoTransacao.TRANSFERENCIA_ENTRE_CONTAS
})
public class AutorizacaoSemRespostaEspecifica extends AbstractAutorizacao {
}
