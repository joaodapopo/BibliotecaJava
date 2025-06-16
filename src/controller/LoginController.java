package controller;

import javax.swing.JOptionPane;

import model.Usuario;
import model.UsuarioDAO;
import view.LoginView;
import view.AdminView;
import view.UsuarioView;

public class LoginController {

    public static void autenticar(LoginView telaLogin, String matricula, String senha) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.autenticar(matricula, senha);

        if (usuario != null) {
            JOptionPane.showMessageDialog(telaLogin, "Bem-vindo, " + usuario.getNome() + "!");
            telaLogin.dispose(); // fecha a tela de login

            if (usuario.getTipo().equalsIgnoreCase("admin")) {
                new AdminView(usuario).setVisible(true); // ✅ passa o usuário
            } else {
                new UsuarioView(usuario).setVisible(true); // ✅ passa o usuário
            }

        } else {
            JOptionPane.showMessageDialog(telaLogin, "Matrícula ou senha incorreta.");
        }
    }
}
