package entities;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

public class Aluno extends Pessoa {

    private LocalDate dataNascimento;
    private String email;
    private LocalDate dataMatricula;
    private Plano planoAtivo;

    public Aluno(Long id, String nome, String cpf, String telefone, LocalDate dataNascimento, String email, LocalDate dataMatricula, Plano planoAtivo) {
        super(id, nome, cpf, telefone);
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.dataMatricula = LocalDate.now();
        this.planoAtivo = planoAtivo;
    }
    private List<Aula> aulas = new ArrayList<>();

    public Aluno(String nome, String cpf, LocalDate dataNascimento,
        String telefone, String email, Plano planoAtivo) {
        super(nome, cpf, telefone);
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.dataMatricula = LocalDate.now();
        this.planoAtivo = planoAtivo;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }
    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public void adicionarAula(Aula aula) {

        if (!aulas.contains(aula)) {
            aulas.add(aula);
        }
    }

    public void removerAula(Aula aula) {
        aulas.remove(aula);
    }

   @Override
public String toString() {
    return "Nome: " + nome +
            " | CPF: " + cpf +
            " | Email: " + email +
            " | Telefone: " + telefone +
            " | Plano: " + (planoAtivo != null ? planoAtivo.getNome() : "Sem plano ou Inativo") +
            " | Nascimento: " + dataNascimento +
            " | Data de Matrícula: " + dataMatricula;
}
}