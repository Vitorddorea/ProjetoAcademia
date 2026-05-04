package entities;

public class Plano {

    private String nome;
    private String descricao;
    private double valorMensal;
    private int duracaoMeses;
    private String beneficios;

    public Plano(String nome, String descricao, double valorMensal, int duracaoMeses, String beneficios) {
        setNome(nome);
        setDescricao(descricao);
        setValorMensal(valorMensal);
        setDuracaoMeses(duracaoMeses);
        setBeneficios(beneficios);
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

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        if (valorMensal < 0) {
            throw new IllegalArgumentException("Valor inválido!");
        }
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
    @Override
    public String toString() {
        return "Nome: " + nome +
                " | Descrição: " + descricao +
                " | Valor: R$" + valorMensal +
                " | Duração: " + duracaoMeses + " meses" +
                " | Benefícios: " + beneficios;
    }
}
