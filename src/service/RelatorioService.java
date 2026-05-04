package service;

import entities.Aula;
import entities.Frequencia;

public class RelatorioService {

    public static void mostrarRelatorioAulas() {

        if (AulaService.getLista().isEmpty()) {
            System.out.println("Nenhuma aula cadastrada.");
            return;
        }

        System.out.println("===== RELATÓRIO DE OCUPAÇÃO DAS AULAS =====");

        for (Aula aula : AulaService.getLista()) {

            int inscritos = aula.getAlunosInscritos();
            int capacidade = aula.getCapacidadeMaxima();

            double ocupacao = ((double) inscritos / capacidade) * 100;

            System.out.println("Aula: " + aula.getNome());
            System.out.println("Inscritos: " + inscritos + "/" + capacidade);
            System.out.printf("Taxa de ocupação: %.2f%%\n", ocupacao);
            System.out.println("------------------------------");
        }
    }

    public static void historicoFrequencia() {

        if (FrequenciaService.getListaFrequencias().isEmpty()) {
            System.out.println("Nenhuma frequência registrada.");
            return;
        }

        System.out.println("===== HISTÓRICO DE FREQUÊNCIA =====");

        for (Frequencia frequencia : FrequenciaService.getListaFrequencias()) {
            System.out.println(frequencia);
            System.out.println("------------------------------");
        }
    }
}