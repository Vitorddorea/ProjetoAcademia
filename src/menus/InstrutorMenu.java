package menus;

import entities.Instrutor;
import entities.Usuario;
import service.InstrutorService;
import util.Util;

import java.util.Scanner;

public class InstrutorMenu implements Menu {

    private Usuario usuario;
    private InstrutorService service;

    public InstrutorMenu(Usuario usuario, InstrutorService service) {
        this.usuario = usuario;
        this.service = service;
    }

    @Override
    public void exibir(Scanner sc) {

        while (true) {
            System.out.println("\n==== GERENCIAR INSTRUTORES ====");
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
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrar(Scanner sc) {
        System.out.print("Nome: ");
        String nome = Util.lerTexto(sc);

        System.out.print("CPF: ");
        String cpf = Util.lerTexto(sc);

        System.out.print("Telefone: ");
        String tel = Util.lerTexto(sc);

        System.out.print("Especialidade: ");
        String esp = Util.lerTexto(sc);

        System.out.print("Horário: ");
        String hor = Util.lerTexto(sc);

        Instrutor i = new Instrutor(nome, cpf, tel, esp, hor);

        if (service.cadastrar(i))
            System.out.println("Cadastrado!");
        else
            System.out.println("Já existe!");
    }

    private void listar() {
        service.listar().forEach(System.out::println);
    }

    private void excluir(Scanner sc) {
        System.out.print("CPF: ");
        String cpf = Util.lerTexto(sc);

        if (service.excluir(cpf))
            System.out.println("Removido!");
        else
            System.out.println("Não encontrado!");
    }

    private void atualizar(Scanner sc) {
        System.out.print("CPF: ");
        String cpf = Util.lerTexto(sc);

        System.out.print("Nome: ");
        String nome = Util.lerTexto(sc);

        System.out.print("Telefone: ");
        String tel = Util.lerTexto(sc);

        System.out.print("Especialidade: ");
        String esp = Util.lerTexto(sc);

        System.out.print("Horário: ");
        String hor = Util.lerTexto(sc);

        Instrutor i = new Instrutor(nome, cpf, tel, esp, hor);

        if (service.atualizar(i))
            System.out.println("Atualizado!");
        else
            System.out.println("Não encontrado!");
    }
}