package br.com.welson.banco.autorizacao;

import br.com.welson.banco.comum.AbstractEntity;
import br.com.welson.banco.transacao.AbstractTransacao;

public abstract class AbstractAutorizacao extends AbstractEntity<Integer> {

    private Integer nsu;
    private Integer agencia;
    private Integer conta;
    private StatusAutorizacao statusAutorizacao;
    private String motivoNegacao;
    private AbstractTransacao transacao;

    @Override
    public Integer getId() {
        return nsu;
    }

    @Override
    public void setId(Integer id) {
        nsu = id;
    }

    public Integer getNsu() {
        return nsu;
    }

    public void setNsu(Integer nsu) {
        this.nsu = nsu;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public StatusAutorizacao getStatusAutorizacao() {
        return statusAutorizacao;
    }

    public void setStatusAutorizacao(StatusAutorizacao statusAutorizacao) {
        this.statusAutorizacao = statusAutorizacao;
    }

    public String getMotivoNegacao() {
        return motivoNegacao;
    }

    public void setMotivoNegacao(String motivoNegacao) {
        this.motivoNegacao = motivoNegacao;
    }

    public AbstractTransacao getTransacao() {
        return transacao;
    }

    public void setTransacao(AbstractTransacao transacao) {
        this.transacao = transacao;
    }

    @Override
    public String toString() {
        return "";
    }
}
