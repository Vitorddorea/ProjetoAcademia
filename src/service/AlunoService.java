package service;

import entities.Aluno;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class AlunoService {

    public static Aluno cadastrarAluno(Scanner sc) {

        Locale.setDefault(Locale.US);

        System.out.println("Nome do Aluno:");
        String nome = sc.nextLine();

        System.out.println("CPF:");
        String cpf = sc.nextLine();

        System.out.println("Data de Nascimento:");
        String dataNascimento = sc.nextLine();

        System.out.println("Telefone:");
        String telefone = sc.nextLine();

        System.out.println("E-mail:");
        String email = sc.nextLine();

        System.out.println("Plano ativo:");
        String planoAtivo = sc.nextLine();

        Aluno aluno = new Aluno(nome, cpf, dataNascimento, telefone, email, planoAtivo);

        System.out.println("Aluno cadastrado com sucesso!");
        aluno.mostrarAluno();

        return aluno;
    }

    public static void listarAlunos(ArrayList<Aluno> listaAlunos) {

        if (listaAlunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        System.out.println("\nLISTA DE ALUNOS");

        for (Aluno aluno : listaAlunos) {
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("CPF: " + aluno.getCpf());
            System.out.println("Telefone: " + aluno.getTelefone());
            System.out.println("Email: " + aluno.getEmail());
            System.out.println("Plano ativo: " + aluno.getPlanoAtivo());
            System.out.println("-------------------------");
        }
    }

    public static void excluirAluno(ArrayList<Aluno> listaAlunos, String cpf) {

        for (int i = 0; i < listaAlunos.size(); i++) {
            if (listaAlunos.get(i).getCpf().equals(cpf)) {
                listaAlunos.remove(i);
                System.out.println("Aluno removido com sucesso.");
                return;
            }
        }

        System.out.println("Aluno não encontrado.");
    }

    public static void atualizarAluno(Scanner sc, ArrayList<Aluno> listaAlunos) {

        System.out.print("Digite o CPF do aluno que deseja atualizar: ");
        String cpf = sc.nextLine();

        for (Aluno aluno : listaAlunos) {
            if (aluno.getCpf().equals(cpf)) {

                System.out.print("Novo nome: ");
                aluno.setNome(sc.nextLine());

                System.out.print("Novo telefone: ");
                aluno.setTelefone(sc.nextLine());

                System.out.print("Novo email: ");
                aluno.setEmail(sc.nextLine());

                System.out.print("Novo plano: ");
                aluno.setPlanoAtivo(sc.nextLine());

                System.out.println("Aluno atualizado com sucesso.");
                return;
            }
        }
        System.out.println("Aluno não encontrado.");
    }
}
