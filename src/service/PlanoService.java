package service;

import entities.Plano;
import repositories.PlanoDAO;

import java.util.List;
import java.util.Scanner;

public class PlanoService {

    private final PlanoDAO repository;
    

    public PlanoService(PlanoDAO repository) {
        this.repository = repository;
    }

    public boolean cadastrarPlano(Plano plano) {
        if (repository.buscarPorNome(plano.getNome()) != null) {
            return false;
        }
        return repository.salvar(plano);
    }

    public List<Plano> listarPlanos() {
        return repository.listar();
    }

    public boolean excluirPlano(String nome) {
        return repository.remover(nome);
    }

    public boolean atualizarPlano(Plano novo) {
        return repository.atualizar(novo);
    }

    public Plano buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }

	public Plano escolherPlano(Scanner sc) {

    System.out.println("\nPLANOS DISPONÍVEIS:");

    for (Plano p : listarPlanos()) {
        System.out.println(" - " + p.getNome()+" - "+ p.getDescricao()+ " - R$" + p.getValorMensal()+ " " + " por mês, duração: "+ " " + p.getDuracaoMeses()+ " " + " meses, benefícios: " + p.getBeneficios());
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
    }

    System.out.print("Escolha um plano: ");
    String nome = sc.nextLine();

    return buscarPorNome(nome);
}
}