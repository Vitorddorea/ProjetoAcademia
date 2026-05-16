package menus;

import entities.Instrutor;
import entities.Usuario;
import service.InstrutorService;
import exceptions.EntradaException;

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
            System.out.println("\n======== GERENCIAR INSTRUTORES ========");
            System.out.println("1- Cadastrar");
            System.out.println("2- Listar");
            System.out.println("3- Atualizar");
            System.out.println("4- Excluir");
            System.out.println("0- Voltar");
            System.out.println("=======================================");
            System.out.println("Escolha uma opção:");

            int op = EntradaException.lerInteiro(sc);

            switch (op) {
                case 1: cadastrar(sc); break;
                case 2: listar(); break;
                case 3: atualizar(sc); break;
                case 4: excluir(sc); break;
                case 0: return;
                default: System.out.println("\nOpção inválida!");
            }
        }
    }

    private void cadastrar(Scanner sc) {
        System.out.print("Cadastrar novo instrutor\n");

        System.out.print("Nome: ");
        String nome = EntradaException.lerTexto(sc);

        System.out.print("CPF: ");
        String cpf = EntradaException.lerTexto(sc);

        System.out.print("Telefone: ");
        String tel = EntradaException.lerTexto(sc);

        System.out.print("Especialidade: ");
        String esp = EntradaException.lerTexto(sc);

        System.out.print("Horário: ");
        String hor = EntradaException.lerTexto(sc);

        Instrutor i = new Instrutor(nome, cpf, tel, esp, hor);

        if (service.cadastrar(i))
            System.out.println("\nInstrutor Cadastrado!");
        else
            System.out.println("\nJá existe!");
    }

    private void listar() {
        System.out.println("Lista de instrutores:");
        service.listar().forEach(System.out::println);
    }

    private void excluir(Scanner sc) {
        System.out.print("CPF: ");
        String cpf = EntradaException.lerTexto(sc);

        Instrutor instrutor = service.buscarPorCpf(cpf);
        if (instrutor == null) {
            System.out.println("\nInstrutor não encontrado!");
            return;
        }

        System.out.print("Deseja realmente excluir o instrutor: " + instrutor.getNome() + "? (S/N):");
        String confirmacao = EntradaException.lerTexto(sc);
        if (confirmacao.equalsIgnoreCase("N") || confirmacao.equalsIgnoreCase("nao")) {
            System.out.println("\nExclusão cancelada.");
            return;
        }
        service.excluir(cpf);
        System.out.println("\nInstrutor Removido com sucesso!");
    }

    private void atualizar(Scanner sc) {
        System.out.println("Atualizar instrutor\n");

        System.out.println("Digite o CPF do instrutor a ser atualizado:");
        System.out.print("CPF: ");
        String cpf = EntradaException.lerTexto(sc);

        Instrutor instrutorExistente = service.buscarPorCpf(cpf);

        if (instrutorExistente == null) {
            System.out.println("\nInstrutor não encontrado!");
            return;
        }

        System.out.print("\nDados atuais do instrutor: "+ instrutorExistente);
       
        System.out.println("\n1- Nome");
        System.out.println("2- Telefone");
        System.out.println("3- Especialidade");
        System.out.println("4- Horário");
        System.out.println("5- Todos os dados");
        System.out.println("0- Cancelar");

        System.out.print("Escolha o campo a atualizar: ");
        int opcao = EntradaException.lerInteiro(sc);

        switch (opcao) {
            case 1:
                System.out.print(" Novo Nome: ");
                String nome = EntradaException.lerTexto(sc);
                instrutorExistente.setNome(nome);
                break;

            case 2:
                System.out.print(" NovoTelefone: ");
                String tel = EntradaException.lerTexto(sc);
                instrutorExistente.setTelefone(tel);
                break;

            case 3:
                System.out.print("Nova Especialidade: ");
                String esp = EntradaException.lerTexto(sc);
                instrutorExistente.setEspecialidade(esp);
                break;

            case 4:
                System.out.print("Novo Horário: ");
                String hor = EntradaException.lerTexto(sc);
                instrutorExistente.setHorarioTrabalho(hor);
                break;

            case 5:
                System.out.print(" Novo Nome: ");
                String nome2 = EntradaException.lerTexto(sc);

                System.out.print(" Novo Telefone: ");
                String tel2 = EntradaException.lerTexto(sc);

                System.out.print("Nova Especialidade: ");
                String esp2 = EntradaException.lerTexto(sc);

                System.out.print("Novo Horário: ");
                String hor2 = EntradaException.lerTexto(sc);

                instrutorExistente.setNome(nome2);
                instrutorExistente.setTelefone(tel2);
                instrutorExistente.setEspecialidade(esp2);
                instrutorExistente.setHorarioTrabalho(hor2);
                break;

            case 0:
                System.out.println("\nAtualização cancelada.");
                return;

            default:
                System.out.println("\nOpção inválida!");
                return;
        }

        service.atualizar(instrutorExistente);
        System.out.println("\nInstrutor atualizado com sucesso!");
    }
}