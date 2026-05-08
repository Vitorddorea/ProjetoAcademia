package service;

import entities.Aluno;
import repositories.AlunoRepository;

public class AlunoService {
    // criando depencia aluno repository
    private AlunoRepository repository; 

    // construtor para receber a dependência do repositório
    public AlunoService(AlunoRepository repository) {
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

    repository.adicionarAluno(aluno);

    return true;

}

    public boolean excluirAluno(String cpf) {
        Aluno aluno = repository.buscarAlunoPorCpf(cpf);

        if (aluno == null) {
            return false;
        }

        repository.removerAluno(aluno);
        return true;
    }
    public boolean atualizarAluno(Aluno alunoAtualizado) {
        Aluno existente = repository.buscarAlunoPorCpf(alunoAtualizado.getCpf());

        if (existente == null) {
            return false;
        }

        existente.setNome(alunoAtualizado.getNome());
        existente.setTelefone(alunoAtualizado.getTelefone());
        existente.setEmail(alunoAtualizado.getEmail());
        existente.setDataNascimento(alunoAtualizado.getDataNascimento());
        existente.setPlanoAtivo(alunoAtualizado.getPlanoAtivo());

        return true;
    }
    public Aluno buscarPorCpf(String cpf) {
    return repository.buscarAlunoPorCpf(cpf);
}
}
