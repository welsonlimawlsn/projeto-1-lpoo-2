package br.com.welson.banco.autorizacao;

import br.com.welson.banco.comum.GenericDAO;

public class AutorizacaoDAO extends GenericDAO<Autorizacao, Integer> {

    public AutorizacaoDAO() {
        super(Autorizacao.class);
    }

    @Override
    public void insere(Autorizacao entidade) {
        entidade.setNsu(getProximaPosicao());
        super.insere(entidade);
    }
}
