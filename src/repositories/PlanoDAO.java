package repositories;

import connection.ConexaoBanco;
import entities.Plano;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO {

    public boolean salvar(Plano plano) {
        String sql = """
                INSERT INTO plano
                (nome_plano, descricao, valor_mensal, duracao_meses, beneficios_incluidos)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, plano.getNome());
            ps.setString(2, plano.getDescricao());
            ps.setDouble(3, plano.getValorMensal());
            ps.setInt(4, plano.getDuracaoMeses());
            ps.setString(5, plano.getBeneficios());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Plano> listar() {
        List<Plano> planos = new ArrayList<>();

        String sql = "SELECT * FROM plano";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Plano plano = new Plano(
                        rs.getLong("id"),
                        rs.getString("nome_plano"),
                        rs.getString("descricao"),
                        rs.getDouble("valor_mensal"),
                        rs.getInt("duracao_meses"),
                        rs.getString("beneficios_incluidos")
                );

                planos.add(plano);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planos;
    }

    public Plano buscarPorNome(String nome) {
        String sql = "SELECT * FROM plano WHERE LOWER(nome_plano) = LOWER(?)";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Plano(
                        rs.getLong("id"),
                        rs.getString("nome_plano"),
                        rs.getString("descricao"),
                        rs.getDouble("valor_mensal"),
                        rs.getInt("duracao_meses"),
                        rs.getString("beneficios_incluidos")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean remover(String nome) {
        String sql = "DELETE FROM plano WHERE LOWER(nome_plano) = LOWER(?)";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Plano plano) {
        String sql = """
                UPDATE plano
                SET descricao = ?, valor_mensal = ?, duracao_meses = ?, beneficios_incluidos = ?
                WHERE LOWER(nome_plano) = LOWER(?)
                """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, plano.getDescricao());
            ps.setDouble(2, plano.getValorMensal());
            ps.setInt(3, plano.getDuracaoMeses());
            ps.setString(4, plano.getBeneficios());
            ps.setString(5, plano.getNome());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}