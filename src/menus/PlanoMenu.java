package menus;

import entities.Plano;
import service.PlanoService;
import util.Util;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class PlanoMenu implements Menu{
    
    @Override
    public void exibir(Scanner sc) {

        Locale.setDefault(Locale.US);

        while (true){

            System.out.println("\n==== GERENCIAR PLANO ====");
            System.out.println("1- Cadastrar novo plano");
            System.out.println("2- Listar planos");
            System.out.println("3- Atualizar plano");
            System.out.println("4- Excluir plano");
            System.out.println("0- Voltar para o menu principal");

            System.out.println("Escolha uma opção: ");
            int opcao = Util.lerInteiro(sc);

            switch (opcao) {
                case 1:
                	System.out.println("==== Cadastrar Plano ====");
                    PlanoService.cadastrarPlano(sc);
                    break;
                case 2:
                	System.out.println("==== Listar Planos ====");
                    PlanoService.listarPlanos();
                    break;
                case 3:
                    System.out.println("==== Atualizar plano ====");
				PlanoService.atualizarPlano(sc);
                    break;
                case 4:
                    System.out.println("==== Excluir plano ====");
                    PlanoService.excluirPlano(sc);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
