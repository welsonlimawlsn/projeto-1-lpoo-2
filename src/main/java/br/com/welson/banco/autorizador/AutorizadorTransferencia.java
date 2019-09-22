package br.com.welson.banco.autorizador;

import br.com.welson.banco.autorizacao.AbstractAutorizacao;
import br.com.welson.banco.comum.MensagemErro;
import br.com.welson.banco.conta.Conta;
import br.com.welson.banco.exception.NegocioException;
import br.com.welson.banco.lancamento.Lancamento;
import br.com.welson.banco.transacao.AbstractTransacao;
import br.com.welson.banco.transacao.TipoTransacao;
import br.com.welson.banco.transacao.Transacao;
import br.com.welson.banco.transacao.TransacaoTransferencia;

@Transacao(TipoTransacao.TRANSFERENCIA_ENTRE_CONTAS)
public class AutorizadorTransferencia extends AbstractAutorizador {

    @Override
    protected void executaRegrasEspecificas(AbstractTransacao transacao, AbstractAutorizacao autorizacao) throws NegocioException {
        TransacaoTransferencia transacaoTransferencia = (TransacaoTransferencia) transacao;

        Conta contaOrigem = personalizaErroCasoContaNaoSejaEncontrada(transacaoTransferencia.getAgencia(), transacaoTransferencia.getNumero(),
                MensagemErro.CONTA_ORIGEM_NAO_ENCONTRADA);

        Conta contaDestino = personalizaErroCasoContaNaoSejaEncontrada(transacaoTransferencia.getAgenciaDestino(),
                transacaoTransferencia.getNumeroDestino(), MensagemErro.CONTA_DESTINO_NAO_ENCONTRADA);

        Lancamento lancamentoOrigem = contaOrigem.debita(transacaoTransferencia.getValor());
        lancamentoOrigem.setDescricao("Transferência para " + contaDestino.getCliente().getNome());

        Lancamento lancamentoDestino = contaDestino.credita(transacaoTransferencia.getValor());
        lancamentoDestino.setDescricao("Transferência de " + contaOrigem.getCliente().getNome());
    }

    private Conta personalizaErroCasoContaNaoSejaEncontrada(Integer agencia, Integer numero, MensagemErro mensagemErro) throws NegocioException {
        Conta contaOrigem;
        try {
            contaOrigem = getContaService().procuraPorAgenciaNumero(agencia, numero);
        } catch (NegocioException e) {
            if (e.getMessage().equals(MensagemErro.CONTA_NAO_ENCONTRADA.getMessage())) {
                throw new NegocioException(mensagemErro, e);
            }
            throw e;
        }
        return contaOrigem;
    }
}
