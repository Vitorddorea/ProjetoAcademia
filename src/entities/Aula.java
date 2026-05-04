package entities;

public class Aula {

    private String nome;
    private String descricao;
    private int alunosInscritos;
    private int capacidadeMaxima;
    private String horario;  // conferir
    private int duracao;  // se for em minutos

    private Instrutor instrutor;

    public Aula(String nome, String descricao, int capacidadeMaxima, String horario, int duracao, Instrutor instrutor) {

        setNome(nome);
        setDescricao(descricao);
        alunosInscritos = 0;
        setCapacidadeMaxima(capacidadeMaxima);
        this.horario = horario;
        setDuracao(duracao);
        this.instrutor = instrutor;

    }

    public Aula(String nome, String horario, int duracao, int capacidadeMaxima, Instrutor instrutor) {
        setNome(nome);
        this.horario = horario;
        setDuracao(duracao);
        setCapacidadeMaxima(capacidadeMaxima);
        this.alunosInscritos = 0;
        this.instrutor = instrutor;
    }

	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição inválida!");
        }
        this.descricao = descricao;
    }

    public int getAlunosInscritos() {
        return alunosInscritos;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        if (capacidadeMaxima < 1 || capacidadeMaxima > 30) {
            throw new IllegalArgumentException(
                    "Capacidade deve estar entre 1 e 30."
            );
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

    public void adicionarAluno() {
        if (alunosInscritos >= capacidadeMaxima) {
            throw new IllegalStateException("Capacidade máxima atingida.");
        }
        alunosInscritos++;
    }

    public void removerAluno() {
        if (alunosInscritos <= 0) {
            throw new IllegalStateException("Nenhum aluno inscrito.");
        }
        alunosInscritos--;
    }


    @Override
    public String toString() {
        return "Nome: " + nome +
                " | Descrição: " + (descricao != null ? descricao : "Sem descrição") +
                " | Alunos: " + alunosInscritos + "/" + capacidadeMaxima +
                " | Horário: " + horario +
                " | Duração: " + duracao +
                " min | Instrutor: " + instrutor;
    }
}
