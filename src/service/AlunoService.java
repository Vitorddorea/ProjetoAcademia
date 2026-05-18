/**
 * Serviço responsável pelo gerenciamento de alunos.
 * Realiza operações de cadastro, atualização, exclusão,
 * consulta e validação de planos ativos.
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
import repositories.AlunoDAO;

public class AlunoService {
    // criando depencia aluno repository
    private AlunoDAO repository;

    // construtor para receber a dependência do repositório
    public AlunoService(AlunoDAO repository) {
        this.repository = repository;
    }

    public java.util.List<Aluno> listarAlunos() {
        if (repository.listarAlunos().isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
          
        }
        return repository.listarAlunos();
    }

    public boolean cadastrarAluno(Aluno aluno) {

        if (repository.buscarAlunoPorCpf(aluno.getCpf()) != null) {
            return false;
        }

        return repository.adicionarAluno(aluno);
    }

    public boolean excluirAluno(String cpf) {
        return repository.removerAluno(cpf);
    }

    public boolean atualizarAluno(Aluno alunoAtualizado) {
        return repository.atualizarAluno(alunoAtualizado);
    }

    public Aluno buscarPorCpf(String cpf) {
    return repository.buscarAlunoPorCpf(cpf);
}

    public boolean planoAtivo(Aluno aluno) {

        if (aluno == null || aluno.getPlanoAtivo() == null) {
            return false;
        }

        return !java.time.LocalDate.now().isAfter(dataVencimento(aluno));
    }

    public java.time.LocalDate dataVencimento(Aluno aluno) {

        if (aluno == null || aluno.getPlanoAtivo() == null) {
            return null;
        }

        return aluno.getDataMatricula().plusMonths(aluno.getPlanoAtivo().getDuracaoMeses());
    }

    public void exibirDetalhesAluno(String cpf, InscricaoService inscricaoService) {

        Aluno aluno = repository.buscarAlunoPorCpf(cpf);

        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.println("\n===== DETALHES DO ALUNO =====");
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("CPF: " + aluno.getCpf());
        System.out.println("Telefone: " + aluno.getTelefone());
        System.out.println("Email: " + aluno.getEmail());
        System.out.println("Nascimento: " + aluno.getDataNascimento());
        System.out.println("Data matrícula: " + aluno.getDataMatricula());
        System.out.println();

        if (aluno.getPlanoAtivo() != null) {
            System.out.println("Plano: " + aluno.getPlanoAtivo().getNome());
            System.out.println("Status plano: " +
                    (planoAtivo(aluno) ? "ATIVO" : "VENCIDO"));
            System.out.println("Vencimento: " + dataVencimento(aluno));
        } else {
            System.out.println("Plano: Sem plano");
        }

        System.out.println("\nAulas inscritas:");

        boolean encontrou = false;

        for (var inscricao : inscricaoService.listar()) {
            if (inscricao.getAluno().getCpf().equals(cpf)) {
                System.out.println("- " + inscricao.getAula().getNome()
                                + " | "
                                + inscricao.getAula().getHorario());
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma aula inscrita.");
        }
    }
}
