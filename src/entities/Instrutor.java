package entities;

public class Instrutor extends Pessoa {

    private String especialidade;
    private String horarioTrabalho;

    public Instrutor(String nome, String cpf, String telefone, String especialidade, String horarioTrabalho) {
        super(nome, cpf, telefone);
        this.especialidade = especialidade;
        this.horarioTrabalho = horarioTrabalho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("Nome inválido!");
        }
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getHorarioTrabalho() {
        return horarioTrabalho;
    }

    public void setHorarioTrabalho(String horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }

    public void mostrarInstrutor() {
        System.out.println("Instrutor: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Telefone: " + this.telefone);
        System.out.println("Especialidade: " + this.especialidade);
        System.out.println("Horário de trabalho: " + this.horarioTrabalho);
    }
    @Override
    public String toString() {
        return "Nome: " + nome +
               "\nCPF: " + cpf +
               "\nTelefone: " + telefone +
               "\nEspecialidade: " + especialidade +
               "\nHorário: " + horarioTrabalho +
               "\n-------------------------";
    }

}
