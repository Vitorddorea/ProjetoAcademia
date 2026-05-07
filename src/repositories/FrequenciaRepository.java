package repositories;

import entities.Frequencia;

import java.util.ArrayList;
import java.util.List;

public class FrequenciaRepository {

    private List<Frequencia> frequencias = new ArrayList<>();

    public void salvar(Frequencia frequencia) {
        frequencias.add(frequencia);
    }

    public List<Frequencia> listar() {
        return new ArrayList<>(frequencias);
    }

    public Frequencia buscar(String cpf, String nomeAula) {
        for (Frequencia f : frequencias) {
            if (f.getAluno().getCpf().equals(cpf) &&
                f.getAula().getNome().equalsIgnoreCase(nomeAula)) {
                return f;
            }
        }
        return null;
    }

    public void remover(Frequencia frequencia) {
        frequencias.remove(frequencia);
    }
}