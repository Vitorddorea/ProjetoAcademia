package entities;

public class Plano {

    private String nome;
    private String descricao;
    private float valorMensal;
    private int duracaoMeses;
    private String beneficios;

    public Plano(String nome, String descricao, float valorMensal, int duracaoMeses, String beneficios) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorMensal = valorMensal;
        setDuracaoMeses(duracaoMeses);
        this.beneficios = beneficios;
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

    public float getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(float valorMensal) {
        this.valorMensal = valorMensal;
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        if (duracaoMeses < 1 || duracaoMeses > 12) {
            throw new IllegalArgumentException("Duração deve ser entre 1 e 12 meses");
        }else {
            this.duracaoMeses = duracaoMeses;
        }

    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

}
