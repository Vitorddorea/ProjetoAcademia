package service;

import entities.Aula;
import util.Util;

import java.util.Locale;
import java.util.Scanner;

public class AulaService {

    public static Aula cadastrarAula(Scanner sc) {

        Locale.setDefault(Locale.US);

        System.out.println("Nome da aula:");
        String nome = Util.lerTexto(sc);

        System.out.println("Descrição:");
        String descricao = Util.lerTexto(sc);

        System.out.println("Capacidade maxima:");
        int capacidadeMaxima = Util.lerInteiro(sc);

        System.out.println("Horário:");
        String horario = Util.lerTexto(sc);

        System.out.println("Duração em minutos:");
        int duracao = Util.lerInteiro(sc);

        System.out.println("Instrutor:");
        String instrutor = Util.lerTexto(sc);

        Aula aula = new Aula(nome, descricao, capacidadeMaxima, horario, duracao, instrutor);

        System.out.println("Aula cadastrada com sucesso!");

        return aula;

    }



}
