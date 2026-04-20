package menus;

import entities.Aluno;
import service.AlunoService;
import util.Util;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class AlunoMenu implements Menu{
    private ArrayList<Aluno> listaAlunos = new ArrayList<> ();
    
    public AlunoMenu(){
        Locale.setDefault(Locale.US);
        ArrayList<Aluno> listaAlunos = new ArrayList<>();
        
        // foi feita a criação apenas para testar as funções aluno Menu!
        Aluno aluno1 = new Aluno("Joana","345543334554", "01032020","11911324567","joana@gmail.com", "sim" );
        Aluno aluno2 = new Aluno("Maria","34455334554", "27042012","1191455665467","mariass@gmail.com", "sim" );
        
        listaAlunos.add(aluno1);
        listaAlunos.add(aluno2);
    }

    @Override
    public void exibir(Scanner sc){

        Locale.setDefault(Locale.US);

        while (true){
            System.out.println("\n==== GERENCIAR ALUNO ====");
            System.out.println("1- Cadastrar aluno");
            System.out.println("2- Listar alunos");
            System.out.println("3- Atualizar aluno");
            System.out.println("4- Excluir aluno");
            System.out.println("0- Voltar para o menu principal");

            System.out.println("Escolha uma opção: ");
            int opcao = Util.lerInteiro(sc);

            switch (opcao) {
                case 1:
                    Aluno aluno = AlunoService.cadastrarAluno(sc);
                    listaAlunos.add(aluno);
                    break;
                case 2:
                	AlunoService.listarAlunos(listaAlunos);
                	break;
                case 3:
                	System.out.println("Atualizar aluno:");
                    AlunoService.atualizarAluno(sc, listaAlunos);
                    break;
                case 4:
                	System.out.println("Excluir aluno:");
                    System.out.print("Digite o CPF: ");
                    String cpf = sc.nextLine();

                    AlunoService.excluirAluno(listaAlunos, cpf);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }

        }

    }

}
