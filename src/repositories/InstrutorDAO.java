package repositories;

import connection.ConexaoBanco;
import entities.Instrutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstrutorDAO {

    public boolean salvar(Instrutor instrutor) {
        String sql = """
                INSERT INTO instrutor
                (nome, cpf, telefone, especialidade, horario_trabalho)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, instrutor.getNome());
            ps.setString(2, instrutor.getCpf());
            ps.setString(3, instrutor.getTelefone());
            ps.setString(4, instrutor.getEspecialidade());
            ps.setString(5, instrutor.getHorarioTrabalho());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Instrutor> listar() {
        List<Instrutor> lista = new ArrayList<>();

        String sql = "SELECT * FROM instrutor";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Instrutor instrutor = new Instrutor(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("especialidade"),
                        rs.getString("horario_trabalho")
                );

                lista.add(instrutor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Instrutor buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM instrutor WHERE cpf = ?";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Instrutor(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("especialidade"),
                        rs.getString("horario_trabalho")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean remover(String cpf) {
        String sql = "DELETE FROM instrutor WHERE cpf = ?";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Instrutor instrutor) {
        String sql = """
                UPDATE instrutor
                SET nome = ?, telefone = ?, especialidade = ?, horario_trabalho = ?
                WHERE cpf = ?
                """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, instrutor.getNome());
            ps.setString(2, instrutor.getTelefone());
            ps.setString(3, instrutor.getEspecialidade());
            ps.setString(4, instrutor.getHorarioTrabalho());
            ps.setString(5, instrutor.getCpf());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}