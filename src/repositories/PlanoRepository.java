package repositories;

import entities.Plano;
import java.util.ArrayList;
import java.util.List;

public class PlanoRepository {

 private final List<Plano> planos = new ArrayList<>();


    // CONSTRUTOR
    public PlanoRepository() {

        Plano plano1 = new Plano(
            "Plano Mensal",
            "Pagamento mensal",
            400.00,
            3 ,
            "Aulas de zumba às terças"
        );
        Plano plano2 = new Plano(
            "Plano Anual",
            "Pagamento anual",
            500.00,
            12,
            "Aulas de yoga às quintas"
        );
        Plano plano3 = new Plano(
            "Plano Semestral",
            "Pagamento semestral",
            600.00,
            6,
            "Aulas de pilates às segundas"
        );

        Plano plano4 = new Plano(
            "Plano Premium",
            "Acesso completo",
            700.00,
            6,
            "Todas as aulas liberadas"
        );

        planos.add(plano1);
        planos.add(plano2);
        planos.add(plano3);
        planos.add(plano4);
    }


    public void salvar(Plano plano) {
        planos.add(plano);
    }

    public List<Plano> listar() {
        return new ArrayList<>(planos);
    }

    public Plano buscarPorNome(String nome) {
        return planos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public void remover(Plano plano) {
        planos.remove(plano);
    }
}