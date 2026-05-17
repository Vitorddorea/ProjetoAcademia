package menus;

import entities.Instrutor;
import entities.Usuario;
import service.InstrutorService;
import util.Util;

import java.util.Scanner;

public class InstrutorMenu implements Menu {

    private final Usuario usuario;
    private final InstrutorService service;

    public InstrutorMenu(
            Usuario usuario,
            InstrutorService service) {

        this.usuario = usuario;
        this.service = service;
    }

    @Override
    public void exibir(Scanner sc) {

        int opcao;

        do {

            exibirOpcoes();

            opcao = Util.lerInteiro(sc);

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

                case 0:
                    System.out.println("\nVoltando...");
                    break;

                default:
                    System.out.println("\nOpção inválida!");
            }

        } while (opcao != 0);
    }

    private void exibirOpcoes() {

        System.out.println("\n==== GERENCIAR INSTRUTORES ====");
        System.out.println("1- Cadastrar");
        System.out.println("2- Listar");
        System.out.println("3- Atualizar");
        System.out.println("4- Excluir");
        System.out.println("0- Voltar");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrar(Scanner sc) {

        if (!temPermissao()) {
            return;
        }

        System.out.println("\n===== CADASTRAR INSTRUTOR =====");

        System.out.print("Nome: ");
        String nome = Util.lerTexto(sc);

        System.out.print("CPF: ");
        String cpf = Util.lerTexto(sc);

        System.out.print("Telefone: ");
        String telefone = Util.lerTexto(sc);

        System.out.print("Especialidade: ");
        String especialidade = Util.lerTexto(sc);

        System.out.print("Horário: ");
        String horario = Util.lerTexto(sc);

        Instrutor instrutor = new Instrutor(
                nome,
                cpf,
                telefone,
                especialidade,
                horario
        );

        boolean cadastrado =
                service.cadastrar(instrutor);

        if (cadastrado) {

            System.out.println(
                    "\nInstrutor cadastrado com sucesso!"
            );

        } else {

            System.out.println(
                    "\nJá existe um instrutor com esse CPF!"
            );
        }
    }

    private void listar() {

        System.out.println(
                "\n===== LISTA DE INSTRUTORES ====="
        );

        if (service.listar().isEmpty()) {

            System.out.println(
                    "Nenhum instrutor cadastrado."
            );

            return;
        }

        for (Instrutor instrutor : service.listar()) {

            System.out.println(instrutor);
        }
    }

    private void excluir(Scanner sc) {

        if (!temPermissao()) {
            return;
        }

        System.out.println(
                "\n===== EXCLUIR INSTRUTOR ====="
        );

        System.out.print("CPF: ");
        String cpf = Util.lerTexto(sc);

        Instrutor instrutor =
                service.buscarPorCpf(cpf);

        if (instrutor == null) {

            System.out.println(
                    "\nInstrutor não encontrado!"
            );

            return;
        }

        System.out.print(
                "Deseja realmente excluir "
                + instrutor.getNome()
                + "? (S/N): "
        );

        String confirmacao =
                Util.lerTexto(sc);

        if (confirmacao.equalsIgnoreCase("N")) {

            System.out.println(
                    "\nExclusão cancelada."
            );

            return;
        }

        boolean removido =
                service.excluir(cpf);

        if (removido) {

            System.out.println(
                    "\nInstrutor removido com sucesso!"
            );

        } else {

            System.out.println(
                    "\nErro ao remover instrutor!"
            );
        }
    }

    private void atualizar(Scanner sc) {

        if (!temPermissao()) {
            return;
        }

        System.out.println(
                "\n===== ATUALIZAR INSTRUTOR ====="
        );

        System.out.print("CPF do instrutor: ");
        String cpf = Util.lerTexto(sc);

        Instrutor instrutor =
                service.buscarPorCpf(cpf);

        if (instrutor == null) {

            System.out.println(
                    "\nInstrutor não encontrado!"
            );

            return;
        }

        System.out.println(
                "\nDados atuais:"
        );

        System.out.println(instrutor);

        System.out.println("\n1- Nome");
        System.out.println("2- Telefone");
        System.out.println("3- Especialidade");
        System.out.println("4- Horário");
        System.out.println("5- Todos os dados");
        System.out.println("0- Cancelar");

        System.out.print(
                "Escolha uma opção: "
        );

        int opcao = Util.lerInteiro(sc);

        switch (opcao) {

            case 1:

                System.out.print(
                        "Novo nome: "
                );

                instrutor.setNome(
                        Util.lerTexto(sc)
                );

                break;

            case 2:

                System.out.print(
                        "Novo telefone: "
                );

                instrutor.setTelefone(
                        Util.lerTexto(sc)
                );

                break;

            case 3:

                System.out.print(
                        "Nova especialidade: "
                );

                instrutor.setEspecialidade(
                        Util.lerTexto(sc)
                );

                break;

            case 4:

                System.out.print(
                        "Novo horário: "
                );

                instrutor.setHorarioTrabalho(
                        Util.lerTexto(sc)
                );

                break;

            case 5:

                System.out.print(
                        "Novo nome: "
                );

                instrutor.setNome(
                        Util.lerTexto(sc)
                );

                System.out.print(
                        "Novo telefone: "
                );

                instrutor.setTelefone(
                        Util.lerTexto(sc)
                );

                System.out.print(
                        "Nova especialidade: "
                );

                instrutor.setEspecialidade(
                        Util.lerTexto(sc)
                );

                System.out.print(
                        "Novo horário: "
                );

                instrutor.setHorarioTrabalho(
                        Util.lerTexto(sc)
                );

                break;

            case 0:

                System.out.println(
                        "\nAtualização cancelada."
                );

                return;

            default:

                System.out.println(
                        "\nOpção inválida!"
                );

                return;
        }

        boolean atualizado =
                service.atualizar(instrutor);

        if (atualizado) {

            System.out.println(
                    "\nInstrutor atualizado com sucesso!"
            );

        } else {

            System.out.println(
                    "\nErro ao atualizar instrutor!"
            );
        }
    }

    private boolean temPermissao() {

        if (usuario.getTipo()
                .equalsIgnoreCase("GERENTE")

                ||

                usuario.getTipo()
                        .equalsIgnoreCase("RECEPCIONISTA")) {

            return true;
        }

        System.out.println(
                "\nAcesso negado!"
        );

        return false;
    }
}