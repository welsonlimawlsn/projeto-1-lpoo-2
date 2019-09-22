package br.com.welson.banco.transacao;

import br.com.welson.banco.comum.Canal;

import java.io.Serializable;

public abstract class AbstractTransacao implements Serializable {

    private Canal canal;
    private Integer agencia;
    private Integer numero;
    private TipoTransacao tipoTransacao;

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
}
