package service;

import entities.Aluno;
import entities.Aula;
import entities.Inscricao;
import repositories.InscricaoRepository;

import java.util.List;

public class InscricaoService {

    private InscricaoRepository repository;
    private AlunoService alunoService;
    private AulaService aulaService;

    public InscricaoService(InscricaoRepository repository,
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

        repository.salvar(new Inscricao(aluno, aula));
        System.out.println("Inscrição realizada!");
        return true;
    }

    public List<Inscricao> listar() {
        return repository.listar();
    }

    public boolean cancelar(String cpf, String nomeAula) {

        Inscricao encontrada = repository.listar().stream()
                .filter(i ->
                        i.getAluno().getCpf().equals(cpf) &&
                        i.getAula().getNome().equalsIgnoreCase(nomeAula)
                )
                .findFirst()
                .orElse(null);

        if (encontrada == null) {
            System.out.println("Inscrição não encontrada.");
            return false;
        }

        encontrada.getAula().removerAluno();
        repository.remover(encontrada);

        System.out.println("Inscrição cancelada!");
        return true;
    }
}