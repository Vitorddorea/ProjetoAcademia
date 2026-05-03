package menus;


import service.InstrutorService;
import util.Util;


import java.util.Locale;
import java.util.Scanner;

public class InstrutorMenu implements Menu {

    //criando uma depencia do service.
    private InstrutorService service;

    //injeções de dependencias
    public InstrutorMenu(InstrutorService service) {
        this.service = service;
    }

    @Override
    public void exibir(Scanner sc) {
        Locale.setDefault(Locale.US);

        while (true){
            System.out.println("\n====== GERENCIAR INSTRUTOR ======");
            System.out.println("1- Cadastrar Instrutor");
            System.out.println("2- Listar instrutores");
            System.out.println("3- Atualizar instrutor");
            System.out.println("4- Excluir instrutor");
            System.out.println("0- Voltar para o menu principal");
            System.out.println("=================================");

            System.out.println("Escolha uma opção: ");
            int opcao = Util.lerInteiro(sc);

            switch (opcao) {
                case 1:
                    System.out.println(" ==== Cadastrar Instrutor ====");
                    boolean result = service.cadastrarInstrutor(sc);
                    break;
                case 2:
                    System.out.println(" ==== Lista de Instrutores ====");
                    boolean result2= service.listarInstrutores();
                    break;
                case 3:
                    System.out.println(" ==== Atualizar aluno ====");
                    boolean result3 = service.atualizarInstrutor(sc);
                    break;
                case 4:
                    System.out.println(" ==== Excluir aluno ====");
                	boolean result4 = service.excluirInstrutor(sc);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
