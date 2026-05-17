package repositories;

import connection.ConexaoBanco;
import entities.Aluno;
import entities.Plano;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public boolean adicionarAluno(Aluno aluno) {
        String sql = """
                INSERT INTO aluno
                (nome, cpf, telefone, data_nascimento, email, data_matricula, id_plano)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCpf());
            ps.setString(3, aluno.getTelefone());
            ps.setDate(4, Date.valueOf(aluno.getDataNascimento()));
            ps.setString(5, aluno.getEmail());
            ps.setDate(6, Date.valueOf(aluno.getDataMatricula()));

            if (aluno.getPlanoAtivo() != null) {
                ps.setLong(7, aluno.getPlanoAtivo().getId());
            } else {
                ps.setNull(7, Types.INTEGER);
            }

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();

        String sql = """
                SELECT a.*, p.nome_plano
                FROM aluno a
                LEFT JOIN plano p ON a.id_plano = p.id
                """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Plano plano = null;

                if (rs.getObject("id_plano") != null) {
                    plano = new Plano(
                            rs.getLong("id_plano"),
                            rs.getString("nome_plano"),
                            "",
                            0,
                            0,
                            ""
                    );
                }

                Aluno aluno = new Aluno(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("email"),
                        rs.getDate("data_matricula").toLocalDate(),
                        plano
                );

                alunos.add(aluno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunos;
    }

    public Aluno buscarAlunoPorCpf(String cpf) {
        String sql = """
                    SELECT a.*, p.nome_plano
                    FROM aluno a
                    LEFT JOIN plano p ON a.id_plano = p.id
                    WHERE a.cpf = ?
        """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Plano plano = null;

                if (rs.getObject("id_plano") != null) {
                    plano = new Plano(
                            rs.getLong("id_plano"),
                            rs.getString("nome_plano"),
                            "",
                            0,
                            0,
                            ""
                    );
                }

                return new Aluno(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("email"),
                        rs.getDate("data_matricula").toLocalDate(),
                        plano
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean removerAluno(String cpf) {
        String sql = "DELETE FROM aluno WHERE cpf = ?";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf.trim());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarAluno(Aluno aluno) {
        String sql = """
                UPDATE aluno
                SET nome = ?, telefone = ?, email = ?, data_nascimento = ?, id_plano = ?
                WHERE cpf = ?
                """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getTelefone());
            ps.setString(3, aluno.getEmail());
            ps.setDate(4, Date.valueOf(aluno.getDataNascimento()));

            if (aluno.getPlanoAtivo() != null) {
                ps.setLong(5, aluno.getPlanoAtivo().getId());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            ps.setString(6, aluno.getCpf());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}