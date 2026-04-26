package service;

import entities.Aluno;
import util.Util;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class AlunoService {

    public static Aluno cadastrarAluno(Scanner sc) {

        Locale.setDefault(Locale.US);

        System.out.println("Nome do Aluno:");
        String nome = Util.lerTexto(sc);

        System.out.println("CPF:");
        String cpf = Util.lerTexto(sc);

        System.out.println("Data de Nascimento:");
        String dataNascimento = Util.lerTexto(sc);

        System.out.println("Telefone:");
        String telefone = Util.lerTexto(sc);

        System.out.println("E-mail:");
        String email = Util.lerTexto(sc);

        System.out.println("Plano ativo:");
        String planoAtivo = Util.lerTexto(sc);

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
            aluno.mostrarAluno();
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
                aluno.setNome(Util.lerTexto(sc));

                System.out.print("Novo telefone: ");
                aluno.setTelefone(Util.lerTexto(sc));

                System.out.print("Novo email: ");
                aluno.setEmail(Util.lerTexto(sc));

                System.out.print("Novo plano: ");
                aluno.setPlanoAtivo(Util.lerTexto(sc));

                System.out.println("Aluno atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Aluno não encontrado.");
    }
}
