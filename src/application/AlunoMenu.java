package application;

import entities.Aluno;
import util.AlunoService;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class AlunoMenu {
    public static void exibirMenu(Scanner sc, ArrayList<Aluno> lista){

        Locale.setDefault(Locale.US);
        ArrayList<Aluno> listaAlunos = new ArrayList<>();

        while (true){
            System.out.println("\n==== GERENCIAR ALUNO ====");
            System.out.println("1- Cadastrar aluno");
            System.out.println("2- Listar alunos");
            System.out.println("3- Atualizar aluno");
            System.out.println("4- Excluir aluno");
            System.out.println("0- Voltar para o menu principal");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    Aluno aluno = AlunoService.cadastrarAluno(sc);
                    listaAlunos.add(aluno);
                    break;
                case 2:
                    System.out.println("Lista de alunos ");
                case 3:
                    System.out.println("Atualizar aluno");
                    break;
                case 4:
                    System.out.println("Excluir aluno");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }

    }

}
