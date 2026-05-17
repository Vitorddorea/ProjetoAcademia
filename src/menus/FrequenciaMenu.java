package menus;

import entities.Frequencia;
import entities.Usuario;
import service.FrequenciaService;
import util.Util;

import java.util.Scanner;

public class FrequenciaMenu implements Menu {

    private Usuario usuario;
    private FrequenciaService service;

    public FrequenciaMenu(Usuario usuario, FrequenciaService service) {
        this.usuario = usuario;
        this.service = service;
    }

    @Override
    public void exibir(Scanner sc) {
        while (true) {
            System.out.println("\n==== FREQUÊNCIA ====");
            System.out.println("1- Registrar presença");
            System.out.println("2- Listar frequências");
            System.out.println("0- Voltar");

            int op = Util.lerInteiro(sc);

            switch (op) {
                case 1: Cadastrar(sc); break;
                case 2: Listar();break;

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void Cadastrar(Scanner sc) {
        if (!temPermissao()) return;

        System.out.println("Teste cadastrar");

        System.out.print("CPF: ");
        String cpf = Util.lerTexto(sc);

        System.out.print("Aula: ");
        String aula = Util.lerTexto(sc);

        System.out.print("Presente (S/N): ");
        String resp = Util.lerTexto(sc);

        boolean presente = resp.equalsIgnoreCase("S");

        service.registrar(cpf, aula, presente);
    }

    private void Listar() {
        System.out.println(
            "\n===== LISTA DE FREQUENCIA ====="
        );

        var frequencias = service.listar();

        if (frequencias.isEmpty()) { System.out.println("Nenhuma frequencia cadastrada."); return; }

        for (var frequencia : frequencias) {
            System.out.println(frequencia);
        }
    }

    private boolean temPermissao() {
        if (usuario.getTipo().equalsIgnoreCase("GERENTE") ||
            usuario.getTipo().equalsIgnoreCase("RECEPCIONISTA")) {
            return true;
        }

        System.out.println("Acesso negado!");
        return false;
    }
}