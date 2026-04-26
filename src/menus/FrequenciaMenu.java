package menus;

import entities.Frequencia;
import service.FrequenciaService;
import util.Util;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FrequenciaMenu implements Menu {

   public ArrayList<Frequencia> listaFrequencias = new ArrayList<>();

   public FrequenciaMenu() {

       Frequencia frequencia1 = new Frequencia("Denise");

   }

    @Override
    public void exibir(Scanner sc) {

        Locale.setDefault(Locale.US);

        while (true) {
            System.out.println("\n ==== REGISTRAR PRESENÇA ====");
            System.out.println("1- Registrar presença");
            System.out.println("2- Listar presentes");
            System.out.println("0- Voltar ao menu principal");

            System.out.println("Escolha uma opção: ");
            int opcao = Util.lerInteiro(sc);

            switch (opcao) {
                case 1:
                    Frequencia presenca = FrequenciaService.registrarFrequencia(sc);
                    listaFrequencias.add(presenca);
                    break;
                case 2:
                    FrequenciaService.mostrarFrequencias(listaFrequencias);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }


        }


    }
}
