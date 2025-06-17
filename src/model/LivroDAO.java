package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public boolean inserirLivro(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, ano, disponivel, imagem) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setBoolean(4, livro.isDisponivel());
            stmt.setString(5, livro.getImagem());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Livro> listarTodos() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAno(rs.getInt("ano"));
                livro.setDisponivel(rs.getBoolean("disponivel"));
                livro.setImagem(rs.getString("imagem"));
                livro.setMatriculaUsuarioAlugou(rs.getString("matricula_usuario"));
                livros.add(livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }

    public boolean atualizarLivro(Livro livro) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, ano = ?, disponivel = ?, imagem = ? WHERE id = ?";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setBoolean(4, livro.isDisponivel());
            stmt.setString(5, livro.getImagem());
            stmt.setInt(6, livro.getId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirLivro(int id) {
        String sql = "DELETE FROM livros WHERE id = ?";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alugarLivro(int id, String matriculaUsuario) {
        String sql = "UPDATE livros SET disponivel = false, matricula_usuario = ? WHERE id = ?";
        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matriculaUsuario);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean devolverLivro(int id) {
        String sql = "UPDATE livros SET disponivel = true, matricula_usuario = NULL WHERE id = ?";
        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Livro buscarPorId(int id) {
        String sql = "SELECT * FROM livros WHERE id = ?";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAno(rs.getInt("ano"));
                livro.setDisponivel(rs.getBoolean("disponivel"));
                livro.setImagem(rs.getString("imagem"));
                return livro;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    
    
    
}
