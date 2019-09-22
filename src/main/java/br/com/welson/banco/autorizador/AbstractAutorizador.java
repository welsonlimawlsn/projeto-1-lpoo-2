package br.com.welson.banco.autorizador;

import br.com.welson.banco.autorizacao.Autorizacao;
import br.com.welson.banco.autorizacao.AutorizacaoDAO;
import br.com.welson.banco.autorizacao.StatusAutorizacao;
import br.com.welson.banco.cliente.ClienteDAO;
import br.com.welson.banco.conta.Conta;
import br.com.welson.banco.conta.ContaDAO;
import br.com.welson.banco.conta.ContaService;
import br.com.welson.banco.di.DI;
import br.com.welson.banco.di.Factory;
import br.com.welson.banco.exception.NegocioException;
import br.com.welson.banco.transacao.AbstractTransacao;

import static br.com.welson.banco.di.DI.injeta;

public abstract class AbstractAutorizador {

    private final static Factory<Autorizacao> AUTORIZACAO_FACTORY = new Factory<>(Autorizacao.class);

    private AutorizacaoDAO autorizacaoDAO = injeta(AutorizacaoDAO.class);
    private ContaDAO contaDAO = injeta(ContaDAO.class);
    private ClienteDAO clienteDAO = injeta(ClienteDAO.class);

    private ContaService contaService = DI.injeta(ContaService.class);

    public Autorizacao executa(AbstractTransacao transacao) {

        Autorizacao autorizacao = AUTORIZACAO_FACTORY.criaNovaInstacia(transacao.getTipoTransacao());

        autorizacaoDAO.insere(autorizacao);

        autorizacao.setTransacao(transacao);
        autorizacao.setStatusAutorizacao(StatusAutorizacao.SOLICITADA);
        autorizacao.setAgencia(transacao.getAgencia());
        autorizacao.setConta(transacao.getNumero());

        try {
            executaRegrasEspecificas(transacao, autorizacao);
            autorizacao.setStatusAutorizacao(StatusAutorizacao.CONFIRMADA);
        } catch (NegocioException e) {
            e.printStackTrace();
            autorizacao.setStatusAutorizacao(StatusAutorizacao.NEGADA);
            autorizacao.setMotivoNegacao(e.getMessage());
        }

        atualizaBanco();

        return autorizacao;
    }

    protected abstract void executaRegrasEspecificas(AbstractTransacao transacao, Autorizacao autorizacao) throws NegocioException;

    protected Conta getContaCliente(AbstractTransacao transacao) throws NegocioException {
        return contaService.procuraPorAgenciaNumero(transacao.getAgencia(), transacao.getNumero());
    }

    protected ContaService getContaService() {
        return contaService;
    }

    private void atualizaBanco() {
        autorizacaoDAO.atualizaArquivo();
        contaDAO.atualizaArquivo();
        clienteDAO.atualizaArquivo();
    }
}
