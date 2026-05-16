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
}
