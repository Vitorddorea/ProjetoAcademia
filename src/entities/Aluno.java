package entities;

import java.time.LocalDate;

public class Aluno extends Pessoa {

    private String dataNascimento; // confirmar
    private String email;
    private LocalDate dataMatricula;
    private Plano planoAtivo;

    public Aluno(String nome, String cpf, String dataNascimento, String telefone, String email) {
        super(nome, cpf, telefone);
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.dataMatricula = LocalDate.now();
        //this.planoAtivo = planoAtivo;
    }

    public Plano getPlanoAtivo() {
        return planoAtivo;
    }

    public void setPlanoAtivo(Plano planoAtivo) {
        this.planoAtivo = planoAtivo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Email: " + email +
                " | Plano: " + planoAtivo.getNome() +
                " | Data Nascimento: " + dataNascimento +
                " | Data Matricula: " + dataMatricula;
    }
}