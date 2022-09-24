
package g55301g48982.chatserver.configManager;

import g55301g48982.chatserver.common.Security;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Dao {
       private Connection connexion;//si je met final ca bug :D

    /**
     * constructor of favoriteDao.
     *
     * @throws java.lang.Exception
     */
    public Dao() throws Exception {
        connexion = DBManager.getInstance().getConnection();
    }

    /**
     * allows to get an instance of the class for security reasons.
     *
     * @return the class instance of this;
     */
    public static Dao getInstance() throws Exception {
        return DaoHolder.getInstance();
    }
    /**
     * Login for user.
     *
     * @param login login.
     * @param hashedPassword sending hash password
     * @return true it's successful, false otherwise.
     */
    public boolean login(String login, String hashedPassword) {    
        String query = "SELECT * FROM users WHERE login = ? AND password = ?";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, login);
            pstmt.setString(2, hashedPassword);

            ResultSet rs = pstmt.executeQuery();

            int compt = 0;
            while (rs.next()) {
                compt++;
            }
            if (compt != 1) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    /**
     * add new user.
     *
     * @param login user login.
     * @param password user password.
     * @param salt the salt to hash the password.
     * @return true if it's successful, false otherwise.
     */
    public  boolean register(String login, String password, byte[] salt) {
        String query = "INSERT INTO users(login, password, public_key, private_key, salt) " +
                "VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, login);
            pstmt.setString(2, password);

            KeyPair keyPair = Security.getKeys();
            Base64.Encoder encoder = Base64.getEncoder();
            pstmt.setString(3, encoder.encodeToString(keyPair.getPublic().getEncoded()));
            pstmt.setString(4, encoder.encodeToString(keyPair.getPrivate().getEncoded()));
            pstmt.setString(5, encoder.encodeToString(salt));

            pstmt.executeUpdate();
            return true;
        } catch (SQLException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Gets the public key of the given user.
     *
     * @param login login of user.
     * @return the public key of that user.
     */
    public String getPublicKey(String login) {
        String query = "SELECT public_key FROM users WHERE login = ?";
        String publicKey = null;
        try {
            PreparedStatement pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, login);

            ResultSet rs = pstmt.executeQuery();

            int compt = 0;
            while (rs.next()) {
                publicKey = rs.getString(1);
                compt++;
            }
            if (compt != 1) {
                throw new SQLException("some error :there is more than 1 ");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return publicKey;
    }

    /**
     * Gets the private key
     *
     * @param login login of user.
     * @return the private key of that user.
     */
    public String getPrivateKey(String login) {
        String query = "SELECT private_key FROM users WHERE login = ?";
        String privateKey = null;
        try {
            PreparedStatement pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, login);

            ResultSet rs = pstmt.executeQuery();

            int compt = 0;
            while (rs.next()) {
                privateKey = rs.getString(1);
                compt++;
            }
            if (compt != 1) {
                throw new SQLException("some error :there is more than 1 ");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return privateKey;
    }

    public  boolean addFriend(String user, String friend) {
        String query = "INSERT INTO friends(user, friend) VALUES(?, ?)";
        try {
           PreparedStatement pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, user);
            pstmt.setString(2, friend);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean deleteFriend(String user, String friend) {
        String query = "DELETE FROM friends WHERE user = ? AND friend = ?";
        try {
           PreparedStatement pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, user);
            pstmt.setString(2, friend);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

public boolean checkUserExist(String userLogin){
String query = "SELECT * FROM users WHERE login = ?";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, userLogin);
            ResultSet rs = pstmt.executeQuery();

            int compt = 0;
            while (rs.next()) {
                compt++;
            }
            if (compt != 1) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
}
    public List<String> getAllFriends(String userName) {
        List<String> friends = new ArrayList<>();
        String query = "SELECT friend FROM friends WHERE user = ?";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                friends.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("this issue "+e.getMessage());
        }
        return friends;
    }

    public boolean hasFriend(String user, String friend) {

        String query = "SELECT * FROM friends WHERE user = ? AND friend = ?";
        try {
            PreparedStatement pstmt = connexion.prepareStatement(query);
            pstmt.setString(1, user);
            pstmt.setString(2, friend);

            ResultSet rs = pstmt.executeQuery();

            int compt = 0;
            while (rs.next()) {
                compt++;
            }
            if (compt != 1) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    
   public List<String> getHistories(String user, String friend){
       List<String> messages = new ArrayList<>();
       try {
            
            String query="select message from message_history where user=? AND friend = ?";
            PreparedStatement pstmt = connexion.prepareStatement(query);
            pstmt.setString(1,user);
            pstmt.setString(2,friend);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()) {
                messages.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger("couln't find history ,"+DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
           return messages;
    }
     
         public boolean addMessage( String senderUserName, String destUserName,String message) {
        String sql="INSERT INTO message_history(user, friend,message) VALUES(?,?,?) ";
        try{
           PreparedStatement ps = connexion.prepareStatement(sql);
            ps.setString(1,senderUserName);
            ps.setString(2,destUserName);
            ps.setString(3,message);
            ps.executeUpdate();
            return  true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }      

    public String getSalt(String login) {
        String sql = "SELECT salt FROM users WHERE login = ?";
        String salt=null;
                try {
            PreparedStatement pstmt = connexion.prepareStatement(sql);
            pstmt.setString(1, login);

            ResultSet rs = pstmt.executeQuery();

            int compt = 0;
            while (rs.next()) {
                salt = rs.getString(1);
                compt++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
                return salt;
    }
      /**
     * creates a instance of the FavoriteDao for security reasons.
     */
    private static class DaoHolder {

        private static Dao getInstance() throws Exception {
            return new Dao();
        }
    }
         
}
