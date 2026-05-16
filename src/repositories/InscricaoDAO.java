package repositories;

import connection.ConexaoBanco;
import entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscricaoDAO {

    public boolean salvar(Inscricao inscricao) {
        String sql = """
                INSERT INTO inscricao (id_aluno, id_aula)
                VALUES (?, ?)
                """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, inscricao.getAluno().getId());
            ps.setLong(2, inscricao.getAula().getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Inscricao> listar() {
        List<Inscricao> lista = new ArrayList<>();

        String sql = """
                SELECT i.id as inscricao_id,

                       a.id as aluno_id,
                       a.nome as aluno_nome,
                       a.cpf,
                       a.telefone,

                       au.id as aula_id,
                       au.nome as aula_nome,
                       au.horario,
                       au.duracao,
                       au.capacidade_maxima,
                       au.alunosinscritos

                FROM inscricao i
                JOIN aluno a ON i.id_aluno = a.id
                JOIN aula au ON i.id_aula = au.id
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
                        rs.getInt("alunosinscritos"),
                        null
                );

                Inscricao inscricao = new Inscricao(
                        rs.getLong("inscricao_id"),
                        aluno,
                        aula
                );

                lista.add(inscricao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean remover(String cpf, String nomeAula) {
        String sql = """
                DELETE FROM inscricao
                WHERE id IN (
                    SELECT i.id
                    FROM inscricao i
                    JOIN aluno a ON i.id_aluno = a.id
                    JOIN aula au ON i.id_aula = au.id
                    WHERE a.cpf = ? AND LOWER(au.nome) = LOWER(?)
                )
                """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
            ps.setString(2, nomeAula);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}