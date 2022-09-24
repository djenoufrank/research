/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g55301g48982.chatserver.server;

import g55301g48982.chatserver.common.Security;
import g55301g48982.chatserver.configManager.Dao;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ServerMain {

    private final int listeningPort;
    private final Set<String> allUsers = new HashSet<>();
    private final Set<BasicServer> threadOfUsers = new HashSet<>();
private final Dao dao;
    public ServerMain(int listeningPort) throws Exception {
        this.listeningPort = listeningPort;
        dao=Dao.getInstance();//initialization
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(listeningPort)) {
            System.out.println("Chat Server is listening on port " + listeningPort);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");

                BasicServer newUser = new BasicServer(socket, this);
                threadOfUsers.add(newUser);
                newUser.start();
            }

        } catch (IOException ex) {
            System.out.println("something wrong in the server: " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void notifyAllUsersThreadWithoutMyOwn(String message, BasicServer userToExclude) {//userToExclude it's me'
        threadOfUsers.stream().filter((user) -> (user != userToExclude)).forEachOrdered((user) -> {
            user.sendMessage(message);
        });
    }

    boolean sendMessageTo(String message, String sender, String receiver)
            throws IllegalArgumentException {
        try {
            boolean hasThisFriend = dao.hasFriend(sender, receiver);
            if (hasThisFriend) {
                String publicKeyDestinataire = dao.getPublicKey(receiver);
                byte[] cipheredMessage = Security.cipherMessage(message, publicKeyDestinataire);
               
               for (BasicServer user : threadOfUsers) {
                  // System.out.println("recev "+receiver+"et "+user.getUser());
                    if (user.getUser().equals(receiver)) {
                        user.sendMessage(sender, cipheredMessage);
                        break;
                    }
                }
                saveMessage(sender,receiver,message);
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    void saveMessage(String sender, String receiver, String message) {
        if (dao.hasFriend(sender, receiver)) {
            String publicKeySender = dao.getPublicKey(sender);

            try {
                byte[] cipheredMsg = Security.cipherMessage(message, publicKeySender);
                String encodedMessage = Base64.getEncoder().encodeToString(cipheredMsg);
                dao.addMessage(sender, receiver,encodedMessage);
            } catch (Exception e) {
                System.out.println("Could not cipher message: " + e.getMessage());
            }
        }
    }

    void addUser(String name) {
        allUsers.add(name);
    }

    /**
     * A client is disconneted, so we remove the associated user and
     * threadForUser
     */
    void removeUser(String name, BasicServer threadForUser) {
        boolean success = allUsers.remove(name);
        if (success) {
            threadOfUsers.remove(threadForUser);
            System.out.println(name + " has left");
        }
    }

    Set<String> getAllUsers() {
        return this.allUsers;
    }

    boolean hasUsers() {
        return !allUsers.isEmpty();
    }

    public static void main(String[] args) {
        if (args.length >= 1) {
            ServerMain server = null;
            try {
                server = new ServerMain(Integer.parseInt(args[0]));
            } catch (Exception ex) {
                Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            server.execute();
        }

    }
}
