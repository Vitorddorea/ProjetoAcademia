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
}