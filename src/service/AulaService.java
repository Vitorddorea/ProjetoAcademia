package service;

import entities.Aula;
import repositories.AulaRepository;

import java.util.List;

public class AulaService {

    private final AulaRepository repository;

    public AulaService(AulaRepository repository) {
        this.repository = repository;
    }

    public boolean cadastrar(Aula aula) {
        if (repository.buscarPorNome(aula.getNome()) != null) {
            return false;
        }
        repository.salvar(aula);
        return true;
    }

    public List<Aula> listar() {
        return repository.listar();
    }

    public boolean excluir(String nome) {
        Aula aula = repository.buscarPorNome(nome);
        if (aula == null) return false;

        repository.remover(aula);
        return true;
    }

    public boolean atualizar(Aula nova) {
        Aula existente = repository.buscarPorNome(nova.getNome());

        if (existente == null) return false;

        existente.setHorario(nova.getHorario());
        existente.setDuracao(nova.getDuracao());
        existente.setCapacidadeMaxima(nova.getCapacidadeMaxima());
        existente.setInstrutor(nova.getInstrutor());

        return true;
    }

    public Aula buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }
}