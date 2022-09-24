/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g55301g48982.chatserver.client;

import g55301g48982.chatserver.common.Security;
import g55301g48982.chatserver.configManager.Dao;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ClientWrite extends Thread {

    private PrintWriter printWriter;
    private final Socket socket;
    private final ClientMain client;
    private final Dao dao;

    public ClientWrite(Socket socket, ClientMain client) throws Exception {
        this.socket = socket;
        this.client = client;
        dao=Dao.getInstance();//initialization
        try {
            OutputStream output = socket.getOutputStream();
            printWriter = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error when try to get output stream " + ex.getMessage());  
        }
    }

    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n Do you want to log in ? or register ? press l or r");
        String loginOrRegister = scanner.nextLine();

        // get the name of that user
        System.out.println("\nEnter your user name: ");
        String user = scanner.nextLine();

        // get the password of that user
        System.out.println("\nEnter your password: ");
            boolean success = false;
            if (loginOrRegister.equals("l")) {
            try {
                Base64.Decoder decoder = Base64.getDecoder();
                String hashedPassword = Security.getHashPasswordSalt(scanner.nextLine(), decoder.decode(dao.getSalt(user)));
                success = dao.login(user, hashedPassword);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ClientWrite.class.getName()).log(Level.SEVERE, null, ex);
            }
            } else if (loginOrRegister.equals("r")) {
            try {
                byte[] salt = Security.getSalt();
                String hashedPassword = Security.getHashPasswordSalt(scanner.nextLine(), salt);
                success = dao.register(user, hashedPassword, salt);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ClientWrite.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            if (!success) {
                System.out.println("wrong connexion");
                System.exit(1);
            }
           client.setUser(user);
        printWriter.println(user);
        String text="";
              do {
            System.out.println(user+ ": says something");
            text = scanner.nextLine();
            printWriter.println(text);
        } while (!text.equals("leave"));

        try {
            socket.close();
        } catch (IOException e) {
        }
    }

}
