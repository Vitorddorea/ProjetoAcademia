package service;

import entities.Aluno;
import entities.Plano;
import util.Util;

import java.util.ArrayList;
import java.util.Scanner;

public class AlunoService {
    private static final ArrayList<Aluno> listaAlunos = new ArrayList<> ();

    public static Aluno cadastrarAluno(Scanner sc) {

        System.out.println("Nome do Aluno:");
        String nome = Util.lerTexto(sc);

        System.out.println("CPF:");
        String cpf = Util.lerTexto(sc);

        System.out.println("Data de Nascimento (xx/xx/xxxx):");
        String dataNascimento = Util.lerTexto(sc);

        System.out.println("Telefone:");
        String telefone = Util.lerTexto(sc);

        System.out.println("E-mail:");
        String email = Util.lerTexto(sc);

        System.out.println("Escolha um plano:");
        PlanoService.listarPlanos();

        String nomePlano = Util.lerTexto(sc);

        Plano planoEscolhido = PlanoService.buscarPlanoPorNome(nomePlano);

        if (planoEscolhido == null) {
            System.out.println("Plano não encontrado.");
            return null;
        }

        Aluno aluno = new Aluno(nome, cpf, dataNascimento, telefone, email, planoEscolhido);

        listaAlunos.add(aluno);

        System.out.println("--------------------------------");
        System.out.println(aluno);
        System.out.println("--------------------------------");
        System.out.println("Aluno(a) cadastrado com sucesso!");
        System.out.println("--------------------------------");

        return aluno;
    }

    public static void listarAlunos() {

        if (listaAlunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("\n==== LISTA DE ALUNOS =====");

        for (Aluno aluno : listaAlunos) {
            System.out.println(aluno);
            System.out.println("-------------------------");
        }
    }

    public static void excluirAluno(Scanner sc) {

        if (listaAlunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        listarAlunos();

        System.out.print("Digite o ID cpf do aluno: ");
        String cpf = Util.lerTexto(sc);

        for (int i = 0; i < listaAlunos.size(); i++) {
            if (listaAlunos.get(i).getCpf().equalsIgnoreCase(cpf)) {
                listaAlunos.remove(i);
                System.out.println("Aluno removido com sucesso.");
                return;
            }
        }

        System.out.println("Aluno não encontrado.");
    }

    public static void atualizarAluno(Scanner sc) {

        if (listaAlunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        System.out.print("Digite o CPF do aluno que deseja atualizar: ");
        String cpf = sc.nextLine();
        Aluno alunoEncontrado = null;

        for (Aluno aluno : listaAlunos){
            if (aluno.getCpf().equals(cpf)){
                alunoEncontrado = aluno;
                break;
            }
        }
        if (alunoEncontrado == null){
            System.out.println("Aluno não encontrado.");
            return;

        }
        System.out.println("\n=== O que deseja atualizar? ===");
        System.out.println("1- Nome");
        System.out.println("2- CPF");
        System.out.println("3- Telefone");
        System.out.println("4- Data de Nascimento");
        System.out.println("5- Email");
        System.out.println("6- Plano Ativo");
        System.out.println("7- Atualizar tudo");

        int opcao = Util.lerInteiro(sc);

        switch (opcao) {
            case 1:
                System.out.print("Novo nome: ");
                alunoEncontrado.setNome(Util.lerTexto(sc));
                break;

            case 2:
                System.out.print("Novo CPF: ");
                alunoEncontrado.setCpf(Util.lerTexto(sc));
                break;

            case 3:
                System.out.print("Novo telefone: ");
                alunoEncontrado.setTelefone(Util.lerTexto(sc));
                break;

            case 4:
                System.out.print("Nova data de nascimento: ");
                alunoEncontrado.setDataNascimento(Util.lerTexto(sc));
                break;

            case 5:
                System.out.print("Novo email: ");
                alunoEncontrado.setEmail(Util.lerTexto(sc));
                break;

            case 6:
                System.out.print("Novo plano ativo: ");
                //alunoEncontrado.setPlanoAtivo(Util.lerTexto(sc));
                break;

            case 7:
                System.out.print("Novo nome: ");
                alunoEncontrado.setNome(Util.lerTexto(sc));

                System.out.print("Novo CPF: ");
                alunoEncontrado.setCpf(Util.lerTexto(sc));

                System.out.print("Nova data de nascimento: ");
                alunoEncontrado.setDataNascimento(Util.lerTexto(sc));

                System.out.print("Novo telefone: ");
                alunoEncontrado.setTelefone(Util.lerTexto(sc));

                System.out.print("Novo email: ");
                alunoEncontrado.setEmail(Util.lerTexto(sc));

                System.out.print("Novo plano ativo: ");
                //alunoEncontrado.setPlanoAtivo(Util.lerTexto(sc));
                break;

            default:
                System.out.println("Opção inválida.");
                return;
        }
        System.out.println("Aluno atualizado com sucesso.");
    }

    public static Aluno buscarPorCpf(String cpf) { // trabalhar usando isso
        for (Aluno aluno : listaAlunos) {
            if (aluno.getCpf().equals(cpf)) {
                return aluno;
            }
        }
        return null;
    }
}
