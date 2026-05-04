package service;

import entities.Aluno;
import entities.Aula;
import entities.Inscricao;
import util.Util;

import java.util.ArrayList;
import java.util.Scanner;

public class InscricaoService {

    private static ArrayList<Inscricao> listaInscricoes = new ArrayList<>();

    public static Inscricao realizarInscricao(Scanner sc) {

        System.out.println("Digite o CPF do aluno:");
        String cpf = Util.lerTexto(sc);

        Aluno aluno = AlunoService.buscarPorCpf(cpf);

        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return null;
        }

        System.out.println("Digite o nome da aula:");
        String nomeAula = Util.lerTexto(sc);

        Aula aula = AulaService.buscarPorNome(nomeAula);

        if (aula == null) {
            System.out.println("Aula não encontrada.");
            return null;
        }

        boolean jaInscrito = listaInscricoes.stream()
                .anyMatch(i ->
                        i.getAluno().getCpf().equals(cpf)
                                && i.getAula().getNome().equalsIgnoreCase(nomeAula)
                );

        if (jaInscrito) {
            System.out.println("Aluno já está inscrito nessa aula.");
            return null;
        }

        try {
            aula.adicionarAluno();

            Inscricao inscricao = new Inscricao(aluno, aula);
            listaInscricoes.add(inscricao);

            System.out.println("--------------------------------");
            System.out.println(inscricao);
            System.out.println("--------------------------------");
            System.out.println("Inscrição realizada com sucesso!");
            System.out.println("--------------------------------");

            return inscricao;

        } catch (Exception ex) {
            System.out.println("Erro ao realizar inscrição: " + ex.getMessage());
            return null;
        }
    }

    public static void cancelarInscricao(Scanner sc) {

        System.out.println("Digite o CPF do aluno:");
        String cpf = Util.lerTexto(sc);

        System.out.println("Digite o nome da aula:");
        String nomeAula = Util.lerTexto(sc);

        Inscricao inscricao = listaInscricoes.stream()
                .filter(i ->
                        i.getAluno().getCpf().equals(cpf)
                                && i.getAula().getNome().equalsIgnoreCase(nomeAula)
                )
                .findFirst()
                .orElse(null);

        if (inscricao == null) {
            System.out.println("Inscrição não encontrada.");
            return;
        }

        try {
            inscricao.getAula().removerAluno();
            listaInscricoes.remove(inscricao);

            System.out.println("Inscrição cancelada com sucesso!");

        } catch (Exception ex) {
            System.out.println("Erro ao cancelar inscrição: " + ex.getMessage());
        }
    }

    public static void listarInscricoes() {

        if (listaInscricoes.isEmpty()) {
            System.out.println("Nenhuma inscrição registrada.");
            return;
        }

        System.out.println("===== LISTA DE INSCRIÇÕES =====");

        for (Inscricao inscricao : listaInscricoes) {
            System.out.println(inscricao);
            System.out.println("-------------------------");
        }
    }

    public static ArrayList<Inscricao> getListaInscricoes() {
        return listaInscricoes;
    }
}