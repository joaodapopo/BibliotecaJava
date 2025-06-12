package view;

import javax.swing.*;
import java.awt.*;
import model.Usuario;

public class TelaUsuarioView extends JFrame {

    public TelaUsuarioView(Usuario usuario) {
        setTitle("Painel do Usuário");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblBoasVindas = new JLabel("Bem-vindo, " + usuario.getNome(), JLabel.CENTER);
        lblBoasVindas.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblBoasVindas, BorderLayout.CENTER);

        setVisible(true);
    }
}
