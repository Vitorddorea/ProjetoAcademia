package entities;

import java.time.LocalDate;

public class Aluno extends Pessoa{

    private String dataNascimento; // confirmar
    private String email;
    private LocalDate dataMatricula;
    private String planoAtivo;

    public Aluno(String nome, String cpf, String dataNascimento, String telefone, String email,  String planoAtivo) {
        super(nome, cpf, telefone);
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.dataMatricula = LocalDate.now();
        this.planoAtivo = planoAtivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public String getPlanoAtivo() {
        return planoAtivo;
    }

    public void setPlanoAtivo(String planoAtivo) {
        this.planoAtivo = planoAtivo;
    }

    public void mostrarAluno(){
        System.out.println("Aluno: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Data de Nascimento: " + this.dataNascimento);
        System.out.println("Telefone: " + this.telefone);
        System.out.println("E-mail: " + this.email);
        System.out.println("Plano ativo: " + this.planoAtivo);
    }

}
