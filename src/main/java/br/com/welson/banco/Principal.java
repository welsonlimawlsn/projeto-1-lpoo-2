package br.com.welson.banco;

import br.com.welson.banco.autorizacao.Autorizacao;
import br.com.welson.banco.autorizacao.StatusAutorizacao;
import br.com.welson.banco.autorizador.AbstractAutorizador;
import br.com.welson.banco.cliente.Cliente;
import br.com.welson.banco.cliente.ClienteService;
import br.com.welson.banco.comum.Canal;
import br.com.welson.banco.conta.Conta;
import br.com.welson.banco.conta.ContaService;
import br.com.welson.banco.conta.NumeroConta;
import br.com.welson.banco.conta.TipoConta;
import br.com.welson.banco.di.Factory;
import br.com.welson.banco.exception.NegocioException;
import br.com.welson.banco.transacao.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static br.com.welson.banco.di.DI.injeta;

public class Principal {

    private static final String MENU_PRINCIPAL = "Escolha uma opção: \n1 - Nova Conta\n2 - Novo Deposito\n3 - Novo Saque\n4 - Nova Transferência" +
            "\n5 - Consultar Extrato\n6 - Consultar Saldo\n7 - Sair";

    private static final String MENU_EXTRATO = "1 - Ultimos lançamentos\n2 - Mes Atual\n3 - Voltar";

    private ClienteService clienteService = injeta(ClienteService.class);
    private ContaService contaService = injeta(ContaService.class);
    private Factory<AbstractAutorizador> factory = new Factory<>(AbstractAutorizador.class);

    public static void main(String[] args) {

        new Principal().inicia();

    }

    private void inicia() {
        menuPrincipal();
    }

    private void menuPrincipal() {
        int opcao;
        do {
            opcao = showInputDialogInteger(MENU_PRINCIPAL);
            switch (opcao) {
                case 1:
                    novaConta();
                    break;
                case 2:
                    novoDeposito();
                    break;
                case 3:
                    novoSaque();
                    break;
                case 4:
                    novaTransferencia();
                    break;
                case 5:
                    menuExtrato();
                    break;
                case 6:
                    novaConsultaSaldo();
                    break;
            }
        } while (opcao != 7);
    }

    private void menuExtrato() {
        int opcao;
        do {
            opcao = showInputDialogInteger(MENU_EXTRATO);
            switch (opcao) {
                case 1:
                    novaConsultaExtratoUltimosLancamentos();
                    break;
                case 2:
                    novaConsultaExtratoMesAtual();
                    break;
            }
        } while (opcao != 3);
    }


    private Cliente novaCliente() throws NegocioException {
        Cliente cliente = new Cliente();

        cliente.setNome(JOptionPane.showInputDialog("Digite o nome do cliente: "));
        cliente.setCpf(JOptionPane.showInputDialog("Digite o CPF do cliente: "));

        clienteService.novo(cliente);

        return cliente;
    }

    private void novaConta() {
        try {
            Cliente cliente = novaCliente();

            int tipoDeConta = JOptionPane.showOptionDialog(null, "Escolha o tipo de conta:", "Tipo de Conta",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, TipoConta.values(), null);

            Conta conta = contaService.novaConta(TipoConta.values()[tipoDeConta], cliente);

            NumeroConta numeroConta = conta.getNumeroConta();
            JOptionPane.showMessageDialog(null,
                    "Anote os dados da nova conta:\nAgência: " + numeroConta.getAgencia() + "\nNúmero: " + numeroConta.getNumero());

        } catch (NegocioException e) {
            e.printStackTrace();
            showMessageDialogError(e.getMessage() + " Tente novamente!", e.getMessage());
            novaConta();
        }
    }

    private void novaConsultaSaldo() {
        TransacaoServicos transacaoServicos = new TransacaoServicos();
        preencheTransacao(TipoTransacao.SALDO, transacaoServicos);
        executa(transacaoServicos);
    }

