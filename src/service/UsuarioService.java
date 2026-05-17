package service;

import entities.Usuario;
import repositories.UsuarioDAO;

import java.util.List;

public class UsuarioService {

    private UsuarioDAO repository;

    public UsuarioService(UsuarioDAO repository) {
        this.repository = repository;
    }

    public Usuario login(String nome, String senha) {
        return repository.autenticar(nome, senha);
    }

    public boolean cadastrar(Usuario usuario) {

        if (repository.buscarPorNome(usuario.getNome()) != null) {
            return false;
        }

        repository.salvar(usuario);
        return true;
    }

    public List<Usuario> listar() {
        return repository.listar();
    }

    public boolean excluir(String nome) {

        Usuario usuario = repository.buscarPorNome(nome);

        if (usuario == null) {
            return false;
        }

        repository.remover(usuario);
        return true;
    }

    public Usuario buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }
}