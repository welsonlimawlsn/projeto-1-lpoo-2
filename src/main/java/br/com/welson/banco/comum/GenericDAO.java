package br.com.welson.banco.comum;

import br.com.welson.banco.exception.InfraestruturaException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class GenericDAO<E extends AbstractEntity<ID>, ID> {

    private Map<ID, E> entidades = new HashMap<>();
    private Path path;

    private Class<E> aClass;

    protected GenericDAO(Class<E> aClass) {
        this.aClass = aClass;
        this.path = Paths.get(aClass.getName() + ".dat");
        io();
    }

    public void insere(E entidade) {
        entidades.put(entidade.getId(), entidade);
        gravaNoArquivo(path);
    }

    public Optional<E> procuraPorId(ID id) {
        try {
            return Optional.ofNullable(entidades.get(id));
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    protected int getProximaPosicao() {
        return entidades.size() + 1;
    }

    private void io() {
        try {
            if (Files.exists(path)) {
                try (InputStream bufferedReader = Files.newInputStream(path)) {
                    entidades = (Map<ID, E>) new ObjectInputStream(bufferedReader).readObject();
                }
            } else {
                Path file = Files.createFile(path);
                gravaNoArquivo(file);
            }
        } catch (Exception e) {
            throw new InfraestruturaException("Erro ao ler " + aClass.getName(), e);
        }
    }

    private void gravaNoArquivo(Path file) {
        try (OutputStream outputStream = Files.newOutputStream(file)) {
            new ObjectOutputStream(outputStream).writeObject(entidades);
        } catch (IOException e) {
            throw new InfraestruturaException("Erro ao escrever o arquivo", e);
        }
    }

    public void atualizaArquivo() {
        gravaNoArquivo(path);
    }
}
