package menus;

 import service.AlunoService;
import util.Util;

import java.util.Locale;
import java.util.Scanner;

import entities.Usuario;

public class AlunoMenu implements Menu{
    
    private Usuario usuario;

    //construtor para receber o usuário logado
    public AlunoMenu(Usuario usuario) {
        this.usuario = usuario;
    }

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
                    if (usuario.getTipo().equalsIgnoreCase("GERENTE")|| usuario.getTipo().equalsIgnoreCase("RECEPCIONISTA")) {
                        System.out.println("==== Cadastrar Aluno ====");
                        AlunoService.cadastrarAluno(sc, usuario);
                    } else {
                        System.out.println("Acesso negado!");
                    }
                    break;
                case 2:
                    System.out.println(" ==== Lista de alunos ==== ");
                	AlunoService.listarAlunos();
                	break;
                case 3:
                     if (usuario.getTipo().equalsIgnoreCase("GERENTE")|| usuario.getTipo().equalsIgnoreCase("RECEPCIONISTA")) {
                        System.out.println(" ==== Atualizar aluno ====");
                        AlunoService.atualizarAluno(sc);
                    } else {
                        System.out.println("Acesso negado!");
                    }
                    break;
                case 4:
                     if (usuario.getTipo().equalsIgnoreCase("GERENTE")|| usuario.getTipo().equalsIgnoreCase("RECEPCIONISTA")) {
                        System.out.println(" ==== Excluir aluno ====");
                        AlunoService.excluirAluno(sc);
                    } else {
                        System.out.println("Acesso negado!");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }

        }

    }

}
