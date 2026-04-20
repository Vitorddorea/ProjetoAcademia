package entities;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;


public class Aluno extends Pessoa{

    private String dataNascimento; // confirmar
    private String email;
    private LocalDate dataMatricula;
    private String planoAtivo;

    public Aluno(String nome, String cpf, String dataNascimento, String telefone, String email,  String planoAtivo) {
        super(nome, cpf, telefone);
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.dataMatricula = LocalDate.now();
        this.planoAtivo = planoAtivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf() {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public String getPlanoAtivo() {
        return planoAtivo;
    }

    public void setPlanoAtivo(String planoAtivo) {
        this.planoAtivo = planoAtivo;
    }

    public void mostrarAluno(){
        System.out.println("Aluno: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Data de Nascimento: " + this.dataNascimento);
        System.out.println("Telefone: " + this.telefone);
        System.out.println("E-mail: " + this.email);
        System.out.println("Plano ativo: " + this.planoAtivo);
    }

<<<<<<< Updated upstream
=======
        for (Aluno aluno : listaAlunos) {
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("CPF: " + aluno.getCpf());
            System.out.println("Telefone: " + aluno.getTelefone());
            System.out.println("Email: " + aluno.getEmail());
            System.out.println("Plano ativo: " + aluno.getPlanoAtivo());
            System.out.println("-------------------------");
        }
    }
    public static void excluirAluno(ArrayList<Aluno> listaAlunos, String cpf) {

        for (int i = 0; i < listaAlunos.size(); i++) {
            if (listaAlunos.get(i).getCpf().equals(cpf)) {
                listaAlunos.remove(i);
                System.out.println("Aluno removido com sucesso.");
                return;
            }
        }

        System.out.println("Aluno não encontrado.");
    }
    public static void atualizarAluno(Scanner sc, ArrayList<Aluno> listaAlunos) {

        System.out.print("Digite o CPF do aluno que deseja atualizar: ");
        String cpf = sc.nextLine();

        for (Aluno aluno : listaAlunos) {
            if (aluno.getCpf().equals(cpf)) {

                System.out.print("Novo nome: ");
                aluno.setNome(sc.nextLine());

                System.out.print("Novo telefone: ");
                aluno.setTelefone(sc.nextLine());

                System.out.print("Novo email: ");
                aluno.setEmail(sc.nextLine());
                
                //ajustar futuramente
                System.out.print("Novo plano: ");
                aluno.setPlanoAtivo(sc.nextLine());

                System.out.println("Aluno atualizado com sucesso.");
                return;
            }
        }
        System.out.println("Aluno não encontrado.");
    }
>>>>>>> Stashed changes
}
