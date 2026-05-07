package menus;

import entities.Aula;
import entities.Instrutor;
import entities.Usuario;
import service.AulaService;
import service.InstrutorService;
import util.Util;

import java.util.Scanner;

public class AulaMenu implements Menu {

    private Usuario usuario;
    private AulaService service;
    private InstrutorService instrutorService;

    public AulaMenu(Usuario usuario, AulaService service, InstrutorService instrutorService) {
        this.usuario = usuario;
        this.service = service;
        this.instrutorService = instrutorService;
    }

    public AulaMenu(Usuario usuario, AulaService service) {
        this.usuario = usuario;
        this.service = service;
    }

    @Override
    public void exibir(Scanner sc) {

        while (true) {
            System.out.println("\n==== GERENCIAR AULAS ====");
            System.out.println("1- Cadastrar");
            System.out.println("2- Listar");
            System.out.println("3- Atualizar");
            System.out.println("4- Excluir");
            System.out.println("0- Voltar");

            int op = Util.lerInteiro(sc);

            switch (op) {
                case 1: cadastrar(sc); break;
                case 2: listar(); break;
                case 3: atualizar(sc); break;
                case 4: excluir(sc); break;
                case 0: return;
            }
        }
    }

    private void cadastrar(Scanner sc) {
        System.out.print("Nome: ");
        String nome = Util.lerTexto(sc);

        System.out.print("Horário: ");
        String horario = Util.lerTexto(sc);

        System.out.print("Duração: ");
        int duracao = Util.lerInteiro(sc);

        System.out.print("Capacidade: ");
        int capacidade = Util.lerInteiro(sc);

        System.out.print("CPF do instrutor: ");
        String cpf = Util.lerTexto(sc);

        Instrutor instrutor = instrutorService.buscarPorCpf(cpf);

        if (instrutor == null) {
            System.out.println("Instrutor não encontrado!");
            return;
        }

        Aula aula = new Aula(nome, horario, duracao, capacidade, instrutor);

        if (service.cadastrar(aula))
            System.out.println("Cadastrada!");
        else
            System.out.println("Já existe!");
    }

    private void listar() {
        service.listar().forEach(System.out::println);
    }

    private void excluir(Scanner sc) {
        System.out.print("Nome: ");
        String nome = Util.lerTexto(sc);

        if (service.excluir(nome))
            System.out.println("Removida!");
        else
            System.out.println("Não encontrada!");
    }

    private void atualizar(Scanner sc) {
        System.out.print("Nome: ");
        String nome = Util.lerTexto(sc);

        System.out.print("Novo horário: ");
        String horario = Util.lerTexto(sc);

        System.out.print("Nova duração: ");
        int duracao = Util.lerInteiro(sc);

        System.out.print("Nova capacidade: ");
        int capacidade = Util.lerInteiro(sc);

        System.out.print("CPF do instrutor: ");
        String cpf = Util.lerTexto(sc);

        Instrutor instrutor = instrutorService.buscarPorCpf(cpf);

        if (instrutor == null) {
            System.out.println("Instrutor não encontrado!");
            return;
        }

        Aula aula = new Aula(nome, horario, duracao, capacidade, instrutor);

        if (service.atualizar(aula))
            System.out.println("Atualizada!");
        else
            System.out.println("Não encontrada!");
    }
}