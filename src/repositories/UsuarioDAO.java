package repositories;

import connection.ConexaoBanco;
import entities.TipoUsuario;
import entities.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario autenticar(String nome, String senha) {
        String sql = "SELECT * FROM usuario WHERE nome = ? AND senha = ?";










        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        TipoUsuario.valueOf(rs.getString("tipo"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void salvar(Usuario usuario) {
        String sql = """
                INSERT INTO usuario (nome, senha, tipo)
                VALUES (?, ?, ?)
                """;

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getTipo().name());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuario";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                usuarios.add(
                        new Usuario(
                                rs.getLong("id"),
                                rs.getString("nome"),
                                rs.getString("senha"),
                                TipoUsuario.valueOf(rs.getString("tipo"))
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public Usuario buscarPorNome(String nome) {
        String sql = "SELECT * FROM usuario WHERE nome = ?";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        TipoUsuario.valueOf(rs.getString("tipo"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void remover(Usuario usuario) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, usuario.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}