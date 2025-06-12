package controller;

import javax.swing.JOptionPane;

import model.Usuario;
import model.UsuarioDAO;
import view.LoginView;
import view.TelaAdminView;
import view.TelaUsuarioView;

public class LoginController {

    public static void autenticar(LoginView telaLogin, String matricula, String senha) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.autenticar(matricula, senha);

        if (usuario != null) {
            JOptionPane.showMessageDialog(telaLogin, "Bem-vindo, " + usuario.getNome() + "!");
            telaLogin.dispose(); // fecha a tela de login

            if (usuario.getTipo().equalsIgnoreCase("admin")) {
                new TelaAdminView(usuario);
            } else {
                new TelaUsuarioView(usuario);
            }

        } else {
            JOptionPane.showMessageDialog(telaLogin, "Matrícula ou senha incorreta.");
        }
    }
}
