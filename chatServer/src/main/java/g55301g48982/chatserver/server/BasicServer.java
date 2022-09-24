/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g55301g48982.chatserver.server;

import g55301g48982.chatserver.common.Security;
import g55301g48982.chatserver.configManager.Dao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Objects;

/**
 * represent the thread of actual user that is typing
 *
 * @author User
 */
public class BasicServer extends Thread {

    private String user;
    private final Socket socket;
    private final ServerMain server;
    private PrintWriter printWriter;
    private final Dao dao;
    public BasicServer(Socket socket, ServerMain server) throws Exception {
        this.socket = socket;
        this.server = server;
        dao=Dao.getInstance();//initialization
    }

    @Override
    public void run() {
        try {

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            printWriter = new PrintWriter(output, true);
            printAllUsers();
            // read user
            String newUser = reader.readLine();
            this.user = newUser;
            server.addUser(newUser);

            //says user is connected to another client
            String serverMessage = "New user connected: " + newUser;
            server.notifyAllUsersThreadWithoutMyOwn(serverMessage, this);//this is me

            // Client input
            String getInput;
            boolean wantToEnd = false;
            boolean messageIsSend = false;
            do {
                getInput = reader.readLine();

                switch (getInput) {
                    case "friends":
                        printAllFriends();
                        break;
                    case "list":
                        printAllUsers();
                        break;
                    case "leave":
                        wantToEnd = true;
                        break;
                    case "help":
                        help();
                        break;
                    default: {
                        String[] splited = getInput.split("@");
                        if (splited.length == 2) {
                            switch (splited[0]) {
                                case "add":
                                    if (!(this.user).equals(splited[1]) && dao.checkUserExist(splited[1])) {
                                        printWriter.println(addUser(splited[1]) ? splited[1]
                                                + " added " : splited[1] + " is not added !");
                                    } else if ((this.user).equals(splited[1])) {
                                        printWriter.println("you can't add you're own user account");
                                    } else {
                                        printWriter.println("this user doesn't exist");
                                    }
                                    break;
                                case "delete":
                                    if (!(this.user).equals(splited[1]) && dao.hasFriend(this.user, splited[1])) {
                                        printWriter.println(deleteUser(splited[1]) ? splited[1]
                                                + " is deleted !" : splited[1] + " is not deleted !");
                                    } else if ((this.user).equals(splited[1])) {
                                        printWriter.println("you can't delete you're own user account");
                                    } else {
                                        printWriter.println("this user doesn't exist or is not your friend ");
                                    }
                                    break;
                                case "history":
                                    if (!(this.user).equals(splited[1]) && dao.hasFriend(this.user, splited[1])) {
                                getHistory(this.user,splited[1]);
                                getHistory(splited[1],this.user);
                                    } else if ((this.user).equals(splited[1])) {
                                        printWriter.println("you can't talk with you're own user account");
                                    } else {
                                        printWriter.println("this user doesn't exist or is not your friend ");
                                    }
                                    break;
                                default: { // send message
                                    if (!(this.user).equals(splited[1]) && dao.checkUserExist(splited[1])) {
                                        messageIsSend = server.sendMessageTo(splited[0], this.user, splited[1]);
                                        if (messageIsSend) {
                                            printWriter.println(splited[1] + " receive your message");
                                        } else {
                                            printWriter.println(splited[1] + " didn't receive your message because he's not in your friend list");
                                        }
                                    } else if ((this.user).equals(splited[1])) {
                                        printWriter.println("you can't send message to your own account");
                                    } else {
                                        printWriter.println("this user doesn't exist");
                                    }
                                }
                                break;
                            }
                        } else {
                            printWriter.println("check you're typing text please");
                            help();
                        }
                    }
                    break;
                }
            } while (!wantToEnd);

        } catch (IOException | IllegalArgumentException e) {
            printWriter.println("Basic server error " + e.getMessage());
        } finally {
            server.removeUser(user, this);
            server.notifyAllUsersThreadWithoutMyOwn(user + " left", this);
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Basic server error: " + e.getMessage());
            }
        }
    }

    private boolean addUser(String friend) {
        return dao.addFriend(user, friend);
    }

    private boolean deleteUser(String user) {
        return dao.deleteFriend(user, user);
    }

    private void printAllFriends() {
        List<String> friends = dao.getAllFriends(user);
        if (friends.isEmpty()) {
            printWriter.println("you don't have friend for the moment ");
        } else {
            printWriter.print("your friends : ");
            friends.forEach((friend) -> {
                printWriter.print(friend + ", ");
            });
            printWriter.println();
        }

    }

    private void help() {
        printWriter.print("there is your commands:\n"
                + "Add a friend: add@friendName.\n"
                + "Delete a friend: delete@friendName.\n"
                + "Show my friends: friends.\n"
                + "Show all connected users: list.\n"
                + "Get all my history with choosen friend: history@friendName\n"
                + "to disconnect: leave.\n"
                + "Send a message to a friend:  message@friendName \n");
    }

    public void printAllUsers() {
        if (server.hasUsers()) {
            printWriter.println("\nusers connected : " + server.getAllUsers());
        } else {
            printWriter.println("No users connected");
        }
    }

    public void sendMessage(String message) {
        printWriter.println("Server says :" + message);
    }

    public void sendMessage(String sender, byte[] message) {
        try {
            printWriter.println(sender + " says: "
                    + Security.decipherMessage(message, dao.getPrivateKey(this.user)));
        } catch (Exception ex) {
            System.out.println("sending issue : " + ex.getMessage());
        }
    }

    void getHistory(String sender, String receiver) throws IllegalArgumentException {
        printWriter.println("histories of "+sender+": ");
        List<String> getMessages=Security.decipherHistoryMessage(dao.getHistories(sender, receiver), dao.getPrivateKey(sender));
        for (String message : getMessages) {
            printWriter.println(sender+ " : "+message);
        }
    }

    public String getUser() {
        return user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(socket, server, printWriter);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BasicServer other = (BasicServer) obj;
        if (!Objects.equals(this.socket, other.socket)) {
            return false;
        }
        if (!Objects.equals(this.server, other.server)) {
            return false;
        }
        return Objects.equals(this.printWriter, other.printWriter);
    }

}
