/**
 * Classe responsável pela persistência e consulta
 * de registros de frequência no banco de dados.
 *
 * Implementa cadastro, atualização, exclusão
 * e geração de consultas para relatórios.
 *
 * @author Camila Bandeira de Oliveira
 * @author Gabriel Rodrigues Lopes
 * @author John Lucas Garcia dos Santos
 * @author Marina Pereira Marcelino
 * @author Marina Santos Morais
 * @author Vitor Daniel Dorea Santos
 *
 * @version 1.0
 * @since 2026-05-16
 */

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
                (id_aluno, id_aula, data_entrada, presente)
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
                    f.data_entrada,
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
                    au.alunosinscritos

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
                        rs.getInt("alunosinscritos"),
                        null
                );

                Frequencia frequencia = new Frequencia(
                        rs.getLong("id"),
                        aluno,
                        aula,
                        rs.getTimestamp("data_entrada").toLocalDateTime(),
                        rs.getBoolean("presente")
                );

                lista.add(frequencia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean atualizar(String cpf, String nomeAula, boolean presente) {
        String sql = """
            UPDATE frequencia
            SET presente = ?
            WHERE id_aluno = (
                SELECT id FROM aluno WHERE cpf = ?
            )
            AND id_aula = (
                SELECT id FROM aula WHERE LOWER(nome) = LOWER(?)
            )
            """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, presente);
            ps.setString(2, cpf);
            ps.setString(3, nomeAula);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remover(String cpf, String nomeAula) {

        String sql = """
        DELETE FROM frequencia
        WHERE id_aluno = (
            SELECT id FROM aluno WHERE cpf = ?
        )
        AND id_aula = (
            SELECT id FROM aula WHERE LOWER(nome) = LOWER(TRIM(?))
        )
        """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf.trim());
            ps.setString(2, nomeAula.trim());

            int linhasAfetadas = ps.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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

    public List<Frequencia> buscarPorAlunoPeriodo(
            String cpf,
            java.time.LocalDate inicio,
            java.time.LocalDate fim
    ) {

        return listar().stream()
                .filter(f ->
                        f.getAluno().getCpf().equals(cpf)
                                && !f.getDataHora().toLocalDate().isBefore(inicio)
                                && !f.getDataHora().toLocalDate().isAfter(fim)
                )
                .toList();
    }

    public long contarVisitas(String cpf) {
        return listar().stream()
                .filter(f ->
                        f.getAluno().getCpf().equals(cpf)
                                && f.isPresente()
                )
                .count();
    }

    public java.time.LocalDateTime buscarUltimaVisita(String cpf) {

        return listar().stream()
                .filter(f ->
                        f.getAluno().getCpf().equals(cpf)
                                && f.isPresente()
                )
                .map(Frequencia::getDataHora)
                .max(java.time.LocalDateTime::compareTo)
                .orElse(null);
    }
}