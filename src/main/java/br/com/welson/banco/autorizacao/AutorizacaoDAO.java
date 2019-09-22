package br.com.welson.banco.autorizacao;

import br.com.welson.banco.comum.GenericDAO;

public class AutorizacaoDAO extends GenericDAO<AbstractAutorizacao, Integer> {

    public AutorizacaoDAO() {
        super(AbstractAutorizacao.class);
    }

    @Override
    public void insere(AbstractAutorizacao entidade) {
        entidade.setNsu(getProximaPosicao());
        super.insere(entidade);
    }
}
