package entities;

public class Instrutor extends Pessoa {

    private String especialidade;
    private String horarioTrabalho;

    public Instrutor(String nome, String cpf, String telefone,
                     String especialidade, String horarioTrabalho) {
        super(nome, cpf, telefone);
        this.especialidade = especialidade;
        this.horarioTrabalho = horarioTrabalho;
    }

    public String getEspecialidade() { return especialidade; }
    public String getHorarioTrabalho() { return horarioTrabalho; }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setHorarioTrabalho(String horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }

    @Override
    public String toString() {
        return "\nNome: " + getNome() + " | " + "Especialidade: " + especialidade + " | " + "Horário de Trabalho: " + horarioTrabalho;
    }
}