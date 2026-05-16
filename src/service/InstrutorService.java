package service;

import entities.Instrutor;
import repositories.InstrutorDAO;

import java.util.List;

public class InstrutorService {

    private final InstrutorDAO repository;

    public InstrutorService(InstrutorDAO repository) {
        this.repository = repository;
    }

    public boolean cadastrar(Instrutor instrutor) {
        if (repository.buscarPorCpf(instrutor.getCpf()) != null) {
            return false;
        }
        return repository.salvar(instrutor);
    }

    public List<Instrutor> listar() {
        return repository.listar();
    }

    public boolean excluir(String cpf) {
        return repository.remover(cpf);
    }

    public boolean atualizar(Instrutor atualizado) {
        return repository.atualizar(atualizado);
    }

    public Instrutor buscarPorCpf(String cpf) {
        return repository.buscarPorCpf(cpf);
    }
}