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
            System.out.println("3- Atualizar frequência");
            System.out.println("4- Excluir frequência");
            System.out.println("5- Relatório por período");
            System.out.println("0- Voltar");
            System.out.println("=======================================");
            System.out.println("Escolha uma opção: ");

            int op = EntradaException.lerInteiro(sc);

            switch (op) {
                case 1:
                    registrar(sc);
                    break;
                case 2:
                    service.listar().forEach(System.out::println);
                    break;
                case 3:
                    atualizar(sc);
                    break;
                case 4:
                    excluir(sc);
                    break;
                case 5:
                    relatorio(sc);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void registrar(Scanner sc) {

        System.out.print("CPF do aluno: ");
        String cpf = EntradaException.lerTexto(sc);

        System.out.print("Aula: ");
        String aula = EntradaException.lerTexto(sc);

        System.out.print("Presente (S/N): ");
        boolean presente =
                EntradaException.lerTexto(sc)
                        .equalsIgnoreCase("S");

        service.registrar(cpf, aula, presente);
    }

    private void atualizar(Scanner sc) {

        System.out.print("CPF do aluno: ");
        String cpf = EntradaException.lerTexto(sc);

        System.out.print("Aula: ");
        String aula = EntradaException.lerTexto(sc);

        System.out.print("Presente (S/N): ");
        boolean presente =
                EntradaException.lerTexto(sc)
                        .equalsIgnoreCase("S");

        service.atualizar(cpf, aula, presente);
    }

    private void excluir(Scanner sc) {

        System.out.print("CPF do aluno: ");
        String cpf = EntradaException.lerTexto(sc);

        System.out.print("Aula: ");
        String aula = EntradaException.lerTexto(sc);

        service.excluir(cpf, aula);
    }

    private void relatorio(Scanner sc) {

        System.out.print("CPF do aluno: ");
        String cpf = EntradaException.lerTexto(sc);

        System.out.print("Data inicial (yyyy-MM-dd): ");
        java.time.LocalDate inicio =
                java.time.LocalDate.parse(
                        EntradaException.lerTexto(sc)
                );

        System.out.print("Data final (yyyy-MM-dd): ");
        java.time.LocalDate fim =
                java.time.LocalDate.parse(
                        EntradaException.lerTexto(sc)
                );

        service.gerarRelatorioAluno(cpf, inicio, fim);
    }
}