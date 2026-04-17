package application;

import entities.Aluno;
import util.AlunoService;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Academia {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        ArrayList<Aluno> listaAlunos = new ArrayList<>();

        while (true) {
            System.out.println("\n==== SISTEMA ACADEMIA ====");
            System.out.println("1- Alunos");
            System.out.println("2- Instrutores");
            System.out.println("3- Planos");
            System.out.println("4- Aulas");
            System.out.println("5- Registro de frequência");
            System.out.println("0- Encerrar programa");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    AlunoMenu.exibirMenu(sc, listaAlunos);
                    break;
                case 2:
                    System.out.println("menu instrutores");
                    break;
                case 3:
                    System.out.println("menu planos");
                    break;
                case 4:
                    System.out.println("menu aulas");
                    break;
                case 5:
                    System.out.println("menu frequência");
                    break;
                case 0:
                    System.out.println("Encerrando programa...");
                    break;
                default:
                    System.out.println("Opção invalída!");
            }

        }




    }

}
