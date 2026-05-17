package service;

import entities.Aula;
import entities.Frequencia;

import java.util.List;

public class RelatorioService {

    private AulaService aulaService;
    private FrequenciaService frequenciaService;

    public RelatorioService(
            AulaService aulaService,
            FrequenciaService frequenciaService) {

        this.aulaService = aulaService;
        this.frequenciaService = frequenciaService;
    }

    public void gerarRelatorioOcupacao() {

        System.out.println(
            "\n===== RELATÓRIO DE OCUPAÇÃO ====="
        );

        List<Aula> aulas = aulaService.listar();

        if (aulas.isEmpty()) {
            System.out.println("Nenhuma aula cadastrada.");
            return;
        }

        for (Aula aula : aulas) {

            int matriculados = aula.getAlunos().size();

            double ocupacao =
                ((double) matriculados /
                aula.getCapacidadeMaxima()) * 100;

            System.out.println(
                "\nAula: " + aula.getNome()
            );

            System.out.println(
                "Instrutor: " +
                aula.getInstrutor().getNome()
            );

            System.out.println(
                "Alunos matriculados: " +
                matriculados +
                "/" +
                aula.getCapacidadeMaxima()
            );

            System.out.printf(
                "Ocupação: %.1f%%\n",
                ocupacao
            );
        }
    }

    public void gerarRelatorioFrequencia() {

        System.out.println(
            "\n===== RELATÓRIO DE FREQUÊNCIA ====="
        );

        List<Frequencia> frequencias =
            frequenciaService.listar();

        if (frequencias.isEmpty()) {
            System.out.println(
                "Nenhuma frequência registrada."
            );
            return;
        }

        for (Frequencia f : frequencias) {

            System.out.println(
                "\nAluno: " +
                f.getAluno().getNome()
            );

            System.out.println(
                "Aula: " +
                f.getAula().getNome()
            );

            System.out.println(
                "Presença: " +
                (f.isPresente() ? "SIM" : "NÃO")
            );
        }
    }

    public void aulaMaisLotada() {

        List<Aula> aulas = aulaService.listar();

        if (aulas.isEmpty()) {
            System.out.println("Nenhuma aula cadastrada.");
            return;
        }

        Aula maisLotada = aulas.get(0);

        for (Aula aula : aulas) {

            if (aula.getAlunos().size() >
                maisLotada.getAlunos().size()) {

                maisLotada = aula;
            }
        }

        System.out.println(
            "\n===== AULA MAIS LOTADA ====="
        );

        System.out.println(
            "Aula: " +
            maisLotada.getNome()
        );

        System.out.println(
            "Quantidade de alunos: " +
            maisLotada.getAlunos().size()
        );
    }
}