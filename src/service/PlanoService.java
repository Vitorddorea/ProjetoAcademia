package service;

import entities.Plano;
import util.Util;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class PlanoService {

	public static ArrayList<Plano> listaPlanos = new ArrayList<>();

	public PlanoService()
	{
		Locale.setDefault(Locale.US);

		Plano plano1 = new Plano("plano A","correr em circulos", 1000.0f,2,"nao tem");
		Plano plano2 = new Plano("plano b","pular plantando bananeira", 2.4f,1,"fortalece os braços" );

		listaPlanos.add(plano1);
		listaPlanos.add(plano2);
	}

    public static Plano cadastrarPlano(Scanner sc) {

        Locale.setDefault(Locale.US);

        System.out.println("Nome do plano:");
        String nome = Util.lerTexto(sc);

        System.out.println("Descrição:");
        String descricao = Util.lerTexto(sc);

        System.out.print("Valor mensal: R$");
        double valorMensal = Util.lerReal(sc);

        int duracaoMeses;

        while (true) {
            System.out.print("Duração em meses (1 a 12): ");
            duracaoMeses = Util.lerInteiro(sc);

            if (duracaoMeses >= 1 && duracaoMeses <= 12) {
                break;
            }

            System.out.println("Duração deve ser entre 1 e 12 meses");
        }

        System.out.println("Benefícios: ");
        String beneficios = Util.lerTexto(sc);

        Plano plano = new Plano(nome, descricao, valorMensal, duracaoMeses, beneficios);

		listaPlanos.add(plano);

		System.out.println("-----------------------------");
		System.out.println(plano);
		System.out.println("-----------------------------");
        System.out.println("Plano cadastrado com sucesso!");
		System.out.println("-----------------------------");


        return plano;
	}

	public static void listarPlanos(){
		if (listaPlanos.isEmpty()) {
			System.out.println("A academia não possui nenhum plano!");
			return;
		}

		for(Plano p: listaPlanos) {
			System.out.println(p);
			System.out.println("-------------------------");
		}
	}

	public static void atualizarPlano(Scanner sc) {
	    if (listaPlanos.isEmpty()) {
			System.out.println("Nenhum plano cadastrado.");
			return;
		}
		System.out.println("Digite o nome do plano:");
		String nomeBusca = Util.lerTexto(sc);

		Plano planoEncontrado = null;

		for (Plano p : listaPlanos) {
			if (p.getNome().equalsIgnoreCase(nomeBusca)) {
				planoEncontrado = p;
				break;
			}
		}
		if (planoEncontrado == null) {
			System.out.println("Plano não encontrado!");
			return;
		}

		System.out.println("\n=== O que deseja atualizar? ===");
		System.out.println("1- Nome");
		System.out.println("2- Descrição");
		System.out.println("3- Valor Mensal");
		System.out.println("4- Duração (meses)");
		System.out.println("5- Benefícios");

		int opcao = Util.lerInteiro(sc);
		sc.nextLine();

		switch (opcao) {
			case 1:
				System.out.print("Novo nome: ");
				planoEncontrado.setNome(Util.lerTexto(sc));
				break;
			case 2:
				System.out.print("Nova descrição: ");
				planoEncontrado.setDescricao(Util.lerTexto(sc));
				break;
			case 3:
				System.out.print("Novo valor mensal: ");
				planoEncontrado.setValorMensal(Util.lerReal(sc));
				sc.nextLine();
				break;
			case 4:
				int novaDuracao;
				while (true) {
					System.out.print("Nova duração (1 a 12 meses): ");
					novaDuracao = Util.lerInteiro(sc);

					if (novaDuracao >= 1 && novaDuracao <= 12) {
						planoEncontrado.setDuracaoMeses(novaDuracao);
						break;
					}
					System.out.println("Valor inválido!");
				}
				sc.nextLine();
				break;
			case 5:
				System.out.print("Novos benefícios: ");
				planoEncontrado.setBeneficios(Util.lerTexto(sc));
				break;
			default:
				System.out.println("Opção inválida!");
				return;
		}

		System.out.println("Plano atualizado com sucesso!");

	}
	public static void excluirPlano(Scanner sc) {
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

	public static Plano buscarPlanoPorNome(String nome) {
		for (Plano plano : listaPlanos) {
			if (plano.getNome().equalsIgnoreCase(nome)) {
				return plano;
			}
		}
		return null;
	}
}
