package service;
import entities.Aluno;

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
    public boolean matricularAluno(
        String nomeAula,
        Aluno aluno) {

    Aula aula = repository.buscarPorNome(nomeAula);

    if (aula == null) {
        return false;
    }

    if (aula.getAlunos().size()
            >= aula.getCapacidadeMaxima()) {

        System.out.println("Aula lotada.");
        return false;
    }

    aula.adicionarAluno(aluno);

    aluno.adicionarAula(aula);

    return true;
    }
    public void gerarRelatorioOcupacao() {

    System.out.println(
        "\n===== RELATÓRIO DE OCUPAÇÃO ====="
    );

    for (Aula aula : repository.listar()) {

        int ocupacaoAtual =
                aula.getAlunos().size();

        int capacidadeMaxima =
                aula.getCapacidadeMaxima();

        double percentual =
                ((double) ocupacaoAtual
                / capacidadeMaxima) * 100;

        System.out.println(
            "\nAula: " + aula.getNome()
        );

        System.out.println(
            "Instrutor: "
            + aula.getInstrutor().getNome()
        );

        System.out.println(
            "Alunos matriculados: "
            + ocupacaoAtual
            + "/"
            + capacidadeMaxima
        );

        System.out.println(
            "Ocupação: "
            + String.format(
                "%.1f",
                percentual
            ) + "%"
        );
    }
}

    public Aula buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }
}