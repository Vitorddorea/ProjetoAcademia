package service;

import entities.Aluno;
import entities.Aula;
import entities.Frequencia;
import util.Util;

import java.util.ArrayList;
import java.util.Scanner;

public class FrequenciaService {

    private static ArrayList<Frequencia> listaFrequencias = new ArrayList<>();

    public static Frequencia registrarFrequencia(Scanner sc) {

        System.out.print("Digite CPF do aluno: ");
        String cpf = Util.lerTexto(sc);

        Aluno aluno = AlunoService.buscarPorCpf(cpf);

        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return null;
        }

        System.out.print("Digite nome da aula: ");
        String nomeAula = Util.lerTexto(sc);

        Aula aula = AulaService.buscarPorNome(nomeAula);

        if (aula == null) {
            System.out.println("Aula não encontrada.");
            return null;
        }

        boolean inscrito = InscricaoService.getListaInscricoes().stream()
                .anyMatch(i ->
                        i.getAluno().getCpf().equals(cpf)
                                && i.getAula().getNome().equalsIgnoreCase(nomeAula)
                );

        if (!inscrito) {
            System.out.println("Aluno não está inscrito nessa aula.");
            return null;
        }

        boolean frequenciaExiste = listaFrequencias.stream()
                .anyMatch(f ->
                        f.getAluno().getCpf().equals(cpf)
                                && f.getAula().getNome().equalsIgnoreCase(nomeAula)
                );

        if (frequenciaExiste) {
            System.out.println("Frequência já registrada para este aluno nesta aula.");
            return null;
        }

        Frequencia frequencia = new Frequencia(aluno, aula);

        while (true) {
            try {
                System.out.print("Aluno presente? [S/N]: ");
                String entrada = Util.lerTexto(sc);

                boolean presente;

                if (entrada.equalsIgnoreCase("S")) {
                    presente = true;
                } else if (entrada.equalsIgnoreCase("N")) {
                    presente = false;
                } else {
                    throw new IllegalArgumentException("Digite apenas S ou N.");
                }

                frequencia.registrarPresenca(presente);
                listaFrequencias.add(frequencia);

                System.out.println("Presença registrada com sucesso.");
                break;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return frequencia;
    }

    public static void mostrarFrequencias() {

        if (listaFrequencias.isEmpty()) {
            System.out.println("Nenhuma frequência registrada.");
            return;
        }

        System.out.println("===== LISTA DE PRESENÇAS =====");

        for (Frequencia frequencia : listaFrequencias) {
            System.out.println(frequencia);
            System.out.println("-------------------------");
        }
    }

    public static ArrayList<Frequencia> getListaFrequencias() {
        return listaFrequencias;
    }
}