package application;

import menus.*;

import repositories.AlunoRepository;
import repositories.AulaRepository;
import repositories.InstrutorRepository;
import repositories.PlanoRepository;
import repositories.InscricaoRepository;
import repositories.FrequenciaRepository;



import service.AlunoService;
import service.AulaService;
import service.FrequenciaService;
import service.InstrutorService;
import service.PlanoService;
import service.InscricaoService;



import entities.Usuario;
import util.Util;

import java.util.Locale;
import java.util.Scanner;

public class Academia {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        //  REPOSITORIES
        AlunoRepository alunoRepository = new AlunoRepository();
        AulaRepository aulaRepository = new AulaRepository();
        InstrutorRepository instrutorRepository = new InstrutorRepository();
        InscricaoRepository inscricaoRepository = new InscricaoRepository();
        FrequenciaRepository frequenciaRepository = new FrequenciaRepository();
        PlanoRepository planoRepository = new PlanoRepository();
        //  SERVICES
        AlunoService alunoService = new AlunoService(alunoRepository);
        AulaService aulaService = new AulaService(aulaRepository);
        InstrutorService instrutorService = new InstrutorService(instrutorRepository);
        PlanoService planoService = new PlanoService(planoRepository);

    InscricaoService inscricaoService = new InscricaoService(inscricaoRepository, alunoService, aulaService);        FrequenciaService frequenciaService = new FrequenciaService(alunoService, aulaService, inscricaoService, frequenciaRepository);         
        //USUÁRIOS 
        Usuario usuario1 = new Usuario("Patricia", "GERENTE", 345);
        Usuario usuario2 = new Usuario("Marcio", "RECEPCIONISTA", 678);

        Usuario usuarioLogado = autenticarUsuario(sc, usuario1, usuario2);

        if (usuarioLogado == null) {
            System.out.println("Acesso negado!");
            sc.close();
            return;
        }

        //  MENUS 
        Menu alunoMenu = new AlunoMenu(usuarioLogado, alunoService, planoService);
        Menu aulaMenu = new AulaMenu(usuarioLogado, aulaService, instrutorService);
        Menu instrutorMenu = new InstrutorMenu(usuarioLogado, instrutorService);

        // menus ainda simples (sem service)
        Menu planoMenu = new PlanoMenu(planoService);
        Menu inscricaoMenu = new InscricaoMenu(usuarioLogado, inscricaoService);
        Menu frequenciaMenu = new FrequenciaMenu(usuarioLogado, frequenciaService);
        // Menu relatorioMenu = new RelatorioMenu();

        boolean executando = true;

        while (executando) {

            System.out.println("\n=== SISTEMA ACADEMIA ===");
            System.out.println("1- Alunos");
            System.out.println("2- Instrutores");
            System.out.println("3- Planos");
            System.out.println("4- Aulas");
            System.out.println("5- Inscrições");
            System.out.println("6- Frequências");
            System.out.println("7- Relatórios");
            System.out.println("0- Sair");

            int opcao = Util.lerInteiro(sc);
            Menu menuSelecionado = null;

            switch (opcao) {
                case 1: menuSelecionado = alunoMenu; break;
                case 2: menuSelecionado = instrutorMenu; break;
                case 3: menuSelecionado = planoMenu; break;
                case 4: menuSelecionado = aulaMenu; break;
                case 5: menuSelecionado = inscricaoMenu; break;
                case 6: menuSelecionado = frequenciaMenu; break;
               // case 7: menuSelecionado = relatorioMenu; break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            if (menuSelecionado != null) {
                menuSelecionado.exibir(sc);
            }
        }

        sc.close();
    }

    private static Usuario autenticarUsuario(Scanner sc, Usuario u1, Usuario u2) {

        System.out.println("\n*** LOGIN ***");
        System.out.print("Digite o código: ");
        String codigo = sc.nextLine();

        if (codigo.equals(String.valueOf(u1.getCodigo()))) {
            System.out.println("Bem-vindo(a) " + u1.getTipo() + " " + u1.getNome());
            return u1;
        }

        if (codigo.equals(String.valueOf(u2.getCodigo()))) {
            System.out.println("Bem-vindo(a) " + u2.getTipo() + " " + u2.getNome());
            return u2;
        }

        return null;
    }
}