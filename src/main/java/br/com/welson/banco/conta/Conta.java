package br.com.welson.banco.conta;

import br.com.welson.banco.cliente.Cliente;
import br.com.welson.banco.comum.AbstractEntity;
import br.com.welson.banco.comum.MensagemErro;
import br.com.welson.banco.exception.NegocioException;
import br.com.welson.banco.lancamento.Lancamento;
import br.com.welson.banco.lancamento.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Conta extends AbstractEntity<NumeroConta> {

    private Cliente cliente;
    private NumeroConta numeroConta;
    private TipoConta tipoConta;
    private BigDecimal saldo = BigDecimal.ZERO;
    private Map<LocalDate, Integer> quantidadeSaqueMensal;
    private List<Lancamento> lancamentos;

    public Conta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Lancamento debita(BigDecimal valor) throws NegocioException {

        validaValor(valor);

        if (saldo.compareTo(valor) < 0) {
            throw new NegocioException(MensagemErro.SALDO_INSUFICIENTE);
        }
        saldo = saldo.subtract(valor);

        Lancamento lancamento = new Lancamento(TipoLancamento.DEBITO, valor);
        lancamentos.add(lancamento);

        return lancamento;
    }

    public Lancamento credita(BigDecimal valor) throws NegocioException {

        validaValor(valor);

        saldo = saldo.add(valor);

        Lancamento lancamento = new Lancamento(TipoLancamento.CREDITO, valor);
        lancamentos.add(lancamento);

        return lancamento;
    }

    public void registraSaque() throws NegocioException {

        if (quantidadeSaqueMensal == null) {
            quantidadeSaqueMensal = new HashMap<>();
        }

        executaRegraQuantidadeSaque();

        LocalDate mes = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());

        if (quantidadeSaqueMensal.containsKey(mes)) {
            quantidadeSaqueMensal.put(mes, quantidadeSaqueMensal.get(mes) + 1);
        } else {
            quantidadeSaqueMensal.put(mes, 1);
        }
    }

    protected Integer getQuantidadeSaqueMes() {

        LocalDate mes = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());

        if (quantidadeSaqueMensal.containsKey(mes)) {
            return quantidadeSaqueMensal.get(mes);
        }

        return 0;
    }

    private void validaValor(BigDecimal valor) throws NegocioException {

        if (valor.compareTo(new BigDecimal(0)) <= 0) {
            throw new NegocioException(MensagemErro.VALOR_DEVE_SER_MAIOR_QUE_ZERO);
        }
    }

    protected abstract void executaRegraQuantidadeSaque() throws NegocioException;

    @Override
    public NumeroConta getId() {
        return numeroConta;
    }

    @Override
    public void setId(NumeroConta numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public NumeroConta getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(NumeroConta numeroConta) {
        this.numeroConta = numeroConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Map<LocalDate, Integer> getQuantidadeSaqueMensal() {
        return quantidadeSaqueMensal;
    }

    public void setQuantidadeSaqueMensal(Map<LocalDate, Integer> quantidadeSaqueMensal) {
        this.quantidadeSaqueMensal = quantidadeSaqueMensal;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }
}
