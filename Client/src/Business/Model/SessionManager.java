package Business.Model;

import Business.Entity.User;
import Persistance.ConnectionDAO;

import java.awt.image.BufferedImage;

public class SessionManager {
    private final ConnectionDAO connectionDAO;

    public SessionManager(ConnectionDAO connectionDAO) {
        this.connectionDAO = connectionDAO;
    }

    public int login(User cliente) {
        if (connectionDAO.validateLogin(cliente)) {
            switch (connectionDAO.checklogin(cliente)) {
                case 0:
                    //Login correcte, primer cop
                    return 0;
                case 1:
                    //login correcte, usuari reincident
                    return 1;
                case -1:
                    //error en el servidor
                    return -1;
            }
        } else {
            System.out.println("login incorrecte");
        }
        return -2;

    }

    public boolean register(User cliente) {
        if (connectionDAO.registerUser(cliente)) {
            return true;
        } else {
            System.out.println("error en el servidor");
            return false;
        }
    }

    public void saveGlobalUser(User cliente) {
        User user = this.connectionDAO.readUser(cliente);
        if (user != null) {
            GlobalUser.getInstance().setMyUser(user);
        }
    }

    public boolean saveNewImage(BufferedImage image) {
        return this.connectionDAO.sendImage(GlobalUser.getInstance().getMyUser(), image);
    }

    public void disconnect() {
        connectionDAO.disconnectFromServer();
    }

    public boolean updateUser(User user) {
        return connectionDAO.updateUser(user);
    }
}
