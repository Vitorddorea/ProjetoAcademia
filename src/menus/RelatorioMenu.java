package menus;

import service.RelatorioService;
import util.Util;

import java.util.Scanner;

public class RelatorioMenu implements Menu {

    private RelatorioService service;

    public RelatorioMenu(RelatorioService service) {
        this.service = service;
    }

    @Override
    public void exibir(Scanner sc) {

        while (true) {

            System.out.println(
                "\n===== RELATÓRIOS ====="
            );

            System.out.println(
                "1- Relatório de ocupação"
            );

            System.out.println(
                "2- Relatório de frequência"
            );

            System.out.println(
                "3- Aula mais lotada"
            );

            System.out.println(
                "0- Voltar"
            );

            int op = Util.lerInteiro(sc);

            switch (op) {

                case 1:
                    service.gerarRelatorioOcupacao();
                    break;

                case 2:
                    service.gerarRelatorioFrequencia();
                    break;

                case 3:
                    service.aulaMaisLotada();
                    break;

                case 0:
                    return;

                default:
                    System.out.println(
                        "Opção inválida!"
                    );
            }
        }
    }
}