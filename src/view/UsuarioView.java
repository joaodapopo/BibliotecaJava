package view;

import model.Livro;
import model.LivroDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class UsuarioView extends JFrame {

    private Usuario usuario; // armazenar o usuário logado

    public UsuarioView(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Biblioteca - Usuário");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painelLivros = new JPanel(new GridLayout(0, 3, 10, 10)); // 3 colunas
        painelLivros.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(painelLivros);
        add(scroll);

        LivroDAO dao = new LivroDAO();
        List<Livro> livros = dao.listarTodos();

        for (Livro livro : livros) {
            painelLivros.add(criarPainelLivro(livro, usuario)); // ✅ passa o usuário para o método
        }
    }

    private JPanel criarPainelLivro(Livro livro, Usuario usuario) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        painel.setBackground(Color.WHITE);
        painel.setPreferredSize(new Dimension(200, 350));

        JLabel imagemLabel = new JLabel();
        imagemLabel.setHorizontalAlignment(JLabel.CENTER);
        imagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Carrega imagem
        File imagemArquivo = new File(System.getProperty("user.dir") + File.separator + livro.getImagem());
        if (imagemArquivo.exists()) {
            ImageIcon icon = new ImageIcon(imagemArquivo.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(120, 160, Image.SCALE_SMOOTH);
            imagemLabel.setIcon(new ImageIcon(img));
        } else {
            imagemLabel.setText("Sem imagem");
            imagemLabel.setForeground(Color.GRAY);
        }

        JLabel tituloLabel = new JLabel("Título: " + livro.getTitulo());
        JLabel autorLabel = new JLabel("Autor: " + livro.getAutor());
        JLabel anoLabel = new JLabel("Ano: " + livro.getAno());
        JLabel statusLabel = new JLabel(livro.isDisponivel() ? "Disponível" : "Indisponível");
        statusLabel.setForeground(livro.isDisponivel() ? new Color(0, 128, 0) : Color.RED);

        // Centraliza os elementos
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        autorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        anoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botão de ação
        JButton btnAcao = new JButton();
        btnAcao.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Se o livro está disponível, qualquer usuário pode alugar
        if (livro.isDisponivel()) {
            btnAcao.setText("Alugar");
            btnAcao.addActionListener(e -> {
                LivroDAO dao = new LivroDAO();
                boolean sucesso = dao.alugarLivro(livro.getId(), usuario.getMatricula());

                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Livro alugado com sucesso.");
                    dispose();
                    new UsuarioView(usuario).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao alugar livro.");
                }
            });
            painel.add(btnAcao);

        // Se o livro está indisponível, só o dono pode devolver
        } else if (usuario.getMatricula().equals(livro.getMatriculaUsuarioAlugou())) {
            btnAcao.setText("Devolver");
            btnAcao.addActionListener(e -> {
                LivroDAO dao = new LivroDAO();
                boolean sucesso = dao.devolverLivro(livro.getId());

                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Livro devolvido com sucesso.");
                    dispose();
                    new UsuarioView(usuario).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao devolver livro.");
                }
            });
            painel.add(btnAcao);
        }


        painel.add(Box.createVerticalStrut(10));
        painel.add(imagemLabel);
        painel.add(Box.createVerticalStrut(10));
        painel.add(tituloLabel);
        painel.add(autorLabel);
        painel.add(anoLabel);
        painel.add(statusLabel);
        painel.add(Box.createVerticalStrut(10));
        painel.add(btnAcao);

        return painel;
    }

}
