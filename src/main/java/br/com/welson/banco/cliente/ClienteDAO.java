package br.com.welson.banco.cliente;

import br.com.welson.banco.comum.GenericDAO;

public class ClienteDAO extends GenericDAO<Cliente, String> {
    public ClienteDAO() {
        super(Cliente.class);
    }
}
