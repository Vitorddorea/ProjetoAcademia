package service;

import entities.Instrutor;

import java.util.Locale;
import java.util.Scanner;

public class InstrutorService {

    public static Instrutor cadastrarInstrutor(Scanner sc) {

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

        System.out.println("Instrutor cadastrado com sucesso!");
        instrutor.mostrarInstrutor();

        return instrutor;

    }


}
