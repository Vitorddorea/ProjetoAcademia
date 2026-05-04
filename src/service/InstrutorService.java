package service;

import entities.Instrutor;
import util.Util;

import java.util.ArrayList;
import java.util.Scanner;

public class InstrutorService {

    private static ArrayList<Instrutor> listaInstrutores = new ArrayList<>();

     public static ArrayList<Instrutor> getLista() {
        return listaInstrutores;
    }

    public static Instrutor cadastrarInstrutor(Scanner sc) {

        System.out.println("Nome do Instrutor:");
        String nome = Util.lerTexto(sc);

        System.out.println("CPF:");
        String cpf = Util.lerTexto(sc);

        System.out.println("Telefone:");
        String telefone = Util.lerTexto(sc);

        System.out.println("Especialidade:");
        String especialidade = Util.lerTexto(sc);

        System.out.println("Horário de trabalho:");
        String horarioTrabalho = Util.lerTexto(sc);

        Instrutor instrutor = new Instrutor(nome, cpf, telefone, especialidade, horarioTrabalho);

        listaInstrutores.add(instrutor);

        System.out.println("---------------------------------");
        System.out.println(instrutor);
        System.out.println("---------------------------------");
        System.out.println("Instrutor cadastrado com sucesso!");
        System.out.println("---------------------------------");

        return instrutor;

    }

    public static void listarInstrutores() {

        if (listaInstrutores.isEmpty()) {
            System.out.println("Nenhum instrutor cadastrado.");
            return;
        }

        for (int i = 0; i < listaInstrutores.size(); i++) {
            Instrutor inst = listaInstrutores.get(i);

            System.out.println("---------------------------");
            System.out.println((i + 1) + " - Nome: " + inst.getNome()
                    + " CPF: " + inst.getCpf()
                    + " Telefone: " + inst.getTelefone()
                    + " Especialidade: " + inst.getEspecialidade()
                    + " Horário: " + inst.getHorarioTrabalho());
        }
    }

    public static void excluirInstrutor(Scanner sc) {
        try {
            if (listaInstrutores.isEmpty()) {
                System.out.println("A lista de Instrutores está vazia!");
                return;
            }

            Instrutor instrutor = getEntity(sc);

            if (instrutor == null) {
                System.out.println("Instrutor não encontrado.");
                return;
            }

            listaInstrutores.remove(instrutor);

            System.out.println("Instrutor removido com sucesso!");

        } catch (Exception ex) {
            System.out.println("Erro em excluirInstrutor: " + ex.getMessage());
        }
    }

    public static void atualizarInstrutor(Scanner sc) {
        try {
            if (listaInstrutores.isEmpty()) {
                System.out.println("A lista de Instrutores está vazia!");
                return;
            }

            Instrutor instrutor = getEntity(sc);

            if (instrutor == null) {
                System.out.println("Instrutor não encontrado.");
                return ;
            }

            System.out.println("\n=== O que deseja atualizar? ===");
            System.out.println("1- Nome");
            System.out.println("2- CPF");
            System.out.println("3- Telefone");
            System.out.println("4- Especialidade");
            System.out.println("5- Horário de trabalho");

            int opcao = Util.lerInteiro(sc);

            switch (opcao) {
                case 1:
                    System.out.println("Novo nome:");
                    instrutor.setNome(Util.lerTexto(sc));
                    break;
                case 2:
                    System.out.println("Novo CPF:");
                    instrutor.setCpf(Util.lerTexto(sc));
                    break;
                case 3:
                    System.out.println("Novo telefone:");
                    instrutor.setTelefone(Util.lerTexto(sc));
                    break;
                case 4:
                    System.out.println("Nova especialidade:");
                    instrutor.setEspecialidade(Util.lerTexto(sc));
                    break;
                case 5:
                    System.out.println("Novo horário de trabalho:");
                    instrutor.setHorarioTrabalho(Util.lerTexto(sc));
                default:
                    System.out.println("Opção inválida.");
                    return ;
            }

            System.out.println("Atualizações realizadas com sucesso!");

        } catch (Exception ex) {
            System.out.println("Erro em atualizarInstrutor: " + ex.getMessage());
        }
    }

    public static Instrutor getEntity(Scanner sc) {
        System.out.println("Digite o CPF do instrutor:");
        String cpfInstrutor = Util.lerTexto(sc);

        return listaInstrutores.stream()
                .filter(e -> e.getCpf().equals(cpfInstrutor))
                .findFirst()
                .orElse(null);
    }
}



