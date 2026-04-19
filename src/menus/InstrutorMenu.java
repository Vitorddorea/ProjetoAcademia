package menus;

import entities.Instrutor;
import service.InstrutorService;
import util.Util;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class InstrutorMenu implements Menu {
    private ArrayList<Instrutor> listaInstrutores = new ArrayList<>();

    public InstrutorMenu(){
        Instrutor instrutor1 = new Instrutor("Marina","345543334554", "27032007","areobica","19:00");
        Instrutor instrutor2 = new Instrutor("Maria","34455334554", "27042007","luta","18:00" );
        listaInstrutores.add(instrutor1);
        listaInstrutores.add(instrutor2);
    }
    @Override
    public void exibir(Scanner sc) {

        Locale.setDefault(Locale.US);

        while (true){
            System.out.println("\n==== GERENCIAR INSTRUTOR ====");
            System.out.println("1- Cadastrar Instrutor");
            System.out.println("2- Listar instrutores");
            System.out.println("3- Atualizar instrutor");
            System.out.println("4- Excluir instrutor");
            System.out.println("0- Voltar para o menu principal");

            System.out.println("Escolha uma opção: ");
            int opcao = Util.lerInteiro(sc);

            switch (opcao) {
                case 1:
                    Instrutor instrutor = InstrutorService.cadastrarInstrutor(sc);
                    listaInstrutores.add(instrutor);
                    break;
                case 2:
                    //Instrutor.listarInstrutores(listaInstrutores);
                    break;
                case 3:
                    System.out.println("Atualizar instrutor:");
                    //Instrutor.atualizarInstrutor(sc, listaInstrutores);
                    break;
                case 4:
                    System.out.println("Excluir aluno:");
                    System.out.print("Digite o CPF: ");
                    //String cpf = sc.nextLine();

                    //Instrutor.excluirInstrutor(listaInstrutores, cpf);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
