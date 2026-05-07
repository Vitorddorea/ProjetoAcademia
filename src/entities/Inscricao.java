package entities;

public class Inscricao {

    private Aluno aluno;
    private Aula aula;

    public Inscricao(Aluno aluno, Aula aula) {
        this.aluno = aluno;
        this.aula = aula;
    }

    public Aluno getAluno() { return aluno; }
    public Aula getAula() { return aula; }

    @Override
    public String toString() {
        return "Aluno: " + aluno.getNome() +
               " | Aula: " + aula.getNome();
    }
}