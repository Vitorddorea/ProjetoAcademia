package menus;

import entities.Aula;
import service.AulaService;
import util.Util;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class AulaMenu implements Menu{

    public ArrayList<Aula> listaAulas = new ArrayList<>();

    public AulaMenu() {

        Aula aula1 = new Aula("corrida", "correr em quadrados", 5, "anoite", 123, "marcos");

        listaAulas.add(aula1);

    }

    @Override
    public void exibir(Scanner sc) {

        Locale.setDefault(Locale.US);

        while (true){

            System.out.println("\n==== GERENCIAR AULA ====");
            System.out.println("1- Cadastrar nova aula");
            System.out.println("2- Listar aulas");
            System.out.println("3- Atualizar aula");
            System.out.println("4- Excluir aula");
            System.out.println("0- Voltar para o menu principal");

            System.out.println("Escolha uma opção: ");
            int opcao = Util.lerInteiro(sc);

            switch (opcao) {
                case 1:
                    System.out.println("==== Cadastrar Aula ====");
                    Aula aula = AulaService.cadastrarAula(sc);
                    listaAulas.add(aula);
                    break;
                case 2:
                    System.out.println("==== Listar Aulas ====");
                    //AulaService.listarAulas(sc,listaAulas);
                    break;
                case 3:
                    System.out.println("==== Atualizar aula ====");
                    //AulaService.atualizarPlano(sc, listaAulas);
                    break;
                case 4:
                    System.out.println("==== Excluir aula ====");
                    //AulaService.excluirPlano(sc, listaAulas);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }

    }
}
