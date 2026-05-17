package service;

import entities.Aluno;
import entities.Aula;
import entities.Inscricao;
import repositories.InscricaoDAO;

import java.util.List;

public class InscricaoService {

    private InscricaoDAO repository;
    private AlunoService alunoService;
    private AulaService aulaService;

    public InscricaoService(InscricaoDAO repository,
                            AlunoService alunoService,
                            AulaService aulaService) {

        this.repository = repository;
        this.alunoService = alunoService;
        this.aulaService = aulaService;
    }

    public boolean inscrever(String cpf, String nomeAula) {

        Aluno aluno = alunoService.buscarPorCpf(cpf);
        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return false;
        }

        if (!alunoService.planoAtivo(aluno)) {
            System.out.println(
                    "Plano vencido em: "
                            + alunoService.dataVencimento(aluno)
            );
            return false;
        }

        Aula aula = aulaService.buscarPorNome(nomeAula);
        if (aula == null) {
            System.out.println("Aula não encontrada.");
            return false;
        }

        if (possuiConflitoHorario(aluno, aula)) {
            System.out.println(
                    "Conflito de horário! " +
                            "Aluno já possui aula nesse horário."
            );
            return false;
        }

        boolean jaExiste = repository.listar().stream()
                .anyMatch(i ->
                        i.getAluno().getCpf().equals(cpf) &&
                                i.getAula().getNome().equalsIgnoreCase(nomeAula)
                );

        if (jaExiste) {
            System.out.println("Aluno já inscrito nessa aula.");
            return false;
        }

        try {
            aula.adicionarAluno();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        aluno.adicionarAula(aula);

        aulaService.atualizar(aula);

        boolean sucesso = repository.salvar(new Inscricao(aluno, aula));

        if (sucesso) {
            System.out.println("Inscrição realizada!");
        }

        return sucesso;
    }

    private boolean possuiConflitoHorario(Aluno aluno, Aula novaAula) {

        return repository.listar().stream()
                .filter(i ->
                        i.getAluno().getCpf().equals(aluno.getCpf())
                )
                .anyMatch(i ->
                        i.getAula().getHorario()
                                .equalsIgnoreCase(novaAula.getHorario())
                );
    }

    public List<Inscricao> listar() {
        return repository.listar();
    }

    public boolean cancelar(String cpf, String nomeAula) {

        Aluno aluno = alunoService.buscarPorCpf(cpf);
        Aula aula = aulaService.buscarPorNome(nomeAula);

        boolean removido = repository.remover(cpf, nomeAula);

        if (removido) {
            if (aluno != null && aula != null) {
                aluno.removerAula(aula);
            }

            System.out.println("Inscrição cancelada!");
        } else {
            System.out.println("Inscrição não encontrada.");
        }

        return removido;
    }
}