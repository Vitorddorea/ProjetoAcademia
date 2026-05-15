package entities;

public class Inscricao {

    private Long id;
    private Aluno aluno;
    private Aula aula;

    public Inscricao(Long id, Aluno aluno, Aula aula) {
        this.id = id;
        this.aluno = aluno;
        this.aula = aula;
    }

    public Inscricao(Aluno aluno, Aula aula) {
        this.aluno = aluno;
        this.aula = aula;
    }

    public Long getId() { return id; }
    public Aluno getAluno() { return aluno; }
    public Aula getAula() { return aula; }

    @Override
    public String toString() {
        return "Aluno: " + aluno.getNome() +
               " | Aula: " + aula.getNome();
    }
}