package view;

import model.Livro;
import model.LivroDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

public class AdminView extends JFrame {
	
	private Usuario usuario;

	public AdminView(Usuario usuario) {
	    this(); // chama o construtor padrão
	    this.usuario = usuario;
	}


    public AdminView() {
        setTitle("Biblioteca - Administrador");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painelLivros = new JPanel(new GridLayout(0, 3, 10, 10));
        painelLivros.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(painelLivros);
        add(scroll, BorderLayout.CENTER);

        LivroDAO dao = new LivroDAO();
        List<Livro> livros = dao.listarTodos();

        for (Livro livro : livros) {
            painelLivros.add(criarPainelLivro(livro));
        }

        // Botão adicionar novo livro
        JButton btnAdicionarLivro = new JButton("Adicionar Livro");
        btnAdicionarLivro.addActionListener(e -> {
            new CadastroLivroView(this, usuario).setVisible(true);
        });

        JPanel painelInferior = new JPanel();
        painelInferior.add(btnAdicionarLivro);
        add(painelInferior, BorderLayout.SOUTH);
    }

    private JPanel criarPainelLivro(Livro livro) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        painel.setBackground(Color.WHITE);
        painel.setPreferredSize(new Dimension(200, 350));

        JLabel imagemLabel = new JLabel();
        imagemLabel.setHorizontalAlignment(JLabel.CENTER);
        imagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        // Centralizar
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        autorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        anoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botões de ação
     // Botão Editar
        JButton editarBtn = new JButton("Editar");
        JButton excluirBtn = new JButton("Excluir");

        editarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        excluirBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        editarBtn.addActionListener(e -> {
            new EditarLivroView(this, livro, this.usuario); // abre janela de edição
        });

        excluirBtn.addActionListener((ActionEvent e) -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o livro?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                LivroDAO dao = new LivroDAO();
                if (dao.excluirLivro(livro.getId())) {
                    JOptionPane.showMessageDialog(this, "Livro excluído com sucesso!");
                    this.dispose(); // Fecha e reabre para atualizar
                    new AdminView().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir o livro.");
                }
            }
        });

        painel.add(Box.createVerticalStrut(10));
        painel.add(imagemLabel);
        painel.add(Box.createVerticalStrut(10));
        painel.add(tituloLabel);
        painel.add(autorLabel);
        painel.add(anoLabel);
        painel.add(statusLabel);
        painel.add(Box.createVerticalStrut(5));
        painel.add(editarBtn);
        painel.add(Box.createVerticalStrut(5));
        painel.add(excluirBtn);

        return painel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminView().setVisible(true));
    }
}
