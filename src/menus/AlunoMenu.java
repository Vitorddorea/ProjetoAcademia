package menus;

import entities.Aluno;
import entities.Aula;
import entities.Plano;
import entities.Usuario;
import service.AlunoService;

import service.PlanoService;
import exceptions.EntradaException;

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
    

    @Override
    public void exibir(Scanner sc) {

        while (true) {
            System.out.println("\n======== GERENCIAR ALUNO ========");
            System.out.println("1- Cadastrar aluno");
            System.out.println("2- Listar alunos");
            System.out.println("3- Atualizar aluno");
            System.out.println("4- Excluir aluno");
            System.out.println("0- Voltar");

            int opcao = EntradaException.lerInteiro(sc);

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
        String nome = EntradaException.lerTexto(sc);

        System.out.print("CPF: ");
        String cpf = EntradaException.lerTexto(sc);

        System.out.print("Telefone: ");
        String telefone = EntradaException.lerTexto(sc);

        System.out.print("Email: ");
        String email = EntradaException.lerTexto(sc);

        System.out.print("Data Nascimento (yyyy-MM-dd): ");
        LocalDate dataNascimento = LocalDate.parse(EntradaException.lerTexto(sc));

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
            System.out.println(" ");
            System.out.println("Aluno cadastrado com Sucesso!");
        } else {
            System.out.println("CPF já existe! tente novamente!");
            return;
        }
    }

   private void listar() {

    System.out.println(
        "\n===== LISTA DE ALUNOS ====="
    );

    if (service.listarAlunos().isEmpty()) {

        System.out.println(
            "Nenhum aluno cadastrado."
        );

        return;
    }

    for (Aluno aluno : service.listarAlunos()) {

        System.out.println(aluno);
    }
}
    public void listarAulasAluno(Scanner sc){
        String cpfAluno = EntradaException.lerTexto(sc);
        Aluno alunoEspecifico = service.buscarPorCpf(cpfAluno);

         if (alunoEspecifico != null) {
        } else {
            System.out.println("Aluno não encontrado.");
        }

            System.out.println("Suas Aulas: "+" ");
    }
    
    public void mostrarDadosAlunoEspecifico(Scanner sc) {

    System.out.println("\n===== RELATÓRIO DO ALUNO =====");

    System.out.print("Digite o CPF do aluno: ");
    String cpf = EntradaException.lerTexto(sc);

    Aluno aluno = service.buscarPorCpf(cpf);

    if (aluno == null) {
        System.out.println("Aluno não encontrado.");
        return;
    }

    System.out.println("\nNome: " + aluno.getNome());
    System.out.println("CPF: " + aluno.getCpf());
    System.out.println("Telefone: " + aluno.getTelefone());
    System.out.println("Email: " + aluno.getEmail());
    System.out.println("Data Nascimento: " + aluno.getDataNascimento());

    if (aluno.getPlanoAtivo() != null) {

        System.out.println(
            "Plano: " +
            aluno.getPlanoAtivo().getNome()
        );

    } else {
        System.out.println("Sem plano ativo.");
    }

    System.out.println("\n===== AULAS MATRICULADAS =====");

    if (aluno.getAulas().isEmpty()) {

        System.out.println("Nenhuma aula cadastrada.");

    } else {

        for (Aula aula : aluno.getAulas()) {

            System.out.println(
                "- " + aula.getNome()
            );
        }
      }
    }
    private void excluir(Scanner sc) {
        if (!temPermissao()) return;

        System.out.print("CPF: ");
        String cpf = EntradaException.lerTexto(sc);

        if (service.excluirAluno(cpf)) {
            System.out.print("Tem certeza que deseja excluir o aluno? (S/N): ");
            String confirmacao = EntradaException.lerTexto(sc);
            if (confirmacao.equalsIgnoreCase("S") || confirmacao.equalsIgnoreCase("SIM")) {
                System.out.println(" ");
                System.out.println("Aluno Removido com sucesso!");
            } else {
                System.out.println(" ");
                System.out.println("Operação cancelada.");
                return;
            }
        } else {
            System.out.println("Aluno não encontrado");
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

    System.out.println("\nO que você deseja atualizar?");
    System.out.println("1- Nome");
    System.out.println("2- Telefone");
    System.out.println("3- Email");
    System.out.println("4- Data de nascimento");
    System.out.println("5- Plano");
    System.out.println("6- Atualizar tudo");
    System.out.println("0- Voltar ao menu");

    int opcao = EntradaException.lerInteiro(sc);

    switch (opcao) {

        case 1:
            System.out.print("Novo nome: ");
            alunoExistente.setNome(EntradaException.lerTexto(sc));
            break;
        case 2:
            System.out.print("Novo telefone: ");
            alunoExistente.setTelefone(EntradaException.lerTexto(sc));
            break;
        case 3:
            System.out.print("Novo email: ");
            alunoExistente.setEmail(EntradaException.lerTexto(sc));
            break;
        case 4:
            System.out.print("Nova data de nascimento (yyyy-MM-dd): ");
            alunoExistente.setDataNascimento(
                LocalDate.parse(EntradaException.lerTexto(sc))
            );
            break;
        case 5:
            planoService.listarPlanos();

            System.out.print("Escolha um novo plano: ");
            Plano plano = planoService.escolherPlano(sc);

            alunoExistente.setPlanoAtivo(plano);
            break;
        case 6:
            System.out.print("Novo nome: ");
            alunoExistente.setNome(EntradaException.lerTexto(sc));

            System.out.print("Novo telefone: ");
            alunoExistente.setTelefone(EntradaException.lerTexto(sc));

            System.out.print("Novo email: ");
            alunoExistente.setEmail(EntradaException.lerTexto(sc));

            System.out.print("Nova data de nascimento (yyyy-MM-dd): ");
            alunoExistente.setDataNascimento(
                LocalDate.parse(EntradaException.lerTexto(sc))
            );

            planoService.listarPlanos();

            System.out.print("Escolha um novo plano: ");
            Plano novoPlano = planoService.escolherPlano(sc);

            alunoExistente.setPlanoAtivo(novoPlano);
            break;
        case 0:
            return;
        default:
            System.out.println("Opção inválida.");
            return;
    }
        service.atualizarAluno(alunoExistente);
    System.out.println("\nAluno atualizado com sucesso!");
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