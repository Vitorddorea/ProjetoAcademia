package service;

import entities.Plano;
import repositories.PlanoRepository;

import java.util.List;
import java.util.Scanner;

public class PlanoService {

    private final PlanoRepository repository;
    

    public PlanoService(PlanoRepository repository) {
        this.repository = repository;
    }

    public boolean cadastrarPlano(Plano plano) {
        if (repository.buscarPorNome(plano.getNome()) != null) {
            return false;
        }
        repository.salvar(plano);
        return true;
    }

    public List<Plano> listarPlanos() {
        return repository.listar();
    }

    public boolean excluirPlano(String nome) {
        Plano plano = repository.buscarPorNome(nome);
        if (plano == null) return false;

        repository.remover(plano);
        return true;
    }

    public boolean atualizarPlano(Plano novo) {
        Plano existente = repository.buscarPorNome(novo.getNome());
        if (existente == null) return false;

        existente.setDescricao(novo.getDescricao());
        existente.setValorMensal(novo.getValorMensal());
        existente.setDuracaoMeses(novo.getDuracaoMeses());
        existente.setBeneficios(novo.getBeneficios());

        return true;
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