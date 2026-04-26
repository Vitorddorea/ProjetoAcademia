package util;

import java.util.Scanner;

public class Util {

    // Função para ler a entrada de número inteiros
    public static int lerInteiro(Scanner sc) {

        while (true){
            try {
                int opcao = sc.nextInt();
                sc.nextLine();

                return opcao;

            } catch (Exception e) {
                System.out.println("Digite um número inteiro!");
                sc.nextLine();
            }
        }
    }

    // Função para ler a entrada de números reais
    public static double lerReal (Scanner sc) {

        while (true){
            try {
                double real = sc.nextDouble();
                sc.nextLine();

                return real;

            } catch (Exception e) {
                System.out.println("Digite um número real!");
                sc.nextLine();
            }
        }
    }


    // Função para ler entrada de texto
    public static String lerTexto(Scanner sc) {

        while (true) {
            String texto = sc.nextLine();

            if (texto != null && !texto.trim().isEmpty()) {
                return texto;
            }

            System.out.println("Digite algo válido!");
        }
    }
}