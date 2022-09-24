/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g55301g48982.chatserver.client;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author User
 */
public class ClientMain {

    private final String nameOfhost;
    private final int listeningPort;
    private String user;

    public ClientMain(String nameOfhost, int listeningPort) {
        this.nameOfhost = nameOfhost;
        this.listeningPort = listeningPort;
    }

    public void execute() throws Exception {
        try {
            Socket socket = new Socket(nameOfhost, listeningPort);
            System.out.println("you're connected to the chat");

            new ClientRead(socket, this).start();
            new ClientWrite(socket, this).start();

        } catch (IOException ex) {
            System.out.println("I/O Error or Server not found " + ex.getMessage());
        }
    }

    void setUser(String updateUser) {
        user = updateUser;
    }

    String getUser() {
        return user;
    }
         public static void main(String[] args) throws Exception {
        if (args.length >= 2){

        String nameOfhost = args[0];
        int listeningPort = Integer.parseInt(args[1]);

        ClientMain client = new ClientMain(nameOfhost, listeningPort);
        client.execute();
        }
    }
}
