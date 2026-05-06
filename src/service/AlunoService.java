package service;

import entities.Usuario;
import entities.Aluno;
import entities.Plano;
import util.Util;

import java.util.ArrayList;
import java.util.Scanner;


public class AlunoService {
    private static final ArrayList<Aluno> listaAlunos = new ArrayList<> ();

    public static Aluno cadastrarAluno(Scanner sc, Usuario usuario) {

    if (!temPermissao(usuario)) {
            System.out.println("Acesso negado.");
            return null;
        }
    System.out.println(" ");
    System.out.println("Deseja realmente cadastrar um aluno?");
    System.out.print("Digite 'SIM' para continuar ou 'NÃO' para voltar: ");
    String resposta = sc.nextLine();

    if(resposta.equalsIgnoreCase("NÃO") || resposta.equalsIgnoreCase("NAO")){
        System.out.println("Voltando ao menu...");
        return null;        
    }

    System.out.println(" ");
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

    System.out.println("\nNossos Planos:");
    PlanoService.listarPlanos();

    System.out.print("Escolha um plano: ");
    String nomePlano = Util.lerTexto(sc);

    Plano planoEscolhido = PlanoService.buscarPlanoPorNome(nomePlano);

    if (planoEscolhido == null) {
        System.out.println("Plano não encontrado.");
        return null;
    }

    Aluno aluno = new Aluno(nome, cpf, dataNascimento, telefone, email, planoEscolhido);

    listaAlunos.add(aluno);

    System.out.println("Aluno cadastrado com sucesso!");

    return aluno;
}
    public static void listarAlunos(Usuario usuario) {

      if (!temPermissao(usuario)) {
            System.out.println("Acesso negado.");
            return ;
        }

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

    public static void excluirAluno(Scanner sc, Usuario usuario) {

        if (!temPermissao(usuario)) {
            System.out.println("Acesso negado.");
            return;
        }

        if (listaAlunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        listarAlunos(usuario);

        System.out.print("Digite o ID cpf do aluno: ");
        String cpf = Util.lerTexto(sc);

        for (int i = 0; i < listaAlunos.size(); i++) {
            if (listaAlunos.get(i).getCpf().equalsIgnoreCase(cpf)) {
                listaAlunos.remove(i);
                System.out.println(" ");
                System.out.println("Aluno encontrado: " + listaAlunos.get(i).getNome());

                System.out.println("Deseja mesmo remover esse Aluno?");
                System.out.print("Digite 'SIM' para confirmar ou 'NÃO' para cancelar: ");
                String resposta2 = sc.nextLine();
                if(resposta2.equalsIgnoreCase("NÃO") || resposta2.equalsIgnoreCase("NAO")){
                    System.out.println("Remoção cancelada. Voltando ao menu...");
                    return;        
                }
                System.out.println("Aluno removido com sucesso.");
                return;
            }
        }

        System.out.println("Aluno não encontrado.");
    }

    public static void atualizarAluno(Scanner sc, Usuario usuario) {

        if (!temPermissao(usuario)) {
        System.out.println("Acesso negado.");
        return;
}
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
                System.out.println("\nNossos Planos:");
                PlanoService.listarPlanos();

                System.out.print("Novo plano ativo: ");
                String nomePlano = Util.lerTexto(sc);

                Plano planoEscolhido = PlanoService.buscarPlanoPorNome(nomePlano);

            if (planoEscolhido == null) {
            System.out.println("Plano não encontrado.");
                return;
            }
            alunoEncontrado.setPlanoAtivo(planoEscolhido);
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

                System.out.println("\nNossos Planos:");
                PlanoService.listarPlanos();

                System.out.print("Escolha um plano: ");
                String nomePlano1 = Util.lerTexto(sc);

                Plano planoEscolhido1 = PlanoService.buscarPlanoPorNome(nomePlano1);

            if (planoEscolhido1 == null) {
            System.out.println("Plano não encontrado.");
                return;
            }
            alunoEncontrado.setPlanoAtivo(planoEscolhido1);
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
    private static boolean temPermissao(Usuario usuario) {
    return usuario.getTipo().equalsIgnoreCase("GERENTE") ||
           usuario.getTipo().equalsIgnoreCase("RECEPCIONISTA");
}
}
