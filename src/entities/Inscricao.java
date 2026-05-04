package entities;

import java.time.LocalDate;

public class Inscricao {

    private Aluno aluno;
    private Aula aula;
    private LocalDate dataInscricao;

    public Inscricao(Aluno aluno, Aula aula) {
        this.dataInscricao = LocalDate.now();
        this.aluno = aluno;
        this.aula = aula;
    }

    public LocalDate getDataInscricao() {
        return dataInscricao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    @Override
    public String toString() {
        return "Aluno: " + aluno.getNome() +
                " | Aula: " + aula.getNome() +
                " | Data da Inscrição: " + dataInscricao;
    }
}
