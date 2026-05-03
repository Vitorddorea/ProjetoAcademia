package menus;

import entities.Aula;
import service.AulaService;
import entities.Instrutor;
import service.InstrutorService;
import menus.InstrutorMenu;

import util.Util;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class AulaMenu implements Menu{

    public AulaMenu() {

        //Aula aula1 = new Aula("corrida", "correr em quadrados", 5, "anoite", 123, "marcos");

        //listaAulas.add(aula1);
    }
    @Override
    public void exibir(Scanner sc) {

        while (true) {
            System.out.println("\n======== GERENCIAR AULA =========");
            System.out.println("1- Cadastrar Aula");
            System.out.println("2- Listar Aula");
            System.out.println("3- Atualizar aula");
            System.out.println("4- Excluir aula");
            System.out.println("0- Voltar");
            System.out.println("=================================");

            int opcao = Util.lerInteiro(sc);

            switch (opcao) {
                case 1:
                    System.out.println(" ==== CADASTRAR AULA ====");
                    Aula aula = AulaService.cadastrarAula(sc);
                    break;
                case 2:
                    System.out.println(" ==== Lista de aulas ====");
                    AulaService.listarAulas();
                    break;
    			case 3:
                    System.out.println(" ==== Atualizar aula ====");
    				AulaService.atualizarAula(sc);
    				break;
    			case 4:
    				
    				System.out.println(" ==== Excluir aula ====");
    				AulaService.excluirAula(sc);
    				break;
    			case 0:
    				return;
    			default:
    				System.out.println("Opção inválida!");
    		}
    	}
    
   }
}
