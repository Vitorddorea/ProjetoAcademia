package menus;

import entities.Aluno;
import entities.Aula;
import entities.Instrutor;
import entities.Usuario;

import service.AlunoService;
import service.AulaService;
import service.InscricaoService;
import service.InstrutorService;
import menus.InscricaoMenu;

import exceptions.EntradaException;

import java.time.LocalTime;
import java.util.Scanner;

public class AulaMenu implements Menu {

    private final Usuario usuario;
    private final AulaService service;
    private final InstrutorService instrutorService;
    private final AlunoService alunoService;
    private final InscricaoService inscricaoService;

    public AulaMenu(
            Usuario usuario,
            AulaService service,
            InstrutorService instrutorService,
            AlunoService alunoService, InscricaoService inscricaoService) {

        this.usuario = usuario;
        this.service = service;
        this.instrutorService = instrutorService;
        this.alunoService = alunoService;
        this.inscricaoService = inscricaoService;

    }

    @Override
    public void exibir(Scanner sc) {

        int opcao;

        do {
            System.out.println("\n========== GERENCIAR AULAS ============");
            System.out.println("1 - Cadastrar aula");
            System.out.println("2 - Listar aulas");
            System.out.println("3 - Atualizar aula");
            System.out.println("4 - Excluir aula");
            System.out.println("5 - Relatório de ocupação");
            System.out.println("6 - Detalhes da aula");
            System.out.println("0 - Voltar");
            System.out.println("=======================================");
            System.out.println("Escolha uma opção: ");

            opcao = EntradaException.lerInteiro(sc);

            switch (opcao) {

                case 1:
                    cadastrar(sc);
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    atualizar(sc);
                    break;
                case 4:
                    excluir(sc);
                    break;
                case 5:
                    relatorioOcupacao();
                    break;
                case 6:
                    detalharAula(sc);
                    break;
                case 0:
                    System.out.println("\nVoltando...");
                    break;
                default:
                    System.out.println("\nOpção inválida.");
            }

        } while (opcao != 0);
    }

    private void cadastrar(Scanner sc) {

        System.out.println("\n===== CADASTRO DE AULA =====");

        System.out.print("Nome: ");
        String nome = EntradaException.lerTexto(sc);

        System.out.print("Horário (HH:mm): ");
        String horario = EntradaException.lerTexto(sc);

        System.out.print("Duração (minutos): ");
        int duracao = EntradaException.lerInteiro(sc);

        System.out.print("Capacidade máxima: ");
        int capacidade = EntradaException.lerInteiro(sc);

        System.out.print("CPF do instrutor: ");
        String cpf = EntradaException.lerTexto(sc);

        Instrutor instrutor =
                instrutorService.buscarPorCpf(cpf);

        if (instrutor == null) {
            System.out.println("\nInstrutor não encontrado!");
            return;
        }

        if (possuiConflitoHorario(cpf, horario, duracao)) {
            System.out.println("\nO instrutor possui " + "conflito de horário.");

            System.out.println("É necessário intervalo " + "mínimo de 10 minutos.");
            return;
        }

        Aula aula = new Aula(nome, horario, duracao, capacidade, instrutor);

        boolean cadastrada = service.cadastrar(aula);

        if (cadastrada) {
            System.out.println("\nAula cadastrada com sucesso!");
        } else {
            System.out.println("\nAula já cadastrada!");
        }
    }

    private boolean possuiConflitoHorario(String cpfInstrutor, String horario, int duracao) {
        LocalTime inicioNovo = LocalTime.parse(horario);

        LocalTime fimNovo = inicioNovo.plusMinutes(duracao);

        return service.listar().stream().filter(a -> a.getInstrutor().getCpf().equals(cpfInstrutor)
                ).anyMatch(a -> {

                    LocalTime inicioExistente = LocalTime.parse(a.getHorario());

                    LocalTime fimExistente = inicioExistente.plusMinutes(a.getDuracao()).plusMinutes(10);

                    return inicioNovo.isBefore(fimExistente) && fimNovo.isAfter(inicioExistente);
                });
    }

    private void listar() {

        System.out.println("\n===== LISTA DE AULAS =====");

        if (service.listar().isEmpty()) {
            System.out.println("Nenhuma aula cadastrada.");
            return;
        }

        for (Aula aula : service.listar()) {
            System.out.println(aula);
        }
    }

    private void excluir(Scanner sc) {

        System.out.println("\n===== EXCLUIR AULA =====");

        System.out.print("Nome da aula: ");
        String nome = EntradaException.lerTexto(sc);

        boolean removida = service.excluir(nome);

        if (removida) {System.out.println("\nAula removida com sucesso!");
        } else {
            System.out.println("\nAula não encontrada!");
        }
    }

    private void atualizar(Scanner sc) {

        System.out.println("\n===== ATUALIZAR AULA =====");

        System.out.print("Nome da aula: ");
        String nome = EntradaException.lerTexto(sc);

        System.out.print("Novo horário: ");
        String horario = EntradaException.lerTexto(sc);

        System.out.print("Nova duração: ");
        int duracao = EntradaException.lerInteiro(sc);

        System.out.print("Nova capacidade: ");
        int capacidade = EntradaException.lerInteiro(sc);

        System.out.print("CPF do instrutor: ");
        String cpf = EntradaException.lerTexto(sc);

        Instrutor instrutor = instrutorService.buscarPorCpf(cpf);

        if (instrutor == null) {
            System.out.println("\nInstrutor não encontrado!");
            return;
        }

        Aula aula = new Aula(nome, horario, duracao, capacidade, instrutor);

        boolean atualizada = service.atualizar(aula);

        if (atualizada) {
            System.out.println("\nAula atualizada com sucesso!");
        } else {System.out.println("\nAula não encontrada!");
        }
    }

    private void relatorioOcupacao() {
        service.gerarRelatorioOcupacao();
    }

    private void detalharAula(Scanner sc) {
        System.out.print("Nome da aula: ");
        String nome = EntradaException.lerTexto(sc);

        service.exibirDetalhesAula(nome, inscricaoService);
    }
}