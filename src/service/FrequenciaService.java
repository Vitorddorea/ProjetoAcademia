package service;

import entities.Frequencia;
import util.Util;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FrequenciaService {

     public static Frequencia registrarFrequencia(Scanner sc) {

        System.out.print("Aluno: ");
        String aluno = Util.lerTexto(sc);

        Frequencia frequencia = new Frequencia(aluno);

        while (true) {
            try {
                System.out.print("Aluno presente? [S/N]: ");
                String entrada = Util.lerTexto(sc);

                frequencia.registrarPresenca(entrada);

                System.out.println("Presença registrada: " + frequencia.isPresente());
                break;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return frequencia;
    }

    public static void mostrarFrequencias(ArrayList<Frequencia> listaFrequencias) {

        if (listaFrequencias.isEmpty()) {
            System.out.println("Nenhuma frequência registrada.");
            return;
        }

        System.out.println("LISTA DE PRESENÇAS");

        for (Frequencia frequencia : listaFrequencias) {
            System.out.println(frequencia.toString());
        }
    }
}
