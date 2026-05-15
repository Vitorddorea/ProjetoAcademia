package entities;

import java.time.LocalDateTime;

public class Frequencia {

    private Long id;
    private Aluno aluno;
    private Aula aula;
    private LocalDateTime dataHora;
    private boolean presente;

    public Frequencia(Long id, Aluno aluno, Aula aula, LocalDateTime dataHora, boolean presente) {
        this.id = id;
        this.aluno = aluno;
        this.aula = aula;
        this.dataHora = LocalDateTime.now();
        this.presente = false;
    }

    public Frequencia(Aluno aluno, Aula aula) {
        this.aluno = aluno;
        this.aula = aula;
        this.dataHora = LocalDateTime.now();
        this.presente = false;
    }

    public Long getId() { return id; }

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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public boolean isPresente() {
        return presente;
    }

    public void registrarPresenca(boolean presente) {
        this.presente = presente;
    }

    @Override
    public String toString() {
        return "Aluno: " + aluno.getNome()
                + " | Aula: " + aula.getNome()
                + " | Data e Hora: " + dataHora
                + " | Presente: " + presente;
    }
}