package repositories;

import connection.ConexaoBanco;
import entities.Aula;
import entities.Instrutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AulaDAO {

    public boolean salvar(Aula aula) {
        String sql = """
                INSERT INTO aula
                (nome, horario, duracao, capacidade_maxima, alunosinscritos, id_instrutor)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, aula.getNome());
            ps.setString(2, aula.getHorario());
            ps.setInt(3, aula.getDuracao());
            ps.setInt(4, aula.getCapacidadeMaxima());
            ps.setInt(5, aula.getAlunosInscritos());
            ps.setLong(6, aula.getInstrutor().getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Aula> listar() {
        List<Aula> aulas = new ArrayList<>();

        String sql = """
        SELECT a.*,
               i.id AS instrutor_id,
               i.nome AS nome_instrutor,
               i.cpf,
               i.telefone,
               i.especialidade,
               i.horario_trabalho
        FROM aula a
        JOIN instrutor i ON a.id_instrutor = i.id
        """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Instrutor instrutor = new Instrutor(
                        rs.getLong("instrutor_id"),
                        rs.getString("nome_instrutor"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("especialidade"),
                        rs.getString("horario_trabalho")
                );

                Aula aula = new Aula(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("horario"),
                        rs.getInt("duracao"),
                        rs.getInt("capacidade_maxima"),
                        rs.getInt("alunosinscritos"),
                        instrutor
                );

                aulas.add(aula);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aulas;
    }

    public Aula buscarPorNome(String nome) {
        return listar().stream()
                .filter(a -> a.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public boolean remover(String nome) {
        String sql = "DELETE FROM aula WHERE LOWER(nome) = LOWER(?)";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Aula aula) {
        String sql = """
            UPDATE aula
            SET horario = ?,
                duracao = ?,
                capacidade_maxima = ?,
                alunosinscritos = ?,
                id_instrutor = ?
            WHERE LOWER(nome) = LOWER(?)
            """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, aula.getHorario());
            ps.setInt(2, aula.getDuracao());
            ps.setInt(3, aula.getCapacidadeMaxima());
            ps.setInt(4, aula.getAlunosInscritos());
            ps.setLong(5, aula.getInstrutor().getId());
            ps.setString(6, aula.getNome());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}