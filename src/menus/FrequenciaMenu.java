package menus;

import entities.Usuario;
import service.FrequenciaService;
import util.Util;

import java.util.Scanner;

public class FrequenciaMenu implements Menu {

    private Usuario usuario;
    private FrequenciaService service;

    public FrequenciaMenu(Usuario usuario, FrequenciaService service) {
        this.usuario = usuario;
        this.service = service;
    }

    @Override
    public void exibir(Scanner sc) {

        while (true) {
            System.out.println("\n==== FREQUÊNCIA ====");
            System.out.println("1- Registrar presença");
            System.out.println("2- Listar frequências");
            System.out.println("0- Voltar");

            int op = Util.lerInteiro(sc);

            switch (op) {
                case 1:
                    System.out.print("CPF: ");
                    String cpf = Util.lerTexto(sc);

                    System.out.print("Aula: ");
                    String aula = Util.lerTexto(sc);

                    System.out.print("Presente (S/N): ");
                    String resp = Util.lerTexto(sc);

                    boolean presente = resp.equalsIgnoreCase("S");

                    service.registrar(cpf, aula, presente);
                    break;

                case 2:
                    service.listar().forEach(System.out::println);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}