package util;

import java.util.Scanner;

public class Util {

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