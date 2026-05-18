/**
 * Serviço responsável pelo controle de frequência
 * dos alunos nas aulas da academia.
 *
 * Permite registrar presença, atualizar frequência,
 * excluir registros e gerar relatórios.
 *
 * @author Camila Bandeira de Oliveira
 * @author Gabriel Rodrigues Lopes
 * @author John Lucas Garcia dos Santos
 * @author Marina Pereira Marcelino
 * @author Marina Santos Morais
 * @author Vitor Daniel Dorea Santos
 *
 * @version 1.0
 * @since 2026-05-16
 */

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

    public void gerarRelatorioAluno(
            String cpf,
            java.time.LocalDate inicio,
            java.time.LocalDate fim
    ) {

        List<Frequencia> frequencias =
                repository.buscarPorAlunoPeriodo(cpf, inicio, fim);

        if (frequencias.isEmpty()) {
            System.out.println("Nenhuma frequência encontrada.");
            return;
        }

        System.out.println("\n===== RELATÓRIO DE FREQUÊNCIA =====");

        frequencias.forEach(System.out::println);

        System.out.println("\nTotal de visitas: " + repository.contarVisitas(cpf));

        System.out.println("Última visita: " + repository.buscarUltimaVisita(cpf));
    }

    public boolean atualizar(String cpf, String nomeAula, boolean presente) {

        boolean atualizado =
                repository.atualizar(cpf, nomeAula, presente);

        if (atualizado) {
            System.out.println("Frequência atualizada com sucesso.");
        } else {
            System.out.println("Frequência não encontrada.");
        }

        return atualizado;
    }

    public boolean excluir(String cpf, String nomeAula) {

        boolean removido =
                repository.remover(cpf, nomeAula);

        if (removido) {
            System.out.println("Frequência removida com sucesso.");
        } else {
            System.out.println("Frequência não encontrada.");
        }

        return removido;
    }
}