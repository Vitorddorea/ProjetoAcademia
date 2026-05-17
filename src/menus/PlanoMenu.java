package menus;

import entities.Plano;
import service.PlanoService;
import exceptions.EntradaException;

import java.util.Scanner;

public class PlanoMenu implements Menu {

    private final PlanoService service;

    public PlanoMenu(PlanoService service) {
        this.service = service;
    }

    @Override
    public void exibir(Scanner sc) {

        while (true) {
            System.out.println("\n========== GERENCIAR PLANO ============");
            System.out.println("1- Cadastrar novo plano");
            System.out.println("2- Listar planos");
            System.out.println("3- Atualizar plano");
            System.out.println("4- Excluir plano");
            System.out.println("0- Voltar");
            System.out.println("=======================================");
            System.out.println("Escolha uma opção");

            int opcao = EntradaException.lerInteiro(sc);

            switch (opcao) {
                case 1:
                    cadastrarPlano(sc);
                    break;
                case 2:
                    listarPlanos();
                    break;
                case 3:
                    atualizarPlano(sc);
                    break;
                case 4:
                    excluirPlano(sc);
                    break;
                case 0:
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    private void cadastrarPlano(Scanner sc) {

        System.out.println("\n=== CADASTRAR PLANO ===");

        System.out.print("Nome: ");
        String nome = EntradaException.lerTexto(sc);

        System.out.print("Descrição: ");
        String descricao = EntradaException.lerTexto(sc);

        System.out.print("Valor mensal: ");
        double valorMensal = EntradaException.lerReal(sc);

        System.out.print("Duração (meses): ");
        int duracao = EntradaException.lerInteiro(sc);

        System.out.print("Benefícios: ");
        String beneficios = EntradaException.lerTexto(sc);

        Plano plano = new Plano(nome, descricao, valorMensal, duracao, beneficios);

        if (service.cadastrarPlano(plano)) {
            System.out.println("Plano cadastrado com sucesso!");
        } else {
            System.out.println("Já existe um plano com esse nome!");
        }
    }

    private void listarPlanos() {
        System.out.println("\n=== LISTA DE PLANOS ===");
        service.listarPlanos().forEach(System.out::println);
    }

    private void atualizarPlano(Scanner sc) {

        System.out.println("\n=== ATUALIZAR PLANO ===");

        System.out.print("Nome do plano: ");
        String nome = EntradaException.lerTexto(sc);

        System.out.print("Nova descrição: ");
        String descricao = EntradaException.lerTexto(sc);

        System.out.print("Novo valor mensal: ");
        double valor = EntradaException.lerReal(sc);

        System.out.print("Nova duração (meses): ");
        int duracao = EntradaException.lerInteiro(sc);

        System.out.print("Novos benefícios: ");
        String beneficios = EntradaException.lerTexto(sc);

        Plano planoAtualizado = new Plano(nome, descricao, valor, duracao, beneficios);

        if (service.atualizarPlano(planoAtualizado)) {
            System.out.println("Plano atualizado com sucesso!");
        } else {
            System.out.println("Plano não encontrado!");
        }
    }

    private void excluirPlano(Scanner sc) {

        System.out.println("\n=== EXCLUIR PLANO ===");

        System.out.print("Nome: ");
        String nome = EntradaException.lerTexto(sc);

        if (service.excluirPlano(nome)) {
            System.out.println("Plano removido com sucesso!");
        } else {
            System.out.println("Plano não encontrado!");
        }
    }
}