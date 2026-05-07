package menus;

import entities.Aluno;
import entities.Plano;
import entities.Usuario;
import service.AlunoService;

import service.PlanoService;
import util.Util;



import java.time.LocalDate;
import java.util.Scanner;

public class AlunoMenu implements Menu {

    private Usuario usuario;
    private AlunoService service;
    private PlanoService planoService;

    public AlunoMenu(Usuario usuario, AlunoService service, PlanoService planoService) {
    this.usuario = usuario;
    this.service = service;
    this.planoService = planoService;
}
    public AlunoMenu(Usuario usuario, AlunoService service) {
        this.usuario = usuario;
        this.service = service;
    }

    @Override
    public void exibir(Scanner sc) {

        while (true) {
            System.out.println("\n======== GERENCIAR ALUNO ========");
            System.out.println("1- Cadastrar aluno");
            System.out.println("2- Listar alunos");
            System.out.println("3- Atualizar aluno");
            System.out.println("4- Excluir aluno");
            System.out.println("0- Voltar");

            int opcao = Util.lerInteiro(sc);

            switch (opcao) {
                case 1: cadastrar(sc, null); break;
                case 2: listar(); break;
                case 3: atualizar(sc); break;
                case 4: excluir(sc); break;
                case 0: return;
                default: System.out.println("Opção inválida");
            }
        }
    }

    private void cadastrar(Scanner sc, Plano planoEscolhido) {
        if (!temPermissao()) return;

        System.out.print("Nome: ");
        String nome = Util.lerTexto(sc);

        System.out.print("CPF: ");
        String cpf = Util.lerTexto(sc);

        System.out.print("Telefone: ");
        String telefone = Util.lerTexto(sc);

        System.out.print("Email: ");
        String email = Util.lerTexto(sc);

        System.out.print("Data Nascimento (yyyy-MM-dd): ");
        LocalDate dataNascimento = LocalDate.parse(Util.lerTexto(sc));

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
            System.out.println("Aluno cadastrado!");
        } else {
            System.out.println("CPF já existe!");
        }
    }

    private void listar() {
        for (Aluno a : service.listarAlunos()) {
            System.out.println(a);
        }
    }

    private void excluir(Scanner sc) {
        if (!temPermissao()) return;

        System.out.print("CPF: ");
        String cpf = Util.lerTexto(sc);

        if (service.excluirAluno(cpf)) {
            System.out.println("Removido!");
        } else {
            System.out.println("Aluno não encontrado");
        }
    }

    private void atualizar(Scanner sc) {
        if (!temPermissao()) return;

        System.out.print("CPF do aluno: ");
        String cpf = Util.lerTexto(sc);

        System.out.print("Novo nome: ");
        String nome = Util.lerTexto(sc);

        System.out.print("Telefone: ");
        String telefone = Util.lerTexto(sc);

        System.out.print("Email: ");
        String email = Util.lerTexto(sc);

        System.out.print("Data nascimento (yyyy-MM-dd): ");
        LocalDate dataNascimento = LocalDate.parse(Util.lerTexto(sc));

        Plano plano = planoService.escolherPlano(sc);

        Aluno aluno = new Aluno(nome, cpf, dataNascimento, telefone, email, plano);

        if (service.atualizarAluno(aluno)) {
            System.out.println("Atualizado!");
        } else {
            System.out.println("Aluno não encontrado");
        }
    }

    private boolean temPermissao() {
        if (usuario.getTipo().equalsIgnoreCase("GERENTE") ||
            usuario.getTipo().equalsIgnoreCase("RECEPCIONISTA")) {
            return true;
        }

        System.out.println("Acesso negado!");
        return false;
    }
}