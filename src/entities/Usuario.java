package entities;

public class Usuario {

    private String nome;
    private String tipo; // gerente || recepcionista
    private int codigo;

    public Usuario(String nome, String tipo, int codigo) {
        this.nome = nome;
        this.tipo = tipo;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
