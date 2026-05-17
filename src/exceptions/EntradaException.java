package exceptions;

import java.util.Scanner;

public class EntradaException {

    // Função para ler a entrada de número inteiros
    public static int lerInteiro(Scanner sc) {

        while (true){
            try {
                int valor = sc.nextInt();
                sc.nextLine();

                if (valor < 0) {
                    System.out.println("Digite um valor maior ou igual a 0");
                    continue;
                }

                return valor;

            } catch (Exception e) {
                System.out.println("Digite um número válido!");
                sc.nextLine();
            }
        }
    }

    // Função para ler a entrada de números reais
    public static double lerReal (Scanner sc) {

        while (true){
            try {
                double valor = sc.nextDouble();
                sc.nextLine();

                if (valor < 0) {
                    System.out.println("Digite um valor maior ou igual a 0");
                    continue;
                }

                return valor;

            } catch (Exception e) {
                System.out.println("Digite um número válido!");
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