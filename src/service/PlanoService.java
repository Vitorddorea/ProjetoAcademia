package service;

import entities.Plano;

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
        float valorMensal = sc.nextFloat();
        sc.nextLine();

        int duracaoMeses;

        while (true) {
            System.out.print("Duração em meses (1 a 12): ");
            duracaoMeses = sc.nextInt();

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

}
