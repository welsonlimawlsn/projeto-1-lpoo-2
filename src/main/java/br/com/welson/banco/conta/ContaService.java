package br.com.welson.banco.conta;

import br.com.welson.banco.cliente.Cliente;
import br.com.welson.banco.comum.MensagemErro;
import br.com.welson.banco.exception.NegocioException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import static br.com.welson.banco.di.DI.injeta;

public class ContaService {

    private ContaDAO contaDAO = injeta(ContaDAO.class);

    public Conta novaConta(TipoConta tipoConta, Cliente cliente) {

        Conta conta;

        switch (tipoConta) {
            case POUPANCA:
                conta = new ContaPoupanca();
                break;
            case SALARIO:
                conta = new ContaSalario();
                break;
            default:
                conta = new ContaCorrente();
        }

        conta.setCliente(cliente);
        conta.setLancamentos(new ArrayList<>());
        conta.setId(new NumeroConta(1));
        conta.setSaldo(BigDecimal.ZERO);
        conta.setQuantidadeSaqueMensal(new HashMap<>());

        contaDAO.insere(conta);

        return conta;
    }

    public Conta procuraPorAgenciaNumero(int agencia, int numero) throws NegocioException {
        return contaDAO.procuraPorId(new NumeroConta(agencia, numero)).orElseThrow(() -> new NegocioException(MensagemErro.CONTA_NAO_ENCONTRADA));
    }
}
