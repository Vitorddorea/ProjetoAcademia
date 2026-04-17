package entities;

public class Aula {

    private String nome;
    private String descricao;
    private int capacidadeMaxima;
    private String horario;  // conferir
    private int duracao;  // se for em minutos
    private String instrutor;

    private Aula(String nome, String descricao, int capacidadeMaxima, String horario, int duracao, String instrutor) {
        this.nome = nome;
        this.descricao = descricao;
        this.capacidadeMaxima = capacidadeMaxima;
        this.horario = horario;
        this.duracao = duracao;
        this.instrutor = instrutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }
}
