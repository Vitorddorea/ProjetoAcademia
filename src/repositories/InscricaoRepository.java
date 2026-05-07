package repositories;

import entities.Inscricao;
import java.util.ArrayList;
import java.util.List;

public class InscricaoRepository {

    private List<Inscricao> inscricoes = new ArrayList<>();

    public void salvar(Inscricao inscricao) {
        inscricoes.add(inscricao);
    }

    public List<Inscricao> listar() {
        return new ArrayList<>(inscricoes);
    }

    public void remover(Inscricao inscricao) {
        inscricoes.remove(inscricao);
    }
}