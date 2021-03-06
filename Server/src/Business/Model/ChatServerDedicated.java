package Business.Model;

import Business.Entity.ChatMessage;
import Business.Entity.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * The type Chat server dedicated.
 */
public class ChatServerDedicated extends Thread{
    private Socket client;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private User myUser;
    private User otherUser;
    private static ChatMessage newMessage;
    private static boolean messageToSend = false;

    /**
     * Instantiates a new Chat server dedicated.
     *
     * @param client the client
     */
    public ChatServerDedicated(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        try {
            this.is = new ObjectInputStream(client.getInputStream());
            this.os = new ObjectOutputStream(client.getOutputStream());

            myUser = (User) is.readObject();
            otherUser = (User) is.readObject();

            while (!DedicatedServer.clientDisconnect) {
                if(messageToSend){
                    if(newMessage.getIdDestiny() == myUser.getId()
                            && newMessage.getIdSource() == otherUser.getId()){
                        os.writeObject(newMessage);
                        messageToSend = false;
                    }
                }
                sleep(100);

            }
            client.close();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }


    }

    /**
     * Function that recieves a message when a client of the platform send one, to be treated later if
     * the server has to send the message to our client.
     *
     * @param message the message
     */
    public static void newMessage(ChatMessage message){
        newMessage = message;
        System.out.println(message.getMessage());
        messageToSend = true;
    }
}
