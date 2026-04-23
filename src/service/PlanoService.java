package service;

import entities.Aluno;
import entities.Plano;
import util.Util;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class PlanoService {

    public static Plano cadastrarPlano(Scanner sc) {

        Locale.setDefault(Locale.US);

        System.out.println("Nome do plano:");
        String nome = sc.nextLine();

        System.out.println("Descrição:");
        String descricao = sc.nextLine();

        System.out.print("Valor mensal: R$");
        float valorMensal = Util.lerReal(sc);

        int duracaoMeses;

        while (true) {
            System.out.print("Duração em meses (1 a 12): ");
            duracaoMeses = Util.lerInteiro(sc);

            if (duracaoMeses >= 1 && duracaoMeses <= 12) {
                break;
            }

            System.out.println("Duração deve ser entre 1 e 12 meses");
        }
        sc.nextLine();

        System.out.println("Benefícios: ");
        String beneficios = sc.nextLine();

        Plano plano = new Plano(nome, descricao, valorMensal, duracaoMeses, beneficios);


        System.out.println("Plano cadastrado com sucesso!");

        return plano;
        }
    	public static void listarPlanos(Scanner sc, ArrayList<Plano> listaPlanos){
    		if (listaPlanos.isEmpty()){
    			System.out.println("A academia não possui nenhum plano!");
    			return;
    		}else {
    			for(Plano p: listaPlanos) {
    				System.out.println(p);
    			}
    		}
    }
    	public static void atualizarPlano(Scanner sc, ArrayList<Plano> listaPlanos) {
    	    System.out.println("Digite o nome do plano:");
    	    String nomeBusca = sc.nextLine();

    	    for (Plano p : listaPlanos) {
    	        if (p.getNome().equalsIgnoreCase(nomeBusca)) {
    	        	System.out.println("Plano Encontrado: ");
    	        	System.out.println("O que você deseja atualizar?");
    	        	
    	        	System.out.println("Digite 1 para *Nome*");
    	        	System.out.println("Digite 2 para *Descrição*");
    	        	System.out.println("Digite 3 para *Valor Mensal*");
    	        	System.out.println("Digite 4 para *Duração do Plano*");
    	        	System.out.println("Digite 5 para *Benefícios*");
    	        	
    	        	int opcao = sc.nextInt();
    	        	sc.nextLine();
    	        	switch (opcao) {
    	        	case 1:
    	        		 System.out.println("Novo nome:");
    	    	         String novoNome = sc.nextLine();
    	    	         p.setNome(novoNome);
    	    	         System.out.println("Nome do Plano atualizado!");
    	    	         break;
    	        	case 2:
    	        		System.out.println("Nova Descrição:");
    	        		String novaDescricao = sc.nextLine();
    	        		p.setDescricao(novaDescricao);
    	        		System.out.println("Descrição do plano atualizado!");
    	        		break;	
    	        	case 3:
    	        		System.out.println("Novo valor mensal:");
    	        		float novoValorMensal = sc.nextFloat();
    	        		sc.nextLine();
    	        		p.setValorMensal(novoValorMensal);
    	        		System.out.println("Valor Mensal do plano atualizado!");
    	        		break;	
    	        	case 4:
    	        		System.out.println("Nova Duração do plano:");
    	        		int novaDuracao = sc.nextInt();
    	        		sc.nextLine();
    	        		p.setDuracaoMeses(novaDuracao);
    	        		System.out.println("Duração em meses do plano atualizado!");
    	        		break;	
    	        	case 5:
    	        		System.out.println("Novos beneficios:");
    	        		String novoBeneficios = sc.nextLine();
    	        		p.setBeneficios(novoBeneficios);
    	        		System.out.println("Beneficios do plano atualizado!");
    	        		break;	
    	        	case 0:
    	        		return;
    	        	default:
    	        		System.out.println("Opção Inválida! tente novamente!");
    	        }
    	    }
    	    
    	}
    
    }
    	public static void excluirPlano(Scanner sc, ArrayList<Plano> listaPlanos) {
    	    System.out.println("Digite o nome do plano que deseja excluir:");
    	    String nome = sc.nextLine();

    	    for (int i = 0; i < listaPlanos.size(); i++) {
    	        if (listaPlanos.get(i).getNome().equalsIgnoreCase(nome)) {
    	            listaPlanos.remove(i);
    	            System.out.println("Plano removido com sucesso!");
    	            return;
    	        }
    	    }
    	    System.out.println("Plano não encontrado!");
    	}
}
