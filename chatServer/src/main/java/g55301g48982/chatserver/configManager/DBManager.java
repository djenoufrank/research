/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g55301g48982.chatserver.configManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class DBManager {

    private  Connection connection;

    Connection getConnection() {
        String jdbcUrl = "jdbc:sqlite:data/sqlite/chatServerDB.db";
        // connection.isClosed()
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(jdbcUrl);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return connection;
    }

    /**
     * Gets an instance of this database manager.
     *
     * @return an instance of this database manager.
     */
     static DBManager getInstance() {
        return DBManagerHolder.INSTANCE;
    }

    
  private static class DBManagerHolder {

        private static final DBManager INSTANCE = new DBManager();
    }

  void startTransaction() throws Exception {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException ex) {
            throw new Exception("Impossible de démarrer"
                    + " une transaction: " + ex.getMessage());
        }
    }

    void startTransaction(int isolationLevel) throws Exception {
        try {
            getConnection().setAutoCommit(false);

            int isol = 0;
            switch (isolationLevel) {
                case 0:
                    isol = java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
                    break;
                case 1:
                    isol = java.sql.Connection.TRANSACTION_READ_COMMITTED;
                    break;
                case 2:
                    isol = java.sql.Connection.TRANSACTION_REPEATABLE_READ;
                    break;
                case 3:
                    isol = java.sql.Connection.TRANSACTION_SERIALIZABLE;
                    break;
                default:
                    throw new Exception("Degré d'isolation inexistant!");
            }
            getConnection().setTransactionIsolation(isol);
        } catch (SQLException ex) {
            throw new Exception("Impossible de démarrer "
                    + "une transaction: " + ex.getMessage());
        }
    }

    void validateTransaction() throws Exception {
        try {
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new Exception("Impossible de valider "
                    + "la transaction: " + ex.getMessage());
        }
    }

    void cancelTransaction() throws Exception {
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new Exception("Impossible d'annuler "
                    + "la transaction: " + ex.getMessage());
        }
    }
}
