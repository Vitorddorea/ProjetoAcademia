package menus;

import entities.Usuario;
import service.UsuarioService;
import exceptions.EntradaException;

import java.util.Scanner;

public class LoginMenu {

    private UsuarioService service;

    public LoginMenu(UsuarioService service) {
        this.service = service;
    }

    public Usuario fazerLogin(Scanner sc) {

        System.out.println("===== LOGIN =====");

        System.out.print("Usuário: ");
        String nome = EntradaException.lerTexto(sc);

        System.out.print("Senha: ");
        String senha = EntradaException.lerTexto(sc);

        Usuario usuario = service.login(nome, senha);

        if (usuario == null) {
            System.out.println("Login inválido.");
            return null;
        }

        System.out.println("Login realizado com sucesso!");
        return usuario;
    }
}