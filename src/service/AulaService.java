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

    // =========================
    // CADASTRAR AULA
    // =========================
    public boolean cadastrar(Aula aula) {

        Aula existente =
                repository.buscarPorNome(aula.getNome());

        if (existente != null) {
            return false;
        }

        repository.salvar(aula);

        return true;
    }

    // =========================
    // LISTAR AULAS
    // =========================
    public List<Aula> listar() {

        return repository.listar();
    }

    // =========================
    // BUSCAR AULA
    // =========================
    public Aula buscarPorNome(String nome) {

        return repository.buscarPorNome(nome);
    }

    // =========================
    // EXCLUIR AULA
    // =========================
    public boolean excluir(String nome) {

        Aula aula =
                repository.buscarPorNome(nome);

        if (aula == null) {
            return false;
        }

        repository.remover(aula);

        return true;
    }

    // =========================
    // ATUALIZAR AULA
    // =========================
    public boolean atualizar(Aula nova) {

        Aula existente =
                repository.buscarPorNome(
                        nova.getNome()
                );

        if (existente == null) {
            return false;
        }

        existente.setHorario(
                nova.getHorario()
        );

        existente.setDuracao(
                nova.getDuracao()
        );

        existente.setCapacidadeMaxima(
                nova.getCapacidadeMaxima()
        );

        existente.setInstrutor(
                nova.getInstrutor()
        );

        return true;
    }

    // =========================
    // MATRICULAR ALUNO
    // =========================
    public boolean matricularAluno(
            String nomeAula,
            Aluno aluno) {

        Aula aula =
                repository.buscarPorNome(nomeAula);

        if (aula == null) {

            System.out.println(
                    "Aula não encontrada."
            );

            return false;
        }

        if (aula.getAlunos().contains(aluno)) {

            System.out.println(
                    "Aluno já matriculado."
            );

            return false;
        }

        if (aula.getAlunos().size()
                >= aula.getCapacidadeMaxima()) {

            System.out.println(
                    "Aula lotada."
            );

            return false;
        }

        aula.adicionarAluno(aluno);

        aluno.adicionarAula(aula);

        return true;
    }

    // =========================
    // RELATÓRIO DE OCUPAÇÃO
    // =========================
    public void gerarRelatorioOcupacao() {

        System.out.println(
                "\n===== RELATÓRIO DE OCUPAÇÃO ====="
        );

        List<Aula> aulas =
                repository.listar();

        if (aulas.isEmpty()) {

            System.out.println(
                    "Nenhuma aula cadastrada."
            );

            return;
        }

        for (Aula aula : aulas) {

            int ocupacaoAtual =
                    aula.getAlunos().size();

            int capacidadeMaxima =
                    aula.getCapacidadeMaxima();

            double percentual = 0;

            if (capacidadeMaxima > 0) {

                percentual =
                        ((double) ocupacaoAtual
                                / capacidadeMaxima) * 100;
            }

            System.out.println(
                    "\nAula: "
                            + aula.getNome()
            );

            System.out.println(
                    "Horário: "
                            + aula.getHorario()
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

    // =========================
    // DETALHAR AULA
    // =========================
    public void detalharAula(String nomeAula) {

        Aula aula =
                repository.buscarPorNome(nomeAula);

        if (aula == null) {

            System.out.println(
                    "Aula não encontrada."
            );

            return;
        }

        System.out.println(
                "\n===== DETALHES DA AULA ====="
        );

        System.out.println(
                "Nome: "
                        + aula.getNome()
        );

        System.out.println(
                "Horário: "
                        + aula.getHorario()
        );

        System.out.println(
                "Duração: "
                        + aula.getDuracao()
                        + " minutos"
        );

        System.out.println(
                "Instrutor: "
                        + aula.getInstrutor().getNome()
        );

        System.out.println(
                "Capacidade: "
                        + aula.getAlunos().size()
                        + "/"
                        + aula.getCapacidadeMaxima()
        );

        System.out.println(
                "\n===== ALUNOS MATRICULADOS ====="
        );

        if (aula.getAlunos().isEmpty()) {

            System.out.println(
                    "Nenhum aluno matriculado."
            );

            return;
        }

        for (Aluno aluno : aula.getAlunos()) {

            System.out.println(
                    "- "
                            + aluno.getNome()
                            + " | CPF: "
                            + aluno.getCpf()
            );
        }
    }
}