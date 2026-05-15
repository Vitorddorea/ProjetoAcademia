package entities;

public class Aula {

    private Long id;
    private String nome;
    private String horario;
    private int duracao;
    private int capacidadeMaxima;
    private int alunosInscritos;
    private Instrutor instrutor;

    public Aula(Long id, String nome, String horario, int duracao, int capacidadeMaxima, int alunosInscritos, Instrutor instrutor) {
        this.id = id;
        this.nome = nome;
        this.horario = horario;
        this.duracao = duracao;
        this.capacidadeMaxima = capacidadeMaxima;
        this.alunosInscritos = 0;
        this.instrutor = instrutor;
    }

    public Aula(String nome, String horario, int duracao, int capacidadeMaxima, Instrutor instrutor) {
        this.nome = nome;
        this.horario = horario;
        this.duracao = duracao;
        this.capacidadeMaxima = capacidadeMaxima;
        this.alunosInscritos = 0;
        this.instrutor = instrutor;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getHorario() { return horario; }
    public int getDuracao() { return duracao; }
    public int getCapacidadeMaxima() { return capacidadeMaxima; }
    public int getAlunosInscritos() { return alunosInscritos; }
    public Instrutor getInstrutor() { return instrutor; }

    public void setHorario(String horario) { this.horario = horario; }
    public void setDuracao(int duracao) { this.duracao = duracao; }
    public void setCapacidadeMaxima(int capacidadeMaxima) { this.capacidadeMaxima = capacidadeMaxima; }
    public void setInstrutor(Instrutor instrutor) { this.instrutor = instrutor; }

    public void adicionarAluno() {
        if (alunosInscritos >= capacidadeMaxima) {
            throw new IllegalStateException("Capacidade máxima atingida.");
        }
        alunosInscritos++;
    }

    public void removerAluno() {
        if (alunosInscritos > 0) {
            alunosInscritos--;
        }
    }

    @Override
    public String toString() {
        return "Aula: " + nome +
                " | Horário: " + horario +
                " | Instrutor: " + instrutor.getNome();
    }
}