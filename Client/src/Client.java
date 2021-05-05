import Persistance.ConfigurationDAO;
import Persistance.ConnectionDAO;
import Persistance.ConnectionDAOImpl;
import Persistance.JsonConfigurationDAO;
import Presentation.Controller.ButtonController;
import Presentation.View.LoginView;
import Presentation.View.RegisterView;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, Client!");
        ConfigurationDAO configurationDAO = new JsonConfigurationDAO("Client/Data/configuracio-client.json");
        ConnectionDAO connectionDAO = new ConnectionDAOImpl(configurationDAO);
        // connectionDAO.registerUser(new User(0, "Edmon", "bosched", 20,
        // "Normal","edmonbosch@gmail.com", "hola", null,"soc l'edmon", "Java"));
        // connectionDAO.sendImage(new User(0, "Edmon", "bosched", 20,
        // "Normal","edmonbosch@gmail.com", "hola", null,"soc l'edmon", "Java"));

        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();
        ButtonController buttonController = new ButtonController(loginView, registerView, connectionDAO);

        loginView.registerController(buttonController);
        registerView.registerController(buttonController);

        loginView.display();
    }
}