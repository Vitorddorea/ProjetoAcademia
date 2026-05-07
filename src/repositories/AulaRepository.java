package repositories;

import entities.Aula;
import java.util.ArrayList;
import java.util.List;

public class AulaRepository {

    private List<Aula> aulas = new ArrayList<>();

    public void salvar(Aula aula) {
        aulas.add(aula);
    }

    public List<Aula> listar() {
        return new ArrayList<>(aulas);
    }

    public Aula buscarPorNome(String nome) {
        for (Aula a : aulas) {
            if (a.getNome().equalsIgnoreCase(nome)) {
                return a;
            }
        }
        return null;
    }

    public void remover(Aula aula) {
        aulas.remove(aula);
    }
}