package menus;

import service.RelatorioService;
import util.Util;

import java.util.Scanner;

public class RelatorioMenu implements Menu{
    @Override
    public void exibir(Scanner sc) {

        while (true) {

            System.out.println("\n=========== RELATÓRIOS ==========");
            System.out.println("1- Relatório de aulas");
            System.out.println("2- Histórico de frequência");
            System.out.println("0- Voltar para o menu principal");
            System.out.println("=================================");

            System.out.println("Escolha uma opção:");
            int opcao = Util.lerInteiro(sc);

            switch (opcao) {
                case 1:
                    System.out.println(" ==== Relatório de aulas ====");
                    RelatorioService.mostrarRelatorioAulas();
                    break;
                case 2:
                    System.out.println(" ==== Histórico de frequência ====");
                    RelatorioService.historicoFrequencia();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
