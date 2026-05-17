package service;

import entities.Instrutor;
import repositories.InstrutorRepository;

import java.util.List;

public class InstrutorService {

    private final InstrutorRepository repository;

    public InstrutorService(InstrutorRepository repository) {
        this.repository = repository;
    }

    public boolean cadastrar(Instrutor instrutor) {
        if (repository.buscarPorCpf(instrutor.getCpf()) != null) {
            return false;
        }
        repository.salvar(instrutor);
        return true;
    }

    public List<Instrutor> listar() {
        return repository.listar();
    }

    public boolean excluir(String cpf) {
        Instrutor instrutor = repository.buscarPorCpf(cpf);

        if (instrutor == null) return false;

        repository.remover(instrutor);
        return true;
    }

    public boolean atualizar(Instrutor atualizado) {
        Instrutor existente = repository.buscarPorCpf(atualizado.getCpf());

        if (existente == null) return false;

        existente.setNome(atualizado.getNome());
        existente.setTelefone(atualizado.getTelefone());
        existente.setEspecialidade(atualizado.getEspecialidade());
        existente.setHorarioTrabalho(atualizado.getHorarioTrabalho());

        return true;
    }

public Instrutor buscarPorCpf(String cpf) {

    for (Instrutor instrutor : repository.listar()) {

        if (instrutor.getCpf().equals(cpf)) {
            return instrutor;
        }
    }

    return null;
}
}