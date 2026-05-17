package menus;

import entities.TipoUsuario;
import entities.Usuario;
import service.UsuarioService;
import exceptions.EntradaException;

import java.util.Scanner;

public class UsuarioMenu implements Menu {

    private UsuarioService service;

    public UsuarioMenu(UsuarioService service) {
        this.service = service;
    }

    @Override
    public void exibir(Scanner sc) {

        while (true) {

            System.out.println("\n========= GERENCIAR USUÁRIOS ==========");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Excluir usuário");
            System.out.println("0 - Voltar");
            System.out.println("=======================================");
            System.out.println("Escolha uma opção:");

            int opcao = EntradaException.lerInteiro(sc);

            switch (opcao) {
                case 1:
                    cadastrar(sc);
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    excluir(sc);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrar(Scanner sc) {

        System.out.print("Nome: ");
        String nome = EntradaException.lerTexto(sc);

        System.out.print("Senha: ");
        String senha = EntradaException.lerTexto(sc);

        System.out.println("Tipo:");
        System.out.println("1 - ADMINISTRADOR");
        System.out.println("2 - FUNCIONARIO");

        int opcaoTipo = EntradaException.lerInteiro(sc);

        TipoUsuario tipo;

        if (opcaoTipo == 1) {
            tipo = TipoUsuario.ADMINISTRADOR;
        } else {
            tipo = TipoUsuario.FUNCIONARIO;
        }

        Usuario usuario = new Usuario(nome, senha, tipo);

        if (service.cadastrar(usuario)) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Usuário já existe.");
        }
    }

    private void listar() {
        System.out.println("\n===== USUÁRIOS =====");

        service.listar().forEach(System.out::println);
    }

    private void excluir(Scanner sc) {

        System.out.print("Nome do usuário: ");
        String nome = EntradaException.lerTexto(sc);

        if (service.excluir(nome)) {
            System.out.println("Usuário removido!");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
}