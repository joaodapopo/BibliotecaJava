package view;

import javax.swing.*;
import java.awt.*;
import model.Usuario;

public class TelaAdminView extends JFrame {

    public TelaAdminView(Usuario admin) {
        setTitle("Painel do Administrador");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblBoasVindas = new JLabel("Bem-vindo, Administrador " + admin.getNome(), JLabel.CENTER);
        lblBoasVindas.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblBoasVindas, BorderLayout.CENTER);

        setVisible(true);
    }
}
