package menus;

import entities.Usuario;
import service.FrequenciaService;
import exceptions.EntradaException;

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
            System.out.println("\n============= FREQUÊNCIA ==============");
            System.out.println("1- Registrar presença");
            System.out.println("2- Listar frequências");
            System.out.println("0- Voltar");
            System.out.println("=======================================");
            System.out.println("Escolha uma opção: ");

            int op = EntradaException.lerInteiro(sc);

            switch (op) {
                case 1:
                    System.out.print("CPF do aluno: ");
                    String cpf = EntradaException.lerTexto(sc);

                    System.out.print("Aula: ");
                    String aula = EntradaException.lerTexto(sc);

                    System.out.print("Presente (S/N): ");
                    String resp = EntradaException.lerTexto(sc);

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