package br.com.welson.banco.conta;

import java.io.Serializable;
import java.util.Objects;

public class NumeroConta implements Serializable {

    private Integer agencia;
    private Integer numero;

    public NumeroConta() {
    }

    public NumeroConta(Integer agencia) {
        this.agencia = agencia;
    }

    public NumeroConta(Integer agencia, Integer numero) {
        this.agencia = agencia;
        this.numero = numero;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumeroConta that = (NumeroConta) o;
        return agencia.equals(that.agencia) &&
                numero.equals(that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agencia, numero);
    }
}
