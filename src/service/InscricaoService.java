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

        Aula aula = aulaService.buscarPorNome(nomeAula);
        if (aula == null) {
            System.out.println("Aula não encontrada.");
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

        boolean sucesso = repository.salvar(new Inscricao(aluno, aula));

        if (sucesso) {
            System.out.println("Inscrição realizada!");
        }

        return sucesso;
    }

    public List<Inscricao> listar() {
        return repository.listar();
    }

    public boolean cancelar(String cpf, String nomeAula) {

        boolean removido = repository.remover(cpf, nomeAula);

        if (removido) {
            System.out.println("Inscrição cancelada!");
        } else {
            System.out.println("Inscrição não encontrada.");
        }

        return removido;
    }
}