    private void novaConsultaExtratoUltimosLancamentos() {
        TransacaoServicos transacaoServicos = new TransacaoServicos();
        preencheTransacao(TipoTransacao.EXTRATO_ULTIMOS_LANCAMENTOS, transacaoServicos);
        executa(transacaoServicos);
    }

    private void novaConsultaExtratoMesAtual() {
        TransacaoExtratoPorPeriodo transacaoExtratoPorPeriodo = new TransacaoExtratoPorPeriodo();

        LocalDate dataAtual = LocalDate.now();

        transacaoExtratoPorPeriodo.setDataFinal(dataAtual);
        transacaoExtratoPorPeriodo.setDataInicial(dataAtual.with(TemporalAdjusters.firstDayOfMonth()));

        preencheTransacao(TipoTransacao.EXTRATO_POR_PERIODO, transacaoExtratoPorPeriodo);

        executa(transacaoExtratoPorPeriodo);
    }

    private void novoDeposito() {
        novaTransacaoFinanceira(TipoTransacao.DEPOSITO);
    }

    private void novoSaque() {
        novaTransacaoFinanceira(TipoTransacao.SAQUE);
    }

    private void novaTransferencia() {
        TransacaoTransferencia transacaoTransferencia = new TransacaoTransferencia();

        preencheComumTransacaoFinanceira(TipoTransacao.TRANSFERENCIA_ENTRE_CONTAS, transacaoTransferencia);

        transacaoTransferencia.setAgenciaDestino(showInputDialogInteger("Digite a agencia destino: "));
        transacaoTransferencia.setNumeroDestino(showInputDialogInteger("Digite o número da conta destino: "));

        executa(transacaoTransferencia);
    }

    private void novaTransacaoFinanceira(TipoTransacao tipoTransacao) {
        TransacaoFinanceira transacaoFinanceira = new TransacaoFinanceira();

        preencheComumTransacaoFinanceira(tipoTransacao, transacaoFinanceira);

        executa(transacaoFinanceira);
    }

    private void preencheComumTransacaoFinanceira(TipoTransacao tipoTransacao, TransacaoFinanceira transacaoFinanceira) {
        preencheTransacao(tipoTransacao, transacaoFinanceira);
        transacaoFinanceira.setValor(showInputDialogBigDecimal());
    }

    private void preencheTransacao(TipoTransacao tipoTransacao, AbstractTransacao transacaoFinanceira) {
        transacaoFinanceira.setTipoTransacao(tipoTransacao);
        transacaoFinanceira.setAgencia(showInputDialogInteger("Digite a agencia:"));
        transacaoFinanceira.setNumero(showInputDialogInteger("Digite o número da conta:"));
    }

    private void executa(AbstractTransacao transacao) {
        transacao.setCanal(Canal.AGENCIA);
        Autorizacao autorizacao = factory.criaNovaInstacia(transacao.getTipoTransacao()).executa(transacao);

        if (autorizacao.getStatusAutorizacao() == StatusAutorizacao.CONFIRMADA) {
            JOptionPane.showMessageDialog(null, "Transação realizada com sucesso!\n\n" + autorizacao.toString());
        } else {
            showMessageDialogError(autorizacao.getMotivoNegacao(), "Erro");
        }
    }

    private void showMessageDialogError(String motivoNegacao, String erro) {
        JOptionPane.showMessageDialog(null, motivoNegacao, erro, JOptionPane.ERROR_MESSAGE);
    }

    private BigDecimal showInputDialogBigDecimal() {
        try {
            return new BigDecimal(JOptionPane.showInputDialog("Digite o valor: "));
        } catch (NumberFormatException e) {
            showMessageDialogError("Insira um valor valido", "Erro");
            return showInputDialogBigDecimal();
        }
    }

    private int showInputDialogInteger(String s) {
        try {
            return Integer.parseInt(JOptionPane.showInputDialog(s));
        } catch (NumberFormatException e) {
            showMessageDialogError("Um número inteiro é necessario!", "Erro");
            return showInputDialogInteger(s);
        }
    }

}
