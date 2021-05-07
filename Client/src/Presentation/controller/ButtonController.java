package Presentation.Controller;

import Business.Entity.User;
import Business.Model.SessionManager;
import Presentation.View.GlobalView;
import Presentation.View.HomeView;
import Presentation.View.LoginView;
import Presentation.View.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonController implements ActionListener {
    private final LoginView loginView;
    private final RegisterView registerView;
    private final SessionManager sessionManager;
    private final GlobalView globalView;

    public ButtonController(LoginView loginView, RegisterView registerView, GlobalView globalView, SessionManager sessionManager) {
        this.loginView = loginView;
        this.registerView = registerView;
        this.globalView = globalView;
        this.sessionManager = sessionManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        User cliente;
        switch (e.getActionCommand()) {
            case LoginView.MOVE_TO_REGISTER:
                loginView.delete();
                registerView.display();
                break;

            case RegisterView.MOVE_TO_LOGIN:
                registerView.delete();
                loginView.display();
                break;

            case LoginView.LOGIN:
                cliente = new User(0, null, registerView.getNickname(), 0, null, null, registerView.getPasswd(), null, null, null);
                sessionManager.login(cliente);
                break;

            case RegisterView.REGISTER:
                cliente = new User(0, registerView.getFirstName(), registerView.getNickname(), Integer.parseInt(registerView.getAge()), registerView.getIsPremium(), registerView.getEmail(), registerView.getPasswd(), null, null, null);
                if (sessionManager.register(cliente)) {
                    registerView.delete();
                    loginView.display();
                }
                break;

            case GlobalView.HOME:
                System.out.println("HOME");
                globalView.setTitle("MINDER HOME");
                break;
            case GlobalView.CHAT:
                System.out.println("CHAT");
                globalView.setTitle("MINDER CHAT");
                break;
            case GlobalView.USER:
                System.out.println("USER");
                globalView.setTitle("MINDER USER");
                break;
            case GlobalView.LOGOUT:
                System.out.println("LOGOUT");
                globalView.dislplayLogoutWindow();
                break;
            case HomeView.LIKE:
                System.out.println("LIKE");
                break;
            case HomeView.DENY:
                System.out.println("DENY");
                break;
        }

    }
}
