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
        
        Aluno aluno1 = new Aluno("Marina","345543334554", "27032007","11911324567","maria@gmail.com", "sim" );
        Aluno aluno2 = new Aluno("Maria","34455334554", "27042007","1191455665467","mariass@gmail.com", "sim" );
        
        listaAlunos.add(aluno1);
        listaAlunos.add(aluno2);
        
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
                	Aluno.listarAlunos(listaAlunos);
                	break;
                case 3:
                	System.out.println("Atualizar aluno:");
                    Aluno.atualizarAluno(sc, listaAlunos);
                    break;
                case 4:
                	System.out.println("Excluir aluno:");
                    System.out.print("Digite o CPF: ");
                    String cpf = sc.nextLine();

                    Aluno.excluirAluno(listaAlunos, cpf);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }

    }

}
