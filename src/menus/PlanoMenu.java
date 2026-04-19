package menus;

import entities.Plano;
import service.PlanoService;
import util.Util;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class PlanoMenu implements Menu{

    ArrayList<Plano> listaPlanos = new ArrayList<>();

    public PlanoMenu(){
        Plano plano1 = new Plano("plano A","correr em circulos", 1000.0f,2,"nao tem");
        Plano plano2 = new Plano("plano b","pular plantando bananeira", 2.4f,1,"fortalece os braços" );

        listaPlanos.add(plano1);
        listaPlanos.add(plano2);
    }



    @Override
    public void exibir(Scanner sc) {

        Locale.setDefault(Locale.US);

        while (true){

            System.out.println("\n==== GERENCIAR PLANO ====");
            System.out.println("1- Cadastrar novo plano");
            System.out.println("2- Listar planos");
            System.out.println("3- Atualizar plano");
            System.out.println("4- Excluir plano");
            System.out.println("0- Voltar para o menu principal");
            System.out.println("Escolha uma opção: ");
            int opcao = Util.lerInteiro(sc);

            switch (opcao) {
                case 1:
                    Plano plano = PlanoService.cadastrarPlano(sc);
                    listaPlanos.add(plano);
                    break;
                case 2:
                    //Plano.listarPlanos(listaPlanos);
                    break;
                case 3:
                    System.out.println("Atualizar instrutor:");
                    //Plano.atualizarPlano(sc, listaPlanos);
                    break;
                case 4:
                    System.out.println("Excluir aluno:");
                    //System.out.print("Nome Plano: ");

                    //Plano.excluirPlano(listaPlanos);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
