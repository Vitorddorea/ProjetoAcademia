package service;

import java.util.Scanner;
import java.util.ArrayList;


import entities.Aula;
import entities.Instrutor;

import util.Util;

public class AulaService {
    private static  ArrayList<Aula> listaAulas = new ArrayList<>();

    public static Aula cadastrarAula(Scanner sc) {

        System.out.print("Nome da aula: ");
        String nome = sc.nextLine();

        System.out.print("Horário (ex: 07:30): ");
        String horario = sc.nextLine();

        System.out.print("Duração (em minutos): ");
        int duracao = sc.nextInt();

        System.out.print("Capacidade maxima de alunos: ");
        int capacidade = sc.nextInt();
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

        int opcao = sc.nextInt();
        sc.nextLine();

        if (opcao < 1 || opcao > lista.size()) {
            System.out.println("Opção inválida!");
            return null;
        }

        Instrutor instrutor = lista.get(opcao - 1);

        Aula novaAula = new Aula(nome, horario, duracao, capacidade, instrutor);

        System.out.println("Aula cadastrada com sucesso!");

        return novaAula;
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

        int opcao = sc.nextInt();
        sc.nextLine();

        if (opcao < 1 || opcao > listaAulas.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        Aula aula = listaAulas.get(opcao - 1);

        System.out.print("Novo nome: ");
        aula.setNome(sc.nextLine());

        System.out.print("Novo horario: ");
        aula.setHorario(sc.nextLine());

        System.out.print("Nova duracao: ");
        aula.setDuracao(sc.nextInt());

        System.out.print("Nova capacidade: ");
        aula.setCapacidadeMaxima(sc.nextInt());
        sc.nextLine();

        System.out.println("Aula atualizada com sucesso!");
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
