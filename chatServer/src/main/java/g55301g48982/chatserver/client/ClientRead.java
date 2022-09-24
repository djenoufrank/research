/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g55301g48982.chatserver.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author User
 */
public class ClientRead extends Thread{
    
    private BufferedReader reader;
    private final Socket socket;
    private final ClientMain client;

    public ClientRead(Socket socket, ClientMain client) {
        this.socket = socket;
        this.client = client;

        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("input stream error " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String answer = reader.readLine();
                System.out.println("\n" + answer);
                if (client.getUser() != null) {
                    System.out.println(client.getUser() + ":");
                }
            } catch (IOException e) {
                System.out.println("you're disconnected and "+e.getMessage());
                break;
            }
        }
    }
}
