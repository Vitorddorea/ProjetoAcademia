package menus;

import entities.*;
import service.AlunoService;
import service.PlanoService;
import service.InscricaoService;
import exceptions.EntradaException;

import java.time.LocalDate;
import java.util.Scanner;

public class AlunoMenu implements Menu {

    private Usuario usuario;
    private AlunoService service;
    private PlanoService planoService;
    private InscricaoService inscricaoService;

    public AlunoMenu(
            Usuario usuario,
            AlunoService service,
            PlanoService planoService,
            InscricaoService inscricaoService
    ) {
        this.usuario = usuario;
        this.service = service;
        this.planoService = planoService;
        this.inscricaoService = inscricaoService;
    }

    @Override
    public void exibir(Scanner sc) {

        while (true) {
            System.out.println("\n========== GERENCIAR ALUNO ============");
            System.out.println("1- Cadastrar aluno");
            System.out.println("2- Listar alunos");
            System.out.println("3- Atualizar aluno");
            System.out.println("4- Excluir aluno");
            System.out.println("5- Detalhes do aluno");
            System.out.println("0- Voltar");
            System.out.println("=======================================");
            System.out.print("Escolha uma opção: ");

            int opcao = EntradaException.lerInteiro(sc);

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
                    detalharAluno(sc);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void cadastrar(Scanner sc) {
        if (!temPermissao()) return;

        System.out.print("Nome: ");
        String nome = EntradaException.lerTexto(sc);

        System.out.print("CPF: ");
        String cpf = EntradaException.lerTexto(sc);

        System.out.print("Telefone: ");
        String telefone = EntradaException.lerTexto(sc);

        System.out.print("Email: ");
        String email = EntradaException.lerTexto(sc);

        System.out.print("Data Nascimento (yyyy-MM-dd): ");
        LocalDate dataNascimento =
                LocalDate.parse(EntradaException.lerTexto(sc));

        Plano plano = planoService.escolherPlano(sc);

        Aluno aluno = new Aluno(
                nome,
                cpf,
                dataNascimento,
                telefone,
                email,
                plano
        );

        if (service.cadastrarAluno(aluno)) {
            System.out.println("\nAluno cadastrado com sucesso!");
        } else {
            System.out.println("CPF já existe!");
        }
    }

    private void listar() {

        System.out.println("\n===== LISTA DE ALUNOS =====");

        if (service.listarAlunos().isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        for (Aluno aluno : service.listarAlunos()) {
            System.out.println(aluno);
        }
    }

    private void excluir(Scanner sc) {
        if (!temPermissao()) return;

        System.out.print("CPF: ");
        String cpf = EntradaException.lerTexto(sc);

        Aluno aluno = service.buscarPorCpf(cpf);

        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.print("Deseja realmente excluir? (S/N): ");
        String confirmacao = EntradaException.lerTexto(sc);

        if (confirmacao.equalsIgnoreCase("S")) {

            if (service.excluirAluno(cpf)) {
                System.out.println("Aluno removido com sucesso!");
            }

        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private void atualizar(Scanner sc) {
        if (!temPermissao()) return;

        System.out.print("CPF do aluno: ");
        String cpf = EntradaException.lerTexto(sc);

        Aluno alunoExistente = service.buscarPorCpf(cpf);

        if (alunoExistente == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.println("\n1- Nome");
        System.out.println("2- Telefone");
        System.out.println("3- Email");
        System.out.println("4- Data nascimento");
        System.out.println("5- Plano");
        System.out.println("6- Atualizar tudo");
        System.out.println("0- Cancelar");

        int opcao = EntradaException.lerInteiro(sc);

        switch (opcao) {

            case 1:
                System.out.print("Novo nome: ");
                alunoExistente.setNome(
                        EntradaException.lerTexto(sc)
                );
                break;

            case 2:
                System.out.print("Novo telefone: ");
                alunoExistente.setTelefone(
                        EntradaException.lerTexto(sc)
                );
                break;

            case 3:
                System.out.print("Novo email: ");
                alunoExistente.setEmail(
                        EntradaException.lerTexto(sc)
                );
                break;

            case 4:
                System.out.print(
                        "Nova data nascimento (yyyy-MM-dd): "
                );
                alunoExistente.setDataNascimento(
                        LocalDate.parse(
                                EntradaException.lerTexto(sc)
                        )
                );
                break;

            case 5:
                Plano novoPlano =
                        planoService.escolherPlano(sc);
                alunoExistente.setPlanoAtivo(novoPlano);
                break;

            case 6:
                System.out.print("Novo nome: ");
                alunoExistente.setNome(
                        EntradaException.lerTexto(sc)
                );

                System.out.print("Novo telefone: ");
                alunoExistente.setTelefone(
                        EntradaException.lerTexto(sc)
                );

                System.out.print("Novo email: ");
                alunoExistente.setEmail(
                        EntradaException.lerTexto(sc)
                );

                System.out.print(
                        "Nova data nascimento (yyyy-MM-dd): "
                );
                alunoExistente.setDataNascimento(
                        LocalDate.parse(
                                EntradaException.lerTexto(sc)
                        )
                );

                Plano planoNovo =
                        planoService.escolherPlano(sc);
                alunoExistente.setPlanoAtivo(planoNovo);
                break;

            case 0:
                return;

            default:
                System.out.println("Opção inválida.");
                return;
        }

        service.atualizarAluno(alunoExistente);
        System.out.println("Aluno atualizado com sucesso!");
    }

    private void detalharAluno(Scanner sc) {
        System.out.print("CPF do aluno: ");
        String cpf = EntradaException.lerTexto(sc);

        service.exibirDetalhesAluno(
                cpf,
                inscricaoService
        );
    }

    private boolean temPermissao() {
        return usuario.getTipo() == TipoUsuario.ADMINISTRADOR
                || usuario.getTipo() == TipoUsuario.FUNCIONARIO;
    }
}