package service;

import entities.Instrutor;
import util.Util;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class InstrutorService {
    // é mais conveniente trabalhar a lista na classe service
    private static ArrayList<Instrutor> listaInstrutores = new ArrayList<>();

    public InstrutorService() {
        if (listaInstrutores.isEmpty()) {
            Instrutor i1 = new Instrutor("Bernado", "00000000000", "11900000000", "Pilates", "10:00 horas");
            Instrutor i2 = new Instrutor("Paula", "00000000001", "11900000001", "Yoga", "08:00 horas");

            adicionar(i1);
            adicionar(i2);
        }
    }
     public static ArrayList<Instrutor> getLista() {
        return listaInstrutores;
    }

    private void adicionar(Instrutor instrutor) {
        listaInstrutores.add(instrutor);
    }

    private Instrutor getEntity(Scanner sc) {
        System.out.println("Digite o CPF do instrutor:");
        String cpfInstrutor = sc.nextLine();

        return listaInstrutores.stream()
                .filter(e -> e.getCpf().equals(cpfInstrutor))
                .findFirst()
                .orElse(null);
    }
    public boolean listarInstrutores() {
        try {
            if (listaInstrutores.isEmpty()) {
                System.out.println("A lista de Instrutores está vazia!");
                return true;
            }
            System.out.println("\n=== LISTA DE INSTRUTORES ===");

            for (int i = 0; i < listaInstrutores.size(); i++) {
                Instrutor inst = listaInstrutores.get(i);

                System.out.println((i + 1) + " - Nome: " + inst.getNome()
                        + " CPF: " + inst.getCpf()
                        + " Telefone: " + inst.getTelefone()
                        + " Especialidade: " + inst.getEspecialidade()
                        + " Horário: " + inst.getHorarioTrabalho());
            }

            return true;

        } catch (Exception ex) {
            System.out.println("Erro em listarInstrutores: " + ex.getMessage());
            return false;
        }
    }
        public boolean cadastrarInstrutor(Scanner sc) {
        try {
            Locale.setDefault(Locale.US);

            System.out.println("Nome do Instrutor:");
            String nome = sc.nextLine();

            System.out.println("CPF:");
            String cpf = sc.nextLine();

            System.out.println("Telefone:");
            String telefone = sc.nextLine();

            System.out.println("Especialidade:");
            String especialidade = sc.nextLine();

            System.out.println("Horário de trabalho:");
            String horarioTrabalho = sc.nextLine();

            Instrutor instrutor = new Instrutor(nome, cpf, telefone, especialidade, horarioTrabalho);

            adicionar(instrutor);

            System.out.println("Instrutor cadastrado com sucesso!");
            return true;

        } catch (Exception ex) {
            System.out.println("Erro em cadastrarInstrutor: " + ex.getMessage());
            return false;
        }
    }
    public boolean atualizarInstrutor(Scanner sc) {
        try {
            if (listaInstrutores.isEmpty()) {
                System.out.println("A lista de Instrutores está vazia!");
                return true;
            }

            Instrutor instrutor = getEntity(sc);

            if (instrutor == null) {
                System.out.println("Instrutor não encontrado.");
                return false;
            }

            System.out.println("1- Nome");
            System.out.println("2- CPF");
            System.out.println("3- Telefone");
            System.out.println("4- Especialidade");

            int opcao = Util.lerInteiro(sc);
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Novo nome:");
                    instrutor.setNome(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Novo CPF:");
                    instrutor.setCpf(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Novo telefone:");
                    instrutor.setTelefone(sc.nextLine());
                    break;
                case 4:
                    System.out.println("Nova especialidade:");
                    instrutor.setEspecialidade(sc.nextLine());
                    break;
                default:
                    System.out.println("Opção inválida.");
                    return false;
            }

            System.out.println("Atualizações realizadas com sucesso!");
            return true;

        } catch (Exception ex) {
            System.out.println("Erro em atualizarInstrutor: " + ex.getMessage());
            return false;
        }
    }
    public boolean excluirInstrutor(Scanner sc) {
        try {
            if (listaInstrutores.isEmpty()) {
                System.out.println("A lista de Instrutores está vazia!");
                return true;
            }

            Instrutor instrutor = getEntity(sc);

            if (instrutor == null) {
                System.out.println("Instrutor não encontrado.");
                return false;
            }

            listaInstrutores.remove(instrutor);

            System.out.println("Instrutor removido com sucesso!");
            return true;

        } catch (Exception ex) {
            System.out.println("Erro em excluirInstrutor: " + ex.getMessage());
            return false;
        }
    }
}



