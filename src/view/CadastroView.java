package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Usuario;
import model.UsuarioDAO;

public class CadastroView extends JFrame {
    private JTextField txtNome, txtMatricula;
    private JPasswordField txtSenha;
    private JButton btnCadastrar, btnVoltar;

    public CadastroView() {
        setTitle("Cadastro de Usuário");
        setSize(420, 350); // aumento do tamanho da tela
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centralizar na tela

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel lblTitulo = new JLabel("Criar Conta");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitulo);

        panel.add(Box.createVerticalStrut(25));

        panel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        txtNome.setMaximumSize(new Dimension(300, 30));
        panel.add(txtNome);

        panel.add(Box.createVerticalStrut(15));

        panel.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        txtMatricula.setMaximumSize(new Dimension(300, 30));
        panel.add(txtMatricula);

        panel.add(Box.createVerticalStrut(15));

        panel.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        txtSenha.setMaximumSize(new Dimension(300, 30));
        panel.add(txtSenha);

        panel.add(Box.createVerticalStrut(25));

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnCadastrar);

        panel.add(Box.createVerticalStrut(15));

        btnVoltar = new JButton("Voltar ao Login");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnVoltar);

        add(panel);
        setVisible(true);

        // Ação do botão cadastrar
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Usuario usuario = new Usuario();
                usuario.setNome(txtNome.getText());
                usuario.setMatricula(txtMatricula.getText());
                usuario.setSenha(new String(txtSenha.getPassword()));
                usuario.setTipo("comum");

                UsuarioDAO dao = new UsuarioDAO();
                boolean sucesso = dao.cadastrar(usuario);

                if (sucesso) {
                    JOptionPane.showMessageDialog(CadastroView.this, "Usuário cadastrado com sucesso!");
                    dispose();
                    new LoginView();
                } else {
                    JOptionPane.showMessageDialog(CadastroView.this, "Erro ao cadastrar usuário.");
                }
            }
        });

        // Ação do botão voltar
        btnVoltar.addActionListener(e -> {
            dispose();
            new LoginView();
        });
    }
}
