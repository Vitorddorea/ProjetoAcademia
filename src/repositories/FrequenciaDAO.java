package repositories;

import connection.ConexaoBanco;
import entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FrequenciaDAO {

    public boolean salvar(Frequencia frequencia) {

        String sql = """
                INSERT INTO frequencia
                (id_aluno, id_aula, data_hora, presente)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, frequencia.getAluno().getId());
            ps.setLong(2, frequencia.getAula().getId());
            ps.setTimestamp(3, Timestamp.valueOf(frequencia.getDataHora()));
            ps.setBoolean(4, frequencia.isPresente());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Frequencia> listar() {

        List<Frequencia> lista = new ArrayList<>();

        String sql = """
                SELECT
                    f.id,
                    f.data_hora,
                    f.presente,

                    a.id as aluno_id,
                    a.nome as aluno_nome,
                    a.cpf,
                    a.telefone,

                    au.id as aula_id,
                    au.nome as aula_nome,
                    au.horario,
                    au.duracao,
                    au.capacidade_maxima,
                    au.alunos_inscritos

                FROM frequencia f
                JOIN aluno a ON f.id_aluno = a.id
                JOIN aula au ON f.id_aula = au.id
                """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Aluno aluno = new Aluno(
                        rs.getLong("aluno_id"),
                        rs.getString("aluno_nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        null,
                        "",
                        null,
                        null
                );

                Aula aula = new Aula(
                        rs.getLong("aula_id"),
                        rs.getString("aula_nome"),
                        rs.getString("horario"),
                        rs.getInt("duracao"),
                        rs.getInt("capacidade_maxima"),
                        rs.getInt("alunos_inscritos"),
                        null
                );

                Frequencia frequencia = new Frequencia(
                        rs.getLong("id"),
                        aluno,
                        aula,
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        rs.getBoolean("presente")
                );

                lista.add(frequencia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Frequencia buscar(String cpf, String nomeAula) {

        return listar().stream()
                .filter(f ->
                        f.getAluno().getCpf().equals(cpf) &&
                                f.getAula().getNome().equalsIgnoreCase(nomeAula)
                )
                .findFirst()
                .orElse(null);
    }
}