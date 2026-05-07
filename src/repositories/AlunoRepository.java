package repositories;

import entities.Aluno;
import java.util.ArrayList;
import java.util.List; 

public class AlunoRepository {
    private List<Aluno> alunos = new ArrayList<>();
    
    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> listarAlunos() {
        return new ArrayList<>(alunos); 
    }

    public Aluno buscarAlunoPorCpf(String cpf){
        for (Aluno a : alunos ){
            if(a.getCpf().equals(cpf)){
                return a;
            }
        }
        return null;
    }

    public void removerAluno(Aluno aluno){
        alunos.remove(aluno);
    }
}
