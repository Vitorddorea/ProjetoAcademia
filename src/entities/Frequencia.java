package entities;

import java.time.LocalDateTime;

public class Frequencia {

    private String aluno; // mudar depois para tipo Aluno
    private LocalDateTime dataHora;
    private boolean presente;

    public Frequencia(String aluno) {
        this.aluno = aluno;
        this.dataHora = LocalDateTime.now();
        this.presente = false;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public boolean isPresente() {
        return presente;
    }

    public void registrarPresenca(String presenca) {
        if (presenca == null || presenca.trim().isEmpty()) {
            throw new IllegalArgumentException("Entrada inválida!");
        }

        char primeiraLetra = presenca.trim().toUpperCase().charAt(0);

        if (primeiraLetra == 'S') {
            presente = true;
        } else if (primeiraLetra == 'N') {
            presente = false;
        } else {
            throw new IllegalArgumentException("Digite apenas S ou N");
        }
    }

    @Override
    public String toString() {
        return "Aluno = '" + aluno + '\'' +
                "| Data e Hora = " + dataHora +
                "| Presente = " + presente +
                '|';
    }
}
