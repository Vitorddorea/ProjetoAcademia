package service;

import entities.Aluno;
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
}
