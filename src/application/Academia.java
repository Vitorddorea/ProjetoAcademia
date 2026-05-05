package application;

import menus.*;
import util.Util;

import entities.Usuario;
import java.util.Locale;
import java.util.Scanner;


public class Academia {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Usuario usuario = new Usuario("Patricia","Gerente", 345);
        Usuario usuario2 = new Usuario("Marcio","Recepcionista", 678    );

        Usuario usuarioLogado = null;

        System.out.println(" ");
        System.out.println("    ****  VERIFICAÇÃO DE USUÁRIO  ***   ");
        System.out.print("Digite o código do usuário: ");

        String codigo = sc.nextLine();

        if(codigo.equals(String.valueOf(usuario.getCodigo()))) {

            usuarioLogado = usuario;

        } else if(codigo.equals(String.valueOf(usuario2.getCodigo()))) {

            usuarioLogado = usuario2;

        } else {

            System.out.println("Usuário não encontrado.");
            sc.close();
            return;
        }

        Menu alunoMenu = new AlunoMenu(usuarioLogado);
        Menu instrutorMenu = new InstrutorMenu();
        Menu planoMenu = new PlanoMenu();
        Menu aulaMenu = new AulaMenu();
        Menu inscricaoMenu = new InscricaoMenu();
        Menu frequenciaMenu = new FrequenciaMenu();
        Menu relatorioMenu = new RelatorioMenu();

        boolean executando = true;
        while (executando) {

            System.out.println("\n - SISTEMA ACADEMIA        ");
            System.out.println(" ");
            System.out.println("1- Alunos |2- Instrutores |3- Planos |4- Aulas |5- Inscrições |6- Frequências |7- Relatórios |0- Encerrar Programa");
            System.out.println(" ");

            System.out.println("O que você deseja gerenciar hoje? ");
            System.out.print("Escolha uma opção: ");

            int opcao = Util.lerInteiro(sc);

            Menu menu = null;

            switch (opcao){
                case 1:
                    menu = alunoMenu;
                    break;
                case 2:
                    menu = instrutorMenu;
                    break;
                case 3:
                    menu = planoMenu;
                    break;
                case 4:
                    menu = aulaMenu;
                    break;
                case 5:
                    menu = inscricaoMenu;
                    break;
                case 6:
                    menu = frequenciaMenu;
                    break;
                case 7:
                    menu = relatorioMenu;
                    break;
                case 0:
                    System.out.println("Encerrando programa...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção invalída!");
            }

            if (menu != null) {
                menu.exibir(sc);
            }

        }
        sc.close();
    }

}
