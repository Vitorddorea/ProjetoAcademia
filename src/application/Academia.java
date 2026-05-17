package application;

import menus.*;
import repositories.*;
import service.*;
import entities.TipoUsuario;
import entities.Usuario;
import exceptions.EntradaException;

import java.util.Locale;
import java.util.Scanner;

public class Academia {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // DAO / REPOSITORIES
        AlunoDAO alunoDAO = new AlunoDAO();
        AulaDAO aulaDAO = new AulaDAO();
        InstrutorDAO instrutorDAO = new InstrutorDAO();
        InscricaoDAO inscricaoDAO = new InscricaoDAO();
        FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
        PlanoDAO planoDAO = new PlanoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // SERVICES
        AlunoService alunoService = new AlunoService(alunoDAO);
        AulaService aulaService = new AulaService(aulaDAO);
        InstrutorService instrutorService = new InstrutorService(instrutorDAO);
        PlanoService planoService = new PlanoService(planoDAO);
        InscricaoService inscricaoService = new InscricaoService(inscricaoDAO, alunoService, aulaService);
        FrequenciaService frequenciaService = new FrequenciaService(alunoService, aulaService, inscricaoService, frequenciaDAO);
        UsuarioService usuarioService = new UsuarioService(usuarioDAO);

        // LOGIN
        Usuario usuarioLogado = null;

        while (usuarioLogado == null) {
            usuarioLogado = autenticarUsuario(sc, usuarioService);

            if (usuarioLogado == null) {
                System.out.println("Usuário ou senha inválidos. Tente novamente.\n");
            }
        }

        // MENUS
        Menu alunoMenu = new AlunoMenu(usuarioLogado, alunoService, planoService, inscricaoService);
        Menu aulaMenu = new AulaMenu(usuarioLogado, aulaService, instrutorService, alunoService, inscricaoService);
        Menu instrutorMenu = new InstrutorMenu(usuarioLogado, instrutorService);
        Menu planoMenu = new PlanoMenu(planoService);
        Menu inscricaoMenu = new InscricaoMenu(usuarioLogado, inscricaoService);
        Menu frequenciaMenu = new FrequenciaMenu(usuarioLogado, frequenciaService);
        Menu relatorioMenu = new RelatorioMenu(alunoService, aulaService);
        Menu usuarioMenu = new UsuarioMenu(usuarioService);

        boolean executando = true;

        while (executando) {
            System.out.println("\n========== SISTEMA ACADEMIA ===========");

            if (usuarioLogado.getTipo() == TipoUsuario.ADMINISTRADOR) {
                System.out.println("1 - Alunos");
                System.out.println("2 - Instrutores");
                System.out.println("3 - Planos");
                System.out.println("4 - Aulas");
                System.out.println("5 - Inscrições");
                System.out.println("6 - Frequências");
                System.out.println("7 - Relatórios");
                System.out.println("8 - Usuários");
                System.out.println("0 - Sair");
            } else {
                System.out.println("1 - Alunos");
                System.out.println("2 - Aulas");
                System.out.println("3 - Inscrições");
                System.out.println("4 - Frequências");
                System.out.println("5 - Relatórios");
                System.out.println("0 - Sair");
            }

            System.out.println("=======================================");
            System.out.println("Escolha uma opção: ");

            int opcao = EntradaException.lerInteiro(sc);
            Menu menuSelecionado = null;

            if (usuarioLogado.getTipo() == TipoUsuario.ADMINISTRADOR) {
                switch (opcao) {
                    case 1 -> menuSelecionado = alunoMenu;
                    case 2 -> menuSelecionado = instrutorMenu;
                    case 3 -> menuSelecionado = planoMenu;
                    case 4 -> menuSelecionado = aulaMenu;
                    case 5 -> menuSelecionado = inscricaoMenu;
                    case 6 -> menuSelecionado = frequenciaMenu;
                    case 7 -> menuSelecionado = relatorioMenu;
                    case 8 -> menuSelecionado = usuarioMenu;
                    case 0 -> {
                        executando = false;
                        System.out.println("Encerrando sistema...");
                    }
                    default -> System.out.println("Opção inválida!");
                }
            } else {
                switch (opcao) {
                    case 1 -> menuSelecionado = alunoMenu;
                    case 2 -> menuSelecionado = aulaMenu;
                    case 3 -> menuSelecionado = inscricaoMenu;
                    case 4 -> menuSelecionado = frequenciaMenu;
                    case 5 -> menuSelecionado = relatorioMenu;
                    case 0 -> {
                        executando = false;
                        System.out.println("Encerrando sistema...");
                    }
                    default -> System.out.println("Opção inválida!");
                }
            }

            if (menuSelecionado != null) {
                menuSelecionado.exibir(sc);
            }
        }
    }

    // Método de login usando UsuarioService
    private static Usuario autenticarUsuario(Scanner sc, UsuarioService usuarioService) {
        System.out.println("\n========= LOGIN =========");

        System.out.print("Usuário: ");
        String nome = EntradaException.lerTexto(sc);

        System.out.print("Senha: ");
        String senha = EntradaException.lerTexto(sc);

        return usuarioService.login(nome, senha);
    }
}
