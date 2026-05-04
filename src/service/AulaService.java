package service;

import java.util.Scanner;
import java.util.ArrayList;

import entities.Aula;
import entities.Instrutor;
import util.Util;

public class AulaService {

    private static ArrayList<Aula> listaAulas = new ArrayList<>();

    public static ArrayList<Aula> getLista() {
        return listaAulas;
    }

    public static Aula cadastrarAula(Scanner sc) {

        System.out.println("Nome da aula:");
        String nome = sc.nextLine();

        System.out.println("Horário (ex: 07:30):");
        String horario = sc.nextLine();

        System.out.println("Duração (em minutos):");
        int duracao = Util.lerInteiro(sc);
        sc.nextLine();

        System.out.println("Capacidade máxima de alunos:");
        int capacidade = Util.lerInteiro(sc);
        sc.nextLine();

        ArrayList<Instrutor> lista = InstrutorService.getLista();

        if (lista.isEmpty()) {
            System.out.println("Nenhum instrutor cadastrado!");
            return null;
        }

        System.out.println("\nEscolha um instrutor:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).getNome());
        }

        int opcao = Util.lerInteiro(sc);
        sc.nextLine();

        if (opcao < 1 || opcao > lista.size()) {
            System.out.println("Opção inválida!");
            return null;
        }

        Instrutor instrutor = lista.get(opcao - 1);

        Aula aula = new Aula(nome, horario, duracao, capacidade, instrutor);
        listaAulas.add(aula);

        System.out.println("---------------------------------");
        System.out.println(aula);
        System.out.println("---------------------------------");
        System.out.println("Aula cadastrada com sucesso!");
        System.out.println("---------------------------------");

        return aula;
    }

    public static void listarAulas() {

        if (listaAulas.isEmpty()) {
            System.out.println("Nenhuma aula cadastrada.");
            return;
        }

        System.out.println("\n==== LISTA DE AULAS ====");

        for (Aula aula : listaAulas) {
            System.out.println(aula);
            System.out.println("-------------------------");
        }
    }

    public static void atualizarAula(Scanner sc) {

        if (listaAulas.isEmpty()) {
            System.out.println("Nenhuma aula cadastrada.");
            return;
        }

        System.out.println("\nEscolha a aula para atualizar:");

        for (int i = 0; i < listaAulas.size(); i++) {
            System.out.println((i + 1) + " - " + listaAulas.get(i).getNome());
        }

        int opcao = Util.lerInteiro(sc);
        sc.nextLine();

        if (opcao < 1 || opcao > listaAulas.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        Aula aula = listaAulas.get(opcao - 1);

        try {
            System.out.print("Novo nome: ");
            aula.setNome(sc.nextLine());

            System.out.print("Novo horário: ");
            aula.setHorario(sc.nextLine());

            System.out.print("Nova duração: ");
            aula.setDuracao(Util.lerInteiro(sc));
            sc.nextLine();

            System.out.print("Nova capacidade: ");
            aula.setCapacidadeMaxima(Util.lerInteiro(sc));
            sc.nextLine();

            System.out.println("Aula atualizada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar aula: " + e.getMessage());
        }
    }

    public static void excluirAula(Scanner sc) {

        if (listaAulas.isEmpty()) {
            System.out.println("Nenhuma aula cadastrada.");
            return;
        }

        listarAulas();

        System.out.println("\nEscolha a aula para excluir:");

        for (int i = 0; i < listaAulas.size(); i++) {
            System.out.println((i + 1) + " - " + listaAulas.get(i).getNome());
        }

        int opcao = Util.lerInteiro(sc);
        sc.nextLine();

        if (opcao < 1 || opcao > listaAulas.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        listaAulas.remove(opcao - 1);

        System.out.println("Aula excluída com sucesso!");
    }

    public static Aula buscarPorNome(String nome) {
        for (Aula aula : listaAulas) {
            if (aula.getNome().equalsIgnoreCase(nome)) {
                return aula;
            }
        }
        return null;
    }
}