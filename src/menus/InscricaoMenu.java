package menus;

import entities.Inscricao;
import service.InscricaoService;
import util.Util;

import java.util.Scanner;

public class InscricaoMenu implements Menu{
    @Override
    public void exibir(Scanner sc) {

        while (true) {
            System.out.println("\n===== GERENCIAR INSCRIÇÕES ======");
            System.out.println("1- Nova inscrição");
            System.out.println("2- Cancelar inscrição");
            System.out.println("3- Listar inscrições");
            System.out.println("0- Voltar para o menu principal");
            System.out.println("=================================");

            System.out.println("Escolha uma opção:");
            int opcao = Util.lerInteiro(sc);

            switch (opcao) {
                case 1:
                    System.out.println(" ==== Realizar inscrição ====");
                    InscricaoService.realizarInscricao(sc);
                    break;
                case 2:
                    System.out.println(" ==== Cancelar inscrição ====");
                    InscricaoService.cancelarInscricao(sc);
                    break;
                case 3:
                    System.out.println(" ==== Lista de inscrições");
                    InscricaoService.listarInscricoes();
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
