package entities;

public class Aula {

    private String nome;
    private String descricao;
    private int capacidadeMaxima;
    private String horario;  // conferir
    private int duracao;  // se for em minutos

    private Instrutor instrutor; // mudar para o tipo instrutor

      public Aula(String nome, String descricao, int capacidadeMaxima, String horario, int duracao, Instrutor instrutor) {

        this.nome = nome;
        this.descricao = descricao;
        setCapacidadeMaxima(capacidadeMaxima);
        this.horario = horario;
        setDuracao(duracao);
        this.instrutor = instrutor;
      }

    public Aula(String nome, String horario, int duracao, int capacidadeMaxima, Instrutor instrutor) {
        this.nome = nome;
        this.horario = horario;
        this.duracao = duracao;
        this.capacidadeMaxima = capacidadeMaxima;
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
        if (capacidadeMaxima < 0 ) {
            throw new IllegalArgumentException("Valor inválido!");
        }
        if (capacidadeMaxima < 1  || capacidadeMaxima > 30) {
            throw new IllegalArgumentException("Capacidade inválida!");
        }
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
        if (duracao <= 0) {
            throw new IllegalArgumentException("Valor inválido!");
        }
        this.duracao = duracao;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                " | Descrição: " + descricao +
                " | Capacidade Máxima: " + capacidadeMaxima +
                " | Horário: " + horario +
                " | Duração: " + duracao +
                " | Instrutor: " + instrutor ;
    }
}
