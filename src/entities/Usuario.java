package entities;

public class Usuario {

    private Long id;
    private String nome;
    private String senha;
    private TipoUsuario tipo;

    public Usuario(Long id, String nome, String senha, TipoUsuario tipo) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Usuario(String nome, String senha, TipoUsuario tipo) {
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return nome + " - " + tipo;
    }
}