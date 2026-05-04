package application;

import menus.*;
import util.Util;

import service.InstrutorService;

import java.util.Locale;
import java.util.Scanner;


public class Academia {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Menu alunoMenu = new AlunoMenu();
        Menu instrutorMenu = new InstrutorMenu();
        Menu planoMenu = new PlanoMenu();
        Menu aulaMenu = new AulaMenu();
        Menu inscricaoMenu = new InscricaoMenu();
        Menu frequenciaMenu = new FrequenciaMenu();
        Menu relatorioMenu = new RelatorioMenu();

        boolean executando = true;
        while (executando) {

            System.out.println("\n======= SISTEMA ACADEMIA ========");
            System.out.println("1- Alunos");
            System.out.println("2- Instrutores");
            System.out.println("3- Planos");
            System.out.println("4- Aulas");
            System.out.println("5- Inscrições");
            System.out.println("6- Frequências");
            System.out.println("7- Relatórios");
            System.out.println("0- Encerrar programa");
            System.out.println("=================================");

            System.out.println("Escolha uma opção:");

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
