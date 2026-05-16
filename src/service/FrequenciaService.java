package service;

import entities.Aluno;
import entities.Aula;
import entities.Frequencia;
import repositories.FrequenciaDAO;

import java.util.List;

public class FrequenciaService {

    private final AlunoService alunoService;
    private final AulaService aulaService;
    private final InscricaoService inscricaoService;
    private final FrequenciaDAO repository;

    public FrequenciaService(AlunoService alunoService,
        AulaService aulaService,
        InscricaoService inscricaoService,
        FrequenciaDAO repository) {

        this.alunoService = alunoService;
        this.aulaService = aulaService;
        this.inscricaoService = inscricaoService;
        this.repository = repository;
    }

    public boolean registrar(String cpf, String nomeAula, boolean presente) {

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
        if (!estaInscrito(cpf, nomeAula)) {
            System.out.println("Aluno não está inscrito nessa aula.");
            return false;
        }
        if (repository.buscar(cpf, nomeAula) != null) {
            System.out.println("Frequência já registrada.");
            return false;
        }
        Frequencia frequencia = new Frequencia(aluno, aula);
        frequencia.registrarPresenca(presente);

        repository.salvar(frequencia);

        System.out.println("Presença registrada com sucesso.");
        return true;
    }
    public List<Frequencia> listar() {
        return repository.listar();
    }
    private boolean estaInscrito(String cpf, String nomeAula) {
        return inscricaoService.listar().stream()
            .anyMatch(i ->
            i.getAluno().getCpf().equals(cpf) &&
            i.getAula().getNome().equalsIgnoreCase(nomeAula)
        );
    }
}