package Business.Model;

import Business.Entity.Peer;
import Business.Entity.User;
import Persistance.ConnectionDAO;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HomeManager {
    private ConnectionDAO connectionDAO;
    private BufferedImage homeImage;
    private ArrayList<User> arrayNextUsers;
    private int countPremium;

    public HomeManager(ConnectionDAO connectionDAO) {
        this.connectionDAO = connectionDAO;
        homeImage = null;
        arrayNextUsers = new ArrayList<>();
    }

    // Funcio retorni un array de users amb el mateix llenguatge
    public void getNextUsers(){
        arrayNextUsers = null;
        System.out.println("nickname = " + GlobalUser.getInstance().getMyUser().getNickname());
        System.out.println("language = " + GlobalUser.getInstance().getMyUser().getProgrammingLanguage());
        System.out.println("premium = " + GlobalUser.getInstance().getMyUser().getType());
        User myUser = GlobalUser.getInstance().getMyUser();
        arrayNextUsers = connectionDAO.getRandomUsers(myUser);
        for (int i = 0; i < arrayNextUsers.size(); i++) {
            if(arrayNextUsers.get(i).getId() == GlobalUser.getInstance().getMyUser().getId()){
                arrayNextUsers.remove(i);
            }
        }
        if(GlobalUser.getInstance().getMyUser().getType().equals("Premium")){
            this.countPremium = this.connectionDAO.countPremium(GlobalUser.getInstance().getMyUser());
            System.out.println(this.countPremium + "usuaris que mhan donat like");
        }
        System.out.println("despues");
        System.out.println("is empty = " + arrayNextUsers.isEmpty());
        System.out.println("size = " + arrayNextUsers.size());
    }
    public User getNextUser(int posicion) {
        return arrayNextUsers.get(posicion);
    }

    public int getSize() {
        return arrayNextUsers.size();
    }

    public int getCountPremium(){
        return this.countPremium;
    }

    public BufferedImage getNextImage(User nextUser){
        BufferedImage image = this.connectionDAO.readImage(nextUser);
        return image;
    }

    public void insertLike(int posicion) {
        Peer peer = new Peer(GlobalUser.getInstance().getMyUser().getId(), arrayNextUsers.get(posicion).getId(), false);
        this.connectionDAO.insertLike(peer);
    }
}