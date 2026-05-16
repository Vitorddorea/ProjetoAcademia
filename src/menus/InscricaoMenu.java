package menus;

import entities.Usuario;
import service.InscricaoService;
import exceptions.EntradaException;

import java.util.Scanner;

public class InscricaoMenu implements Menu {

    private Usuario usuario;
    private InscricaoService service;

    public InscricaoMenu(Usuario usuario, InscricaoService service) {
        this.usuario = usuario;
        this.service = service;
    }

    @Override
    public void exibir(Scanner sc) {

        while (true) {
            System.out.println("\n==== INSCRIÇÕES ====");
            System.out.println("1- Inscrever aluno");
            System.out.println("2- Listar inscrições");
            System.out.println("0- Voltar");

            int op = EntradaException.lerInteiro(sc);

            switch (op) {
                case 1:
                    System.out.print("CPF do aluno: ");
                    String cpf = EntradaException.lerTexto(sc);

                    System.out.print("Nome da aula: ");
                    String aula = EntradaException.lerTexto(sc);

                    service.inscrever(cpf, aula);
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