package br.com.welson.banco.di;

import br.com.welson.banco.autorizacao.AutorizacaoDAO;
import br.com.welson.banco.autorizacao.AutorizacaoExtrato;
import br.com.welson.banco.autorizacao.AutorizacaoSaldo;
import br.com.welson.banco.autorizacao.AutorizacaoSemRespostaEspecifica;
import br.com.welson.banco.autorizador.*;
import br.com.welson.banco.cliente.ClienteDAO;
import br.com.welson.banco.cliente.ClienteService;
import br.com.welson.banco.conta.ContaDAO;
import br.com.welson.banco.conta.ContaService;

public class RegistroClassesDI {

    public final static Class<?>[] CLASSES_DISPONIVEIS = {
            AutorizacaoSemRespostaEspecifica.class,
            AutorizadorSaque.class,
            AutorizadorDeposito.class,
            ClienteService.class,
            ClienteDAO.class,
            ContaService.class,
            ContaDAO.class,
            AutorizacaoDAO.class,
            AutorizadorTransferencia.class,
            AutorizadorSaldo.class,
            AutorizacaoSaldo.class,
            AutorizadorExtratoUltimosLancamentos.class,
            AutorizadorExtratoPorPeriodo.class,
            AutorizacaoExtrato.class
    };
}
