package menus;

import entities.Aluno;
import service.AlunoService;
import util.Util;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class AlunoMenu implements Menu{

    @Override
    public void exibir(Scanner sc){

        Locale.setDefault(Locale.US);

        while (true){
            System.out.println("\n======== GERENCIAR ALUNO ========");
            System.out.println("1- Cadastrar aluno");
            System.out.println("2- Listar alunos");
            System.out.println("3- Atualizar aluno");
            System.out.println("4- Excluir aluno");
            System.out.println("0- Voltar para o menu principal");
            System.out.println("=================================");

            System.out.println("Escolha uma opção: ");
            int opcao = Util.lerInteiro(sc);

            switch (opcao) {
                case 1:
                    System.out.println(" ==== Cadastrar Aluno ====");
                    AlunoService.cadastrarAluno(sc);
                    break;
                case 2:
                    System.out.println(" ==== Lista de alunos ==== ");
                	AlunoService.listarAlunos();
                	break;
                case 3:
                	System.out.println(" ==== Atualizar aluno ====");
                    AlunoService.atualizarAluno(sc);
                    break;
                case 4:
                	System.out.println(" ==== Excluir aluno ====");
                    AlunoService.excluirAluno(sc);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }

        }

    }

}
