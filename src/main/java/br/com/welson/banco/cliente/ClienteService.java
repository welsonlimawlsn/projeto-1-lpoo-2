package br.com.welson.banco.cliente;

import br.com.welson.banco.exception.NegocioException;

import static br.com.welson.banco.comum.Validador.naoVazio;
import static br.com.welson.banco.di.DI.injeta;

public class ClienteService {

    private ClienteDAO dao = injeta(ClienteDAO.class);

    public void novo(Cliente cliente) throws NegocioException {
        validaCliente(cliente);
        dao.insere(cliente);
    }

    private void validaCliente(Cliente cliente) throws NegocioException {
        naoVazio(cliente.getNome(), "O nome é obrigatório!");
        naoVazio(cliente.getCpf(), "O CPF é obrigatório!");
    }
}
