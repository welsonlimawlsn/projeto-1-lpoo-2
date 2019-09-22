package br.com.welson.banco.conta;

import br.com.welson.banco.comum.GenericDAO;

public class ContaDAO extends GenericDAO<Conta, NumeroConta> {

    public ContaDAO() {
        super(Conta.class);
    }

    @Override
    public void insere(Conta entidade) {
        entidade.getNumeroConta().setNumero(getProximaPosicao());
        super.insere(entidade);
    }
}
