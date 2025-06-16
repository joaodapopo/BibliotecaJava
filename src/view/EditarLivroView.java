package view;

import model.Livro;
import model.LivroDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.*;

public class EditarLivroView extends JDialog {
	
	private Usuario usuario;

	

    public EditarLivroView(JFrame parent, Livro livro, Usuario usuario) {
        super(parent, "Editar Livro", true);
        this.usuario = usuario;
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField campoTitulo = new JTextField(livro.getTitulo());
        JTextField campoAutor = new JTextField(livro.getAutor());
        JTextField campoAno = new JTextField(String.valueOf(livro.getAno()));
        JCheckBox checkDisponivel = new JCheckBox("Disponível", livro.isDisponivel());
        JTextField campoImagem = new JTextField(livro.getImagem());

        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.addActionListener(e -> {
            livro.setTitulo(campoTitulo.getText());
            livro.setAutor(campoAutor.getText());
            livro.setAno(Integer.parseInt(campoAno.getText()));
            livro.setDisponivel(checkDisponivel.isSelected());
            livro.setImagem(campoImagem.getText());

            boolean sucesso = new LivroDAO().atualizarLivro(livro);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Livro atualizado com sucesso!");
                dispose();
                parent.dispose(); // fecha a AdminView atual
                new AdminView(usuario).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar livro.");
            }
        });

        panel.add(new JLabel("Título:"));
        panel.add(campoTitulo);
        panel.add(new JLabel("Autor:"));
        panel.add(campoAutor);
        panel.add(new JLabel("Ano:"));
        panel.add(campoAno);
        panel.add(new JLabel("Disponível:"));
        panel.add(checkDisponivel);
        panel.add(new JLabel("Imagem (caminho):"));
        panel.add(campoImagem);
        panel.add(new JLabel());
        panel.add(btnSalvar);

        add(panel);
        setVisible(true);
    }
}
