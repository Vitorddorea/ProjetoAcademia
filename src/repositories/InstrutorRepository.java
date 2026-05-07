package repositories;

import entities.Instrutor;

import java.util.ArrayList;
import java.util.List;

public class InstrutorRepository {

    private List<Instrutor> lista = new ArrayList<>();

    public void salvar(Instrutor instrutor) {
        lista.add(instrutor);
    }

    public List<Instrutor> listar() {
        return new ArrayList<>(lista);
    }

    public Instrutor buscarPorCpf(String cpf) {
        return lista.stream()
                .filter(i -> i.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public void remover(Instrutor instrutor) {
        lista.remove(instrutor);
    }
}