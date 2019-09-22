package br.com.welson.banco.lancamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Lancamento implements Serializable {

    private String descricao;
    private TipoLancamento tipoLancamento;
    private BigDecimal valor;
    private LocalDate data;

    public Lancamento(TipoLancamento tipoLancamento, BigDecimal valor) {
        this.tipoLancamento = tipoLancamento;
        this.valor = valor;
        data = LocalDate.now();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + " - " + descricao + " - "
                + NumberFormat.getCurrencyInstance().format(valor) + (tipoLancamento == TipoLancamento.DEBITO ? "-" : "+");
    }
}
