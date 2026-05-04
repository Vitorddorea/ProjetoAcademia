package menus;

import service.FrequenciaService;
import util.Util;

import java.util.Locale;
import java.util.Scanner;

public class FrequenciaMenu implements Menu {

    @Override
    public void exibir(Scanner sc) {

        Locale.setDefault(Locale.US);

        while (true) {
            System.out.println("\n====== REGISTRAR FREQUÊNCIA =====");
            System.out.println("1- Registrar presença");
            System.out.println("2- Listar presentes");
            System.out.println("0- Voltar ao menu principal");
            System.out.println("=================================");

            System.out.println("Escolha uma opção: ");
            int opcao = Util.lerInteiro(sc);

            switch (opcao) {
                case 1:
                    System.out.println(" ==== Registrar frequência ==== ");
                    FrequenciaService.registrarFrequencia(sc);
                    break;
                case 2:
                    System.out.println(" ==== Lista de frequência");
                    FrequenciaService.mostrarFrequencias();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }


        }


    }
}
