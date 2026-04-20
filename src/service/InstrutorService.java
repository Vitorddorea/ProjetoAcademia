package service;

import entities.Instrutor;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class InstrutorService {

    public static Instrutor cadastrarInstrutor(Scanner sc) {

        Locale.setDefault(Locale.US);

        System.out.println("Nome do Instrutor: ");
        String nome = sc.nextLine();

        System.out.println("CPF:");
        String cpf = sc.nextLine();

        System.out.println("Telefone:");
        String telefone = sc.nextLine();

        System.out.println("Especialidade:");
        String especialidade = sc.nextLine();

        System.out.println("Horário de trabalho:");
        String horarioTrabalho = sc.nextLine();

        Instrutor instrutor = new Instrutor(nome, cpf, telefone, especialidade, horarioTrabalho);

        System.out.println("Instrutor cadastrado com sucesso!");
        instrutor.mostrarInstrutor();
		return instrutor;
        
        }
    public static void listarInstrutores(Scanner sc, ArrayList<Instrutor> listaInstrutores){
    	
    	if (listaInstrutores.isEmpty()) {
            System.out.println("Nenhum instrutor cadastrado.");
            return;
        }
    	else{
    		for (Instrutor i: listaInstrutores){
    			System.out.println(i);
    		}
    	}
    }
    
    public static void atualizarInstrutor(Scanner sc, ArrayList<Instrutor> listaInstrutor) {

        System.out.println("Digite o CPF do instrutor:");
        String cpfInstrutor = sc.nextLine();

        for (Instrutor i : listaInstrutor) {
            if (i.getCpf().equals(cpfInstrutor)) {

                System.out.println("O que deseja alterar?");
                System.out.println("1- Nome");
                System.out.println("2- CPF");
                System.out.println("3- Telefone");
                System.out.println("4- Especialidade");

                int opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("Novo nome:");
                        i.setNome(sc.nextLine());
                        break;

                    case 2:
                        System.out.println("Novo CPF:");
                        i.setCpf(sc.nextLine());
                        break;

                    case 3:
                        System.out.println("Novo telefone:");
                        i.setTelefone(sc.nextLine());
                        break;

                    case 4:
                        System.out.println("Nova especialidade:");
                        i.setEspecialidade(sc.nextLine());
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

                System.out.println("Atualizações realizadas com sucesso!");
                return;
            }
        }

        System.out.println("Instrutor não encontrado.");
    }
    public static void excluirInstrutor(Scanner sc, ArrayList<Instrutor> listaInstrutor){
    	if (listaInstrutor.isEmpty()){
    		System.out.println("A lista de Instrutores está vazia!");
    		return;
    	}
    	System.out.println("Digite o cpf do  Instrutor para localiza-lo");
    	String cpf = sc.nextLine();
    	
    	 for (int i = 0; i < listaInstrutor.size(); i++){
    	        if (listaInstrutor.get(i).getCpf().equals(cpf)){
    	            listaInstrutor.remove(i);
    	            System.out.println("Instrutor removido com sucesso!");
    	            return;
    	        }
    	    }
    		System.out.println("Intrutor não encontrado, tente novamente!");
    		
    	}
    	
    
}


