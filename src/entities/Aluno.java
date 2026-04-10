package entities;

public class Aluno extends Pessoa{

    String dataNascimento; // confirmar
    String email;
    String dataMatricula;
    String planoAtivo;

    private Aluno(String nome, String cpf, String dataNascimento, String telefone, String email, String dataMatricula, String planoAtivo) {
        super(nome, cpf, telefone);
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.dataMatricula = dataMatricula;
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

    public String getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(String dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public String getPlanoAtivo() {
        return planoAtivo;
    }

    public void setPlanoAtivo(String planoAtivo) {
        this.planoAtivo = planoAtivo;
    }

}